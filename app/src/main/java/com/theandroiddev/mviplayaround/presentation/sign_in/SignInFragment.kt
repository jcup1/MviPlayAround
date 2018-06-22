package com.theandroiddev.mviplayaround.presentation.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theandroiddev.mviplayaround.R
import com.theandroiddev.mviplayaround.mvi.MviDaggerFragment
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_sign_in.*
import timber.log.Timber

class SignInFragment : MviDaggerFragment<SignInView, SignInPresenter>(),
        SignInView {

    val signInWithGoogleButtonClicked = PublishSubject.create<Boolean>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sign_in_with_google_button.setOnClickListener { signInWithGoogleButtonClicked.onNext(true) }

    }

    override fun signInWithGoogleButtonClickedIntent() = signInWithGoogleButtonClicked

    override fun render(viewState: SignInViewState) {

        Timber.d("View State: $viewState")

        if (viewState.isUserSignedIn) {
            activity?.finish()
        }

        sign_in_swipe_refresh_layout.isRefreshing = viewState.isUserSigningIn

    }
}
