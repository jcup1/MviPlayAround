package com.theandroiddev.mviplayaround.mvi

import android.content.Context
import android.support.v4.app.Fragment
import com.hannesdorfmann.mosby3.mvi.MviFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class MviDaggerFragment<V : MviView, P : MviPresenter<V, *>> : MviFragment<V, P>(),
        MviView, HasSupportFragmentInjector {

    @Inject
    lateinit var _childFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var injectedPresenter: P

    override fun createPresenter(): P = injectedPresenter

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return _childFragmentInjector
    }

}