package com.theandroiddev.mviplayaround.di.module

import com.theandroiddev.mviplayaround.domain.service.authentication.AuthenticationService
import com.theandroiddev.mviplayaround.domain.service.authentication.AuthenticationServiceImpl
import dagger.Module
import dagger.Provides

@Module
class ServiceModule {

    @Provides
    fun provideAuthenticationService(authenticationServiceImpl: AuthenticationServiceImpl):
            AuthenticationService = authenticationServiceImpl
}