package com.theandroiddev.mviplayaround.di.module

import com.theandroiddev.mviplayaround.di.scope.FragmentScope
import com.theandroiddev.mviplayaround.presentation.sign_in.SignInFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeSignInFragment(): SignInFragment
}
