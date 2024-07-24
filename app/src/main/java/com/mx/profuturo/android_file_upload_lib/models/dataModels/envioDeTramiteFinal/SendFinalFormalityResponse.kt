package com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal


import com.google.gson.annotations.SerializedName

data class SendFinalFormalityResponse(
    @SerializedName("idSolicitud")
    var idSolicitud: Int?
)