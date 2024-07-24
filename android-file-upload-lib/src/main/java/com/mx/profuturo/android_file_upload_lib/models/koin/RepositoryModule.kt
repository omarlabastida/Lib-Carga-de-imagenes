package com.mx.profuturo.android_file_upload_lib.models.koin

import com.mx.profuturo.android_file_upload_lib.ui.fileUpload.viewModel.SendDocumentsRepository
import com.mx.profuturo.android_file_upload_lib.ui.fileUpload.viewModel.SendDocumentsRepositoryImplement
import org.koin.dsl.module


var RepositoryModule = module{
    single<SendDocumentsRepository>(createdAtStart = true) { SendDocumentsRepositoryImplement() }
}
