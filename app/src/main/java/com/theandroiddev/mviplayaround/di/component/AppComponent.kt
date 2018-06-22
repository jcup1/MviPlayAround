package com.theandroiddev.mviplayaround.di.component

import android.app.Application
import com.theandroiddev.mviplayaround.di.module.ActivityBuildersModule
import com.theandroiddev.mviplayaround.di.module.AppModule
import com.theandroiddev.mviplayaround.di.module.FragmentBuildersModule
import com.theandroiddev.mviplayaround.di.module.ServiceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            (AndroidSupportInjectionModule::class),
            (AppModule::class),
            (ActivityBuildersModule::class),
            (FragmentBuildersModule::class),
            (ServiceModule::class)
        ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    override fun inject(application: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

}
