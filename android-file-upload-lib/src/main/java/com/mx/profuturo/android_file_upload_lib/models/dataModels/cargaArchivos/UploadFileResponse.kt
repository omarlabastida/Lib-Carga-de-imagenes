package com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaArchivos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UploadFileResponse(
    @SerializedName("result")
    var result: Boolean?,
    @SerializedName("status")
    var status: Int?,
    @SerializedName("statusText")
    var statusText: String?
): Parcelable