package com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Prospecto(
    @SerializedName("apellidoMaterno")
    var apellidoMaterno: String?,
    @SerializedName("apellidoPaterno")
    var apellidoPaterno: String?,
    @SerializedName("claveEntidad")
    var claveEntidad: String?,
    @SerializedName("curp")
    var curp: String?,
    @SerializedName("digitoVerificadorCta")
    var digitoVerificadorCta: Int?,
    @SerializedName("fechaNacimiento")
    var fechaNacimiento: String?,
    @SerializedName("folioSolitud")
    var folioSolitud: String?,
    @SerializedName("idEntidadNacimiento")
    var idEntidadNacimiento: Int?,
    @SerializedName("idEstadoCivil")
    var idEstadoCivil: Int?,
    @SerializedName("idGiroEmpresarial")
    var idGiroEmpresarial: Int?,
    @SerializedName("idMedioContacto")
    var idMedioContacto: Int?,
    @SerializedName("idNacionalidad")
    var idNacionalidad: Int?,
    @SerializedName("idNivelEstudios")
    var idNivelEstudios: Int?,
    @SerializedName("idOcupacion")
    var idOcupacion: Int?,
    @SerializedName("idPais")
    var idPais: Int?,
    @SerializedName("idRegimenAfiliacion")
    var idRegimenAfiliacion: Int?,
    @SerializedName("idSexo")
    var idSexo: Int?,
    @SerializedName("nombre")
    var nombre: String?,
    @SerializedName("numeroCuenta")
    var numeroCuenta: String?,
    @SerializedName("rfc")
    var rfc: String?
): Parcelable