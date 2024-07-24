package com.mx.profuturo.android_file_upload_lib.models.dataModels.procedures

data class Procedure(
     var idTramiteDeMenor: Int = 0,
    var curpDeMenor: String = "",
    var name: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var tipoTitular: String = "",
    var traspasoConTitular: Boolean = false,
    var estatusEnvio: Boolean = false,
    var curpDeTitular: String = ""
)
