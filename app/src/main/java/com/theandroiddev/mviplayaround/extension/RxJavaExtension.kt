package com.theandroiddev.mviplayaround.extension

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber

fun <T> Observable<T>.logOnNext(log: (T) -> String): Observable<T> = this.doOnNext { Timber.d(log(it)) }

fun <T> Observable<T>?.orEmpty(): Observable<T> = this ?: Observable.empty<T>()

fun <T> T.asObservable(): Observable<T> = Observable.just(this)

fun <T> T.asSingle(): Single<T> = Single.just(this)

fun <T> T.asFlowable(): Flowable<T> = Flowable.just(this)

fun Int?.orZero() = this ?: 0
