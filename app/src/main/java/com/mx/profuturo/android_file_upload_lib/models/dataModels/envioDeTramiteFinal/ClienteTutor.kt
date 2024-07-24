package com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClienteTutor(
    @SerializedName("curpPadreTutor")
    var curpPadreTutor: String?,
    @SerializedName("idCliente")
    var idCliente: Int?,
    @SerializedName("idContrato")
    var idContrato: Int?,
    @SerializedName("idOrigen")
    var idOrigen: Int?,
    @SerializedName("idTramiteTutor")
    var idTramiteTutor: Int? = null,
    @SerializedName("numeroCuentaTutor")
    var numeroCuentaTutor: String?,
): Parcelable