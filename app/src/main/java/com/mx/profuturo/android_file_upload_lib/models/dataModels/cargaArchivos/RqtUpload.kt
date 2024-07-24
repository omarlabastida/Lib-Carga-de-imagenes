package com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaArchivos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RqtUpload(
    @SerializedName("base64")
    var base64: String? = null,
    @SerializedName("idProceso")
    var idProceso: Int? = 11251397,
    @SerializedName("idTramite")
    var idTramite: String? = null,
    @SerializedName("nombre")
    var nombre: String = "",
    var estatusDeEnvio: Int? = 0
): Parcelable