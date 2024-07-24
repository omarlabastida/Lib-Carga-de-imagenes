package com.mx.profuturo.android_file_upload_lib.core.javaRX

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

fun <T> Observable<T>.with(schedulerProvider: ScheduleProvider): Observable<T> =
    this.observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())

fun Completable.with(schedulerProvider: ScheduleProvider): Completable =
    this.observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())

fun <T> Single<T>.with(schedulerProvider: ScheduleProvider): Single<T> =
    this.observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())