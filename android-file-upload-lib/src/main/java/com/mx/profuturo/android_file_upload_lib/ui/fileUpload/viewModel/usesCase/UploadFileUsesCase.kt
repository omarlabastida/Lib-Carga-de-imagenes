package com.mx.profuturo.android_file_upload_lib.ui.fileUpload.viewModel.usesCase

import android.content.Context
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaArchivos.UploadFileRequest
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaArchivos.UploadFileResponse
import com.mx.profuturo.android_file_upload_lib.ui.fileUpload.viewModel.SendDocumentsRepository
import com.mx.profuturo.android_file_upload_lib.utils.Constants
import io.reactivex.Single

class UploadFileUsesCase (val sendDocumentsRepository: SendDocumentsRepository){

    fun execute(
        context: Context,
        uploadFileRequest: UploadFileRequest
    ): Single<UploadFileResponse> {
        return sendDocumentsRepository.uploadFileYounger(
            baseURL = "https://www.proinversion.mx:2180/",
            endpoint = "mb/fileNet/cargaArchivo",
            typeMethod = Constants.TYPE_POST_SERVICE,
            uploadFileRequest = uploadFileRequest ,
            context = context
        )
    }

}