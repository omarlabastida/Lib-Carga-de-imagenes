package com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaListaDeArchivos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaListaDeArchivos.DocumentoNombre
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rqt(
    @SerializedName("documentos")
    var documentos: List<DocumentoNombre?>? = null,
    @SerializedName("idProceso")
    var idProceso: String? = "11251397",
    @SerializedName("idTramite")
    var idTramite: String? = null,
    @SerializedName("total")
    var total: Int? = null
): Parcelable