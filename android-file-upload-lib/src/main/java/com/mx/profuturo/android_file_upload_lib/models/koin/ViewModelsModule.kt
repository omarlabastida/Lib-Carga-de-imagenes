package com.mx.profuturo.android_file_upload_lib.models.koin

import com.mx.profuturo.android_file_upload_lib.ui.fileUpload.viewModel.SendDocumentsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var ViewModelsModule = module {
    viewModel {SendDocumentsViewModel(get(), get(), androidContext())}
}