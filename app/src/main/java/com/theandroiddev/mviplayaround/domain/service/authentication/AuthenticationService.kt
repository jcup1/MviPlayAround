package com.theandroiddev.mviplayaround.domain.service.authentication

import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface AuthenticationService {

    fun signInWithGoogle(): Observable<Boolean>

}

class AuthenticationServiceImpl @Inject constructor() : AuthenticationService {

    override fun signInWithGoogle(): Observable<Boolean> = Observable.just(true).delay(5, TimeUnit.SECONDS)

}