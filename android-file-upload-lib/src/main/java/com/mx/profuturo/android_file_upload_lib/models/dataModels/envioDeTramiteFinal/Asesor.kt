package com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Asesor(
    @SerializedName("fechaFirmaSolicitud")
    var fechaFirmaSolicitud: String?,
    @SerializedName("fechaImpresionFolioSol")
    var fechaImpresionFolioSol: String?,
    @SerializedName("idAsesorPatrimonial")
    var idAsesorPatrimonial: Int?,
    @SerializedName("idAsesorVenta")
    var idAsesorVenta: Int?,
    @SerializedName("idSucursalAsesorPatrimonial")
    var idSucursalAsesorPatrimonial: Int?
): Parcelable