package com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CorreoElectronico(
    @SerializedName("consecutivo")
    var consecutivo: Int?,
    @SerializedName("correoElectronico")
    var correoElectronico: String?,
    @SerializedName("idOrigenCorreo")
    var idOrigenCorreo: Int?,
    @SerializedName("idTipoCorreo")
    var idTipoCorreo: Int?,
    @SerializedName("preferencia")
    var preferencia: Int?
): Parcelable