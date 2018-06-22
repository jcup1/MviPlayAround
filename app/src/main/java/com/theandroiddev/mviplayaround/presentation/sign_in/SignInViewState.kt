package com.theandroiddev.mviplayaround.presentation.sign_in

import java.io.Serializable

data class SignInViewState(

        val isUserSigningIn: Boolean = false,
        val isUserSignedIn: Boolean = false,
        val userSigningInError: Throwable? = null

) : Serializable {

    constructor(builder: Builder) : this(
            builder.isUserSigningIn,
            builder.isUserSignedIn,
            builder.userSigningInError
    )

    inline fun build(block: SignInViewState.Builder.() -> Unit) =
            SignInViewState.Builder(this).apply(block).build()

    class Builder(toCopyFrom: SignInViewState) {

        var isUserSigningIn = toCopyFrom.isUserSigningIn
        var isUserSignedIn = toCopyFrom.isUserSignedIn
        var userSigningInError = toCopyFrom.userSigningInError

        fun build() = SignInViewState(this)
    }
}

sealed class SignInPartialViewState {

    //region Sign in
    class SigningInPartialViewState : SignInPartialViewState()

    data class SignedInPartialViewState(val isUserSignedIn: Boolean) : SignInPartialViewState()

    data class SigningInErrorPartialViewState(val signingInError: Throwable?) : SignInPartialViewState()
    //endregion

}
