package com.mx.profuturo.android_file_upload_lib.ui.fileUpload.viewModel.usesCase

import android.content.Context
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaListaDeArchivos.AddFileInformationRequest
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaListaDeArchivos.AddFileInformationResponse
import com.mx.profuturo.android_file_upload_lib.ui.fileUpload.viewModel.SendDocumentsRepository
import com.mx.profuturo.android_file_upload_lib.utils.Constants
import io.reactivex.Single

class AddFileInformationUserCase (val sendDocumentsRepository: SendDocumentsRepository){

    fun execute(
        context: Context,
        addFileInformationRequest: AddFileInformationRequest
    ): Single<AddFileInformationResponse> {
        return sendDocumentsRepository.addFileInformation(
            baseURL = "https://www.proinversion.mx:2180/",
            endpoint = "mb/fileNet/insertaInformacionArchivos",
            typeMethod = Constants.TYPE_POST_SERVICE,
            addFileInformationRequest = addFileInformationRequest ,
            context = context
        )
    }

}