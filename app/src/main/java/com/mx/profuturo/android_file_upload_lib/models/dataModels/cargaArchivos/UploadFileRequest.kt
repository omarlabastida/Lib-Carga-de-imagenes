package com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaArchivos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UploadFileRequest(
    @SerializedName("rqt")
    var rqt: RqtUpload? = null
): Parcelable