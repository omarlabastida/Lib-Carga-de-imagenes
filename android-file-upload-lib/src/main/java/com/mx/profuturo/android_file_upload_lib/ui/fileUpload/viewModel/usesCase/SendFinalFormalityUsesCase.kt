package com.mx.profuturo.android_file_upload_lib.ui.fileUpload.viewModel.usesCase

import android.content.Context
import com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal.SendFinalFormalityRequest
import com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal.SendFinalFormalityResponse
import com.mx.profuturo.android_file_upload_lib.ui.fileUpload.viewModel.SendDocumentsRepository
import com.mx.profuturo.android_file_upload_lib.utils.Constants
import io.reactivex.Single

class SendFinalFormalityUsesCase (val sendDocumentsRepository: SendDocumentsRepository){

    fun execute(
        context: Context,
        curp: String,
        sendFinalFormalityRequest: SendFinalFormalityRequest
    ): Single<SendFinalFormalityResponse> {
        return sendDocumentsRepository.sendFinalFormality(
            baseURL = "https://www.proinversion.mx:2091/",
            endpoint = "iib/traspasomenor/solicitud/$curp",
            typeMethod = Constants.TYPE_POST_SERVICE,
            sendFinalFormalityRequest = sendFinalFormalityRequest ,
            context = context
        )
    }

}