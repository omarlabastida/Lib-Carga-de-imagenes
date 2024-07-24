package com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaListaDeArchivos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddFileInformationRequest(
    @SerializedName("rqt")
    var rqt: Rqt? = null
): Parcelable