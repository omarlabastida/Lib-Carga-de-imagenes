package com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Telefono(
    @SerializedName("claveCelular")
    var claveCelular: String?,
    @SerializedName("claveLada")
    var claveLada: String?,
    @SerializedName("consecutivo")
    var consecutivo: Int?,
    @SerializedName("extension")
    var extension: String?,
    @SerializedName("idOrigen")
    var idOrigen: Int?,
    @SerializedName("idTipoTelefono")
    var idTipoTelefono: Int?,
    @SerializedName("numeroTelefono")
    var numeroTelefono: String?,
    @SerializedName("preferente")
    var preferente: Int?
): Parcelable