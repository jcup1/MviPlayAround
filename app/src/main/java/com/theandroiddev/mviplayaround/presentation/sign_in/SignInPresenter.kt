package com.theandroiddev.mviplayaround.presentation.sign_in

import com.theandroiddev.mviplayaround.domain.service.authentication.AuthenticationService
import com.theandroiddev.mviplayaround.mvi.MviPresenter
import com.theandroiddev.mviplayaround.presentation.sign_in.SignInPartialViewState.*
import io.reactivex.Observable
import javax.inject.Inject

class SignInPresenter @Inject constructor(
        val authenticationService: AuthenticationService
) : MviPresenter<SignInView, SignInViewState>() {

    override fun bindIntents() {

        val signInWithGoogle: Observable<SignInPartialViewState> =
                intent(SignInView::signInWithGoogleButtonClickedIntent)
                        .flatMap {
                            signInWithGoogle()
                        }

        val initialState = SignInViewState()

        val allIntentsObservable =
                Observable.merge(
                        arrayListOf(
                                signInWithGoogle
                        ))

        val scan = allIntentsObservable.scan(initialState, this::viewStateReducer)
                .distinctUntilChanged()

        subscribeViewState(scan, SignInView::render)

    }

    private fun signInWithGoogle(): Observable<SignInPartialViewState> =
            authenticationService.signInWithGoogle()
                    .flatMap { Observable.just(SignedInPartialViewState(true)) }
                    .cast(SignInPartialViewState::class.java)
                    .startWith(SigningInPartialViewState())
                    .onErrorReturn { error -> SigningInErrorPartialViewState(error) }

    private fun viewStateReducer(previousState: SignInViewState,
                                 partialStateChanges: SignInPartialViewState) =
            when (partialStateChanges) {

                is SigningInPartialViewState -> previousState.build {
                    isUserSigningIn = true
                }
                is SignedInPartialViewState -> previousState.build {
                    isUserSigningIn = false
                    isUserSignedIn = true
                }
                is SigningInErrorPartialViewState -> previousState.build {
                    isUserSigningIn = false
                    userSigningInError = partialStateChanges.signingInError
                }
            }
}

