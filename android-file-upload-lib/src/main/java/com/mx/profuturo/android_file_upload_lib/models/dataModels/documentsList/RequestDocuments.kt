package com.mx.profuturo.android_file_upload_lib.models.dataModels.documentsList


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RequestDocuments(
    @SerializedName("rqt")
    var rqt: List<RqtDocuments>?
): Parcelable