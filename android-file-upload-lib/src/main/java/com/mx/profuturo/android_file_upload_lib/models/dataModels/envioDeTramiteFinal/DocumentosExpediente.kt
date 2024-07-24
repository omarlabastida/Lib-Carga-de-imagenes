package com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DocumentosExpediente(
    @SerializedName("fechaVigenciaNueva")
    var fechaVigenciaNueva: String?,
    @SerializedName("idSubtipoDoc")
    var idSubtipoDoc: String?,
    @SerializedName("idTipoDoc")
    var idTipoDoc: String?
): Parcelable