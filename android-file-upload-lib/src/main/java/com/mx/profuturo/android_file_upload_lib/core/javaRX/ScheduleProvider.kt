package com.mx.profuturo.android_file_upload_lib.core.javaRX

import io.reactivex.Scheduler

interface ScheduleProvider {

    fun io(): Scheduler

    fun ui(): Scheduler

    fun computation(): Scheduler

}