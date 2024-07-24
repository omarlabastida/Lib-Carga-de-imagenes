package com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaListaDeArchivos


import com.google.gson.annotations.SerializedName

data class AddFileInformationResponse(
    @SerializedName("idBitacora")
    var idBitacora: Int?,
    @SerializedName("result")
    var result: Boolean?,
    @SerializedName("status")
    var status: Int?,
    @SerializedName("statusText")
    var statusText: String?
)