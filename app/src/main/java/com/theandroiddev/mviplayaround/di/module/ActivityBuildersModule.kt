package com.theandroiddev.mviplayaround.di.module

import com.theandroiddev.mviplayaround.di.scope.ActivityScope
import com.theandroiddev.mviplayaround.presentation.dashboard.DashboardActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeDashboardActivity(): DashboardActivity

}
