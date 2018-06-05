package com.theandroiddev.mviplayaround.mvi

import android.os.Bundle
import android.support.v4.app.Fragment
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.hannesdorfmann.mosby3.mvi.MviPresenter
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class MviDaggerAppCompatActivity<V : MviView, P : MviPresenter<V, *>> :
        MviActivity<V, P>(), MviView, HasFragmentInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var _supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var _frameworkFragmentInjector: DispatchingAndroidInjector<android.app.Fragment>

    @Inject
    lateinit var injectedPresenter: P

    override fun createPresenter(): P = injectedPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return _supportFragmentInjector
    }

    override fun fragmentInjector(): AndroidInjector<android.app.Fragment>? {
        return _frameworkFragmentInjector
    }


}