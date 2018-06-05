package com.theandroiddev.mviplayaround.mvi

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter

abstract class MviPresenter<V : MviView, VS> : MviBasePresenter<V, VS>()