package com.mx.profuturo.android_file_upload_lib.app

import android.app.Application
import android.support.multidex.MultiDexApplication
import com.mx.profuturo.android_file_upload_lib.models.koin.RepositoryModule
import com.mx.profuturo.android_file_upload_lib.models.koin.ViewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SendDocumentsApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SendDocumentsApp)
            modules(listOf(RepositoryModule, ViewModelsModule))
        }
    }

}