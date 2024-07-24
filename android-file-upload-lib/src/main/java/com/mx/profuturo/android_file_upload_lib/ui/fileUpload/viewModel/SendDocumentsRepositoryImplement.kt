package com.mx.profuturo.android_file_upload_lib.ui.fileUpload.viewModel

import android.content.Context
import com.mx.profuturo.android_file_upload_lib.core.retrofitRX.RetrofitRX
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaArchivos.UploadFileRequest
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaArchivos.UploadFileResponse
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaListaDeArchivos.AddFileInformationRequest
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaListaDeArchivos.AddFileInformationResponse
import com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal.SendFinalFormalityRequest
import com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal.SendFinalFormalityResponse
import io.reactivex.Single

class SendDocumentsRepositoryImplement: SendDocumentsRepository {
    override fun sendFinalFormality(
        baseURL: String,
        endpoint: String,
        typeMethod: String,
        sendFinalFormalityRequest: SendFinalFormalityRequest,
        context: Context
    ): Single<SendFinalFormalityResponse> {
        return RetrofitRX().excecuteService(
            baseURL = baseURL,
            endpoint = endpoint,
            typeMethod = typeMethod,
            request = sendFinalFormalityRequest,
            response = SendFinalFormalityResponse::class.java,
            context = context
        )
    }

    override fun addFileInformation(
        baseURL: String,
        endpoint: String,
        typeMethod: String,
        addFileInformationRequest: AddFileInformationRequest,
        context: Context
    ): Single<AddFileInformationResponse> {
        return RetrofitRX().excecuteService(
            baseURL = baseURL,
            endpoint = endpoint,
            typeMethod = typeMethod,
            request = addFileInformationRequest,
            response = AddFileInformationResponse::class.java,
            context = context
        )
    }

    override fun uploadFileYounger(
        baseURL: String,
        endpoint: String,
        typeMethod: String,
        uploadFileRequest: UploadFileRequest,
        context: Context
    ): Single<UploadFileResponse> {
        return RetrofitRX().excecuteService(
            baseURL = baseURL,
            endpoint = endpoint,
            typeMethod = typeMethod,
            request = uploadFileRequest,
            response = UploadFileResponse::class.java,
            context = context
        )
    }
}