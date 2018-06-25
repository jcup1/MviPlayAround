package com.theandroiddev.mviplayaround.presentation.sign_in

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import org.junit.Assert
import org.junit.jupiter.api.Assertions
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class SignInViewRobot @Inject constructor(
        presenter: SignInPresenter
) {

    val signInWithGoogleButtonClicked = PublishSubject.create<Boolean>()
    val renderEvents = CopyOnWriteArrayList<SignInViewState>()
    val renderEventSubject = ReplaySubject.create<SignInViewState>()

    private val view = object : SignInView {

        override fun signInWithGoogleButtonClickedIntent(): PublishSubject<Boolean> {
            return signInWithGoogleButtonClicked
        }

        override fun render(viewState: SignInViewState) {
            renderEvents.add(viewState)
            renderEventSubject.onNext(viewState)
        }

    }

    init {
        presenter.attachView(view)
    }

    fun fireSignInWithGoogleButtonClickedIntent() {
        signInWithGoogleButtonClicked.onNext(true)
    }

    fun assertViewStateRendered(vararg expectedSignInViewStates: SignInViewState) {

        if (expectedSignInViewStates.isEmpty()) {
            throw NullPointerException("expectedSignInViewStates == null")
        }

        val eventsCount: Long = expectedSignInViewStates.size.toLong()
        renderEventSubject.take(eventsCount).timeout(10, TimeUnit.SECONDS).blockingSubscribe()

        if (renderEventSubject.values.size > eventsCount) {
            Assert.fail("Expected to wait for "
                    + eventsCount
                    + ", but there were "
                    + renderEventSubject.values.size
                    + " Events in total, which is more than expected: "
                    + renderEventSubject.values.toString())
        }

        Assertions.assertEquals(Arrays.asList(expectedSignInViewStates), renderEvents)

    }

}