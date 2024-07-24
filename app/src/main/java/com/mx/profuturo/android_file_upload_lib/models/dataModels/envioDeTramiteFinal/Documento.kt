package com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Documento(
    @SerializedName("consecutivo")
    var consecutivo: Int?,
    @SerializedName("fechaVigenciaIdentificacion")
    var fechaVigenciaIdentificacion: String?,
    @SerializedName("idDocumentoSoporte")
    var idDocumentoSoporte: Int?,
    @SerializedName("idTipoDocumento")
    var idTipoDocumento: Int?
): Parcelable