package com.mx.profuturo.android_file_upload_lib.core.javaRX
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApplicationScheduleProvider: ScheduleProvider {
    override fun io() = Schedulers.io()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun computation() = Schedulers.computation()
}