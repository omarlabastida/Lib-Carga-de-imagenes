package com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Domicilio(
    @SerializedName("calle")
    var calle: String?,
    @SerializedName("ciudad")
    var ciudad: String?,
    @SerializedName("colonia")
    var colonia: String?,
    @SerializedName("consecutivo")
    var consecutivo: Int?,
    @SerializedName("idCodigoPostal")
    var idCodigoPostal: String?,
    @SerializedName("idEntidadFederativa")
    var idEntidadFederativa: Int?,
    @SerializedName("idMunicipio")
    var idMunicipio: Int?,
    @SerializedName("municipio")
    var municipio: String?,
    @SerializedName("idPais")
    var idPais: Int?,
    @SerializedName("idTipoDomicilio")
    var idTipoDomicilio: Int?,
    @SerializedName("numeroExterior")
    var numeroExterior: String?,
    @SerializedName("numeroInterior")
    var numeroInterior: String?
): Parcelable