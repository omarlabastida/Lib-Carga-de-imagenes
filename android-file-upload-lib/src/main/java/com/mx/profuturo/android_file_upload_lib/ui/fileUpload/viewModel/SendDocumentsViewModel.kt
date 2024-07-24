package com.mx.profuturo.android_file_upload_lib.ui.fileUpload.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mx.profuturo.android_file_upload_lib.core.javaRX.ScheduleProvider
import com.mx.profuturo.android_file_upload_lib.core.javaRX.with
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaArchivos.RqtUpload
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaArchivos.UploadFileRequest
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaArchivos.UploadFileResponse
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaListaDeArchivos.AddFileInformationRequest
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaListaDeArchivos.AddFileInformationResponse
import com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal.SendFinalFormalityRequest
import com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal.SendFinalFormalityResponse
import com.mx.profuturo.android_file_upload_lib.ui.fileUpload.viewModel.usesCase.AddFileInformationUserCase
import com.mx.profuturo.android_file_upload_lib.ui.fileUpload.viewModel.usesCase.SendFinalFormalityUsesCase
import com.mx.profuturo.android_file_upload_lib.ui.fileUpload.viewModel.usesCase.UploadFileUsesCase
import kotlinx.coroutines.launch

class SendDocumentsViewModel(
    private var sendDocumentsRepository: SendDocumentsRepository,
    private var scheduleProvider: ScheduleProvider,
    private var contextApp: Context):ViewModel()
{

    private val TAG_LOG = SendDocumentsViewModel::class.simpleName



    //Variables Mutables
    private var _loading: MutableLiveData<Pair<Boolean, String>> = MutableLiveData()
    var loading: LiveData<Pair<Boolean, String>> = _loading

    private var _toolTitle: MutableLiveData<Pair<String,String>> = MutableLiveData()
    var toolTitle: LiveData<Pair<String,String>> = _toolTitle

    var _sendFinalFormality: MutableLiveData<SendFinalFormalityResponse?> = MutableLiveData()
    var sendFinalFormality: LiveData<SendFinalFormalityResponse?> = _sendFinalFormality

    var _addFileInformation: MutableLiveData<AddFileInformationResponse?> = MutableLiveData()
    var addFileInformation: LiveData<AddFileInformationResponse?> = _addFileInformation

    var _uploadFile: MutableLiveData<Pair<Pair<UploadFileResponse, RqtUpload>?, Boolean>> = MutableLiveData()
    var uploadFile: LiveData<Pair<Pair<UploadFileResponse, RqtUpload>?, Boolean>> = _uploadFile



    //Variables temporales
    var UploadFileRequest = listOf<List<UploadFileRequest>>()
    var AddFileInformationRequest = listOf<AddFileInformationRequest>()
    var sendFinalFormalityRequest = listOf<SendFinalFormalityRequest>()
    var idOrigin = ""
    var idFormality = ""
    var idProcess = 0
    var totalDocuments = 0



    fun setLoading(loader: Boolean, title: String){
        _loading.postValue(Pair(loader, title))
    }

    fun setToolTitle(title: String, subTitle: String = "") {
        _toolTitle.postValue(Pair(title, subTitle))
    }

    //Servicio utilizado para enviar un tramite de menor
    fun sendFinalFormality(sendFinalFormalityRequest: SendFinalFormalityRequest, curp: String){
        setLoading(true, "")

        viewModelScope.launch {
            SendFinalFormalityUsesCase(sendDocumentsRepository).
            execute(
                contextApp,
                curp,
                sendFinalFormalityRequest
            ).with(scheduleProvider)
                .doOnSuccess {response ->
                    Log.e(TAG_LOG, "Response Envio final de tramite -> $response")
                    setLoading(false, "")
                    if(response != null){
                        _sendFinalFormality.postValue(response)
                    }else{
                        Toast.makeText(contextApp, "Ocurrió un error, intente mas tarde", Toast.LENGTH_SHORT).show()
                    }
                }.doOnError{
                    setLoading(false, "")
                    Toast.makeText(contextApp, "Ocurrió un error, intente mas tarde", Toast.LENGTH_SHORT).show()
                }.subscribe(
                    {},
                    {
                        setLoading(false, "")
                        Toast.makeText(contextApp, "Ocurrió un error, intente mas tarde", Toast.LENGTH_SHORT).show()
                    }
                )
        }
    }

    fun addFileInformation(addFileInformationRequest: AddFileInformationRequest){
        setLoading(true, "Enviando listado de documentos")

        viewModelScope.launch {
            AddFileInformationUserCase(sendDocumentsRepository).
            execute(
                contextApp,
                addFileInformationRequest
            ).with(scheduleProvider)
                .doOnSuccess {response ->
                    Log.e(TAG_LOG, "Response Lista de archivos -> $response")
                    setLoading(false, "")
                    if(response != null){
                        if(response.status == 200){
                            _addFileInformation.postValue(response)
                        }else{
                            Toast.makeText(contextApp, "Ocurrió un error, intente mas tarde", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(contextApp, "Ocurrió un error, intente mas tarde", Toast.LENGTH_SHORT).show()
                    }
                }.doOnError{
                    setLoading(false, "")
                    Toast.makeText(contextApp, "Ocurrió un error, intente mas tarde", Toast.LENGTH_SHORT).show()
                }.subscribe(
                    {},
                    {
                        setLoading(false, "")
                        Toast.makeText(contextApp, "Ocurrió un error, intente mas tarde", Toast.LENGTH_SHORT).show()
                    }
                )
        }
    }


    fun uploadFileYounger(
        uploadFileRequest: UploadFileRequest,
        documentUpload: RqtUpload,
        isResend: Boolean = false
    ){
        //setLoading(true, "Espere un momento por favor")

        viewModelScope.launch {
            UploadFileUsesCase(sendDocumentsRepository).
            execute(
                contextApp,
                uploadFileRequest
            ).with(scheduleProvider)
                .doOnSuccess {response ->
                    Log.e(TAG_LOG, "Response Archivo Cargado -> $response")
                    //setLoading(false, "")
                    if(response != null){
                        if(response.status == 200){
                            _uploadFile.postValue(Pair(Pair(response, documentUpload), isResend))
                        }else{
                            var resp = response
                            resp.status == 500
                            _uploadFile.postValue(Pair(Pair(resp, documentUpload), isResend))
                            Toast.makeText(contextApp, response.statusText, Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(contextApp, "Ocurrió un error, intente mas tarde", Toast.LENGTH_SHORT).show()
                    }
                }.doOnError{
                    //setLoading(false, "")
                    var resp = UploadFileResponse(true, 500, "")
                    _uploadFile.postValue(Pair(Pair(resp, documentUpload), isResend))
                    Toast.makeText(contextApp, "Ocurrió un error, intente mas tarde", Toast.LENGTH_SHORT).show()
                }.subscribe(
                    {},
                    {
                        //setLoading(false, "")
                        Toast.makeText(contextApp, "Ocurrió un error, intente mas tarde", Toast.LENGTH_SHORT).show()
                    }
                )
        }
    }


}