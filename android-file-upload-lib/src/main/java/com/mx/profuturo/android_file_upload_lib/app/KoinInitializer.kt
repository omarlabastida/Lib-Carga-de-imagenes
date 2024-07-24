package com.mx.profuturo.android_file_upload_lib.app

import android.content.Context
import com.mx.profuturo.android_file_upload_lib.models.koin.CommonModule
import com.mx.profuturo.android_file_upload_lib.models.koin.RepositoryModule
import com.mx.profuturo.android_file_upload_lib.models.koin.ViewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

object KoinInitializer {
    fun init(context: Context) {
        startKoin {
            androidLogger()
            androidContext(context)
            modules(listOf(RepositoryModule, ViewModelsModule, CommonModule))
        }
    }
}