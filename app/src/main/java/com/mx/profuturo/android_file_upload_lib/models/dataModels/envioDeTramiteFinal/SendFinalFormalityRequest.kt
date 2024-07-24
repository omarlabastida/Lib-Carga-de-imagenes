package com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class SendFinalFormalityRequest(
    @SerializedName("asesor")
    var asesor: Asesor? = null,
    @SerializedName("claveAforeCedente")
    var claveAforeCedente: String? = null,
    @SerializedName("clienteTutor")
    var clienteTutor: ClienteTutor? = null,
    @SerializedName("correoElectronico")
    var correoElectronico: List<CorreoElectronico?>? = null,
    @SerializedName("datosControlAdministradora")
    var datosControlAdministradora: DatosControlAdministradora? = null,
    @SerializedName("documentos")
    var documentos: List<Documento?>? = null,
    @SerializedName("domicilio")
    var domicilio: List<Domicilio?>? = null,
    @SerializedName("idEstatusSolicitud")
    var idEstatusSolicitud: Int? = null,
    @SerializedName("idEtapa")
    var idEtapa: Int? = null,
    @SerializedName("idOrigenCaptura")
    var idOrigenCaptura: Int? = null,
    @SerializedName("idOrigenSolicitud")
    var idOrigenSolicitud: Int? = null,
    @SerializedName("idTipoSolicitud")
    var idTipoSolicitud: Int? = null,
    @SerializedName("idTramite")
    var idTramite: Int? = null,
    @SerializedName("marcaEdoCta")
    var marcaEdoCta: Int? = null,
    @SerializedName("prospecto")
    var prospecto: Prospecto? = null,
    @SerializedName("telefonos")
    var telefonos: List<Telefono?>? = null,
    @SerializedName("expedienteIntegral")
    var expedienteIntegral: ExpedienteIntegral? = null
): Parcelable