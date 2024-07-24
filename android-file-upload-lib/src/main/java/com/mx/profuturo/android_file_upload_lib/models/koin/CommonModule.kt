package com.mx.profuturo.android_file_upload_lib.models.koin

import com.mx.profuturo.android_file_upload_lib.core.javaRX.ApplicationScheduleProvider
import com.mx.profuturo.android_file_upload_lib.core.javaRX.ScheduleProvider
import org.koin.dsl.module

val CommonModule = module{
    single<ScheduleProvider>(createdAtStart = true) {ApplicationScheduleProvider()}
}