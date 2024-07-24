package com.mx.profuturo.android_file_upload_lib.models.dataModels.documentsList


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RqtDocuments(
    @SerializedName("base64")
    var base64: String?,
    @SerializedName("nombre")
    var nombre: String?
): Parcelable