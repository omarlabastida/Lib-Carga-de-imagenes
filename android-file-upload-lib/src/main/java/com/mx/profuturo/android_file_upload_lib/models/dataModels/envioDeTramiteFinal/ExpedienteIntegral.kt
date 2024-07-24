package com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal.DocumentosExpediente
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExpedienteIntegral(
    @SerializedName("idTramite")
    var idTramite: Int?,
    @SerializedName("idExpUnico")
    var idExpUnico: String?,
    @SerializedName("numeroCuenta")
    var numeroCuenta: String?,
    @SerializedName("idSolicitud")
    var idSolicitud: String? = null,
    @SerializedName("idOrigen")
    var idOrigen: String? = "643",
    @SerializedName("documentosExpediente")
    var documentosExpediente: List<DocumentosExpediente?>?,
): Parcelable