package com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaArchivos


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RqtUpload(
    @SerializedName("base64")
    @Expose
    var base64: String? = null,
    @SerializedName("idProceso")
    @Expose
    var idProceso: Int? = 11251397,
    @SerializedName("idTramite")
    @Expose
    var idTramite: String? = null,
    @SerializedName("nombre")
    @Expose
    var nombre: String = "",
    @Transient
    var estatusDeEnvio: Int? = 0
): Parcelable