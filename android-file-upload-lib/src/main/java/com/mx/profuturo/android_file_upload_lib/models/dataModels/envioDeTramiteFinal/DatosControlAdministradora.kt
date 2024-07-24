package com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DatosControlAdministradora(
    @SerializedName("fechaOperacionCaptura")
    var fechaOperacionCaptura: String?,
    @SerializedName("referenciaCVP")
    var referenciaCVP: String?
): Parcelable