package com.theandroiddev.mviplayaround.presentation.sign_in

import com.theandroiddev.mviplayaround.mvi.MviView
import io.reactivex.subjects.PublishSubject

interface SignInView : MviView {

    fun signInWithGoogleButtonClickedIntent(): PublishSubject<Boolean>

    fun render(viewState: SignInViewState)
}