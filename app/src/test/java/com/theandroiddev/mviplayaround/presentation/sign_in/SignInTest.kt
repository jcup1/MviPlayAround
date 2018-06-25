package com.theandroiddev.mviplayaround.presentation.sign_in

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.inject.Inject

internal class SignInTest @Inject() constructor(
        val presenter: SignInPresenter,
        val view: SignInView,
        val signInViewRobot: SignInViewRobot
) {

    @Test
    fun shouldPass() {
        Assertions.assertEquals(4, 2 + 2)
    }

    @Test
    fun testSigningIn() {

        presenter.attachView(view)

        signInViewRobot.fireSignInWithGoogleButtonClickedIntent()

        val expectedData = true

        val signingInViewState = SignInViewState(isUserSigningIn = true)

        val signedInViewState = SignInViewState(isUserSignedIn = expectedData)

        signInViewRobot.assertViewStateRendered(signingInViewState, signedInViewState)

    }

}
