package com.mx.profuturo.android.traspasospro.ui.traspasoMenores.view.EnvioDeTramite

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mx.profuturo.android_file_upload_lib.databinding.FragmentSendDocumentsBinding
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaArchivos.RqtUpload
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaArchivos.UploadFileRequest
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaListaDeArchivos.AddFileInformationRequest
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaListaDeArchivos.DocumentoNombre
import com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal.SendFinalFormalityRequest
import com.mx.profuturo.android_file_upload_lib.models.dataModels.procedures.Procedure
import com.mx.profuturo.android_file_upload_lib.ui.fileUpload.adapters.ProgressDocumentsAdapter
import com.mx.profuturo.android_file_upload_lib.ui.fileUpload.viewModel.SendDocumentsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SendDocumentsFragment: Fragment() {

    private lateinit var binding: FragmentSendDocumentsBinding
    private lateinit var adapter: ProgressDocumentsAdapter
    private val viewModelSenDocuments by sharedViewModel<SendDocumentsViewModel>()
    private var procedureList = mutableListOf<SendFinalFormalityRequest>()//Lista de Trámites a enviar L1
    private var procedureSendList = mutableListOf<SendFinalFormalityRequest>()//Lista de Trámites enviados L2
    private var documentsToSendList = mutableListOf<RqtUpload>()//Lista de documentos a enviar L3
    private var documentsTotalList = mutableListOf<UploadFileRequest>()//Lista de documentos totales L4
    private var documentsContentList = mutableListOf<RqtUpload>()//Lista del contenido base64 de los documentos L5
    private var documentsSendList = mutableListOf<RqtUpload>()//Lista con los documentos enviados L6
    private var isValidateYounger = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSendDocumentsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setInitialValues()
        setObservers()
        setListeners()
    }

    private fun setInitialValues() {
        procedureList.clear()
        procedureSendList.clear()
        sendDocumentsRecyclerView()
        getTotalProcedures()
    }

    private fun setObservers() {
        viewModelSenDocuments.sendFinalFormality.observe(viewLifecycleOwner){ sendFormality ->
            if(sendFormality != null){
                if(sendFormality.idSolicitud != null){
                    Log.d("TRAMITE", "-------------------------------------------")
                    Log.d("TRAMITE", "DATOS DE MENOR ENVIADOS")
                    Log.d("TRAMITE", "-------------------------------------------")
                    val youngerSend = procedureList[procedureSendList.size]
                    youngerSend.estatusEnvio = true
                    //viewModelSenDocuments.updateYounger(youngerSend) //
                    sendListDocuments(viewModelSenDocuments.AddFileInformationRequest[procedureSendList.size])
                }
                viewModelSenDocuments._sendFinalFormality.value = null
            }
        }

        viewModelSenDocuments.addFileInformation.observe(viewLifecycleOwner){ addFileInformation ->
            if(addFileInformation != null){
                Log.d("TRAMITE", "-------------------------------------------")
                Log.d("TRAMITE", "LISTA DE DOCUMENTOS ENVIADA")
                Log.d("TRAMITE", "-------------------------------------------")
                sendDocument(documentsTotalList[documentsSendList.size])
                viewModelSenDocuments._addFileInformation.value = null
            }
        }

        viewModelSenDocuments.uploadFile.observe(viewLifecycleOwner){ uploadFile ->
            Log.d("DOCUMENTO_RESPONSE", "${uploadFile?.first?.second?.nombre}")
            if(uploadFile != null){
                if(uploadFile.first?.first?.status !=500){
                    var documentUpload = RqtUpload()
                    documentUpload = uploadFile.first?.second?: RqtUpload()
                    documentUpload.estatusDeEnvio = 1
                    documentsContentList[documentsSendList.size] = documentUpload
                    adapter.notifyItemChanged(documentsSendList.size)
                    scrollDown()
                    documentsSendList.add(documentUpload)
                    //viewModelSenDocuments.setToolTitle("Documentos cargados ${documentsSendList.size} de ${documentsToSendList.size}", "CURP DE MENOR: " + procedureList[procedureSendList.size].prospecto?.curp + "    NOMBRE DE MENOR: " + procedureList[procedureSendList.size].prospecto?.nombre + " " + procedureList[procedureSendList.size].prospecto?.apellidoPaterno + " " + procedureList[procedureSendList.size].prospecto?.apellidoMaterno)
                    if(documentsSendList.size < documentsToSendList.size){
                        sendDocument(documentsTotalList[documentsSendList.size])
                    }else{
                        var sizeYoungerList = 0
                        sizeYoungerList = procedureSendList.size
                        procedureSendList.add(procedureList[sizeYoungerList])
                        Toast.makeText(requireActivity(), "El trámite de menor con Curp: ${procedureSendList.last().prospecto?.curp} se ha enviado", Toast.LENGTH_LONG).show()
                        continueFormality()
                    }
                }else{
                    var documentUpload = RqtUpload()
                    documentUpload = uploadFile.first?.second?: RqtUpload()
                    documentUpload.estatusDeEnvio = 2
                    documentsContentList[documentsSendList.size] = documentUpload
                    adapter.notifyItemChanged(documentsSendList.size)
                    scrollDown()
                    //viewModelSenDocuments.setToolTitle("Documentos cargados ${documentsSendList.size} de ${documentsToSendList.size}", "CURP DE MENOR: " + procedureList[procedureSendList.size].prospecto?.curp + "    NOMBRE DE MENOR: " + procedureList[procedureSendList.size].prospecto?.nombre + " " + procedureList[procedureSendList.size].prospecto?.apellidoPaterno + " " + procedureList[procedureSendList.size].prospecto?.apellidoMaterno)
                    binding.btRetry.visibility = View.VISIBLE
                }
                viewModelSenDocuments._uploadFile.value = null
            }
        }
    }

    private fun setListeners() {
        binding.btContinue.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("YoungerFromality", "TRAMITE_FINALIZADO")
            requireActivity().setResult(Activity.RESULT_OK, resultIntent)
            requireActivity().finish()
        }

        binding.btRetry.setOnClickListener {
            reloadDocument()
        }
    }

    private fun continueFormality(){
        if(procedureList.size != procedureSendList.size){
            binding.btContinue.visibility = View.GONE
         /*   if(!procedureList[procedureSendList.size].estatusEnvio) sendFormalityYounger(procedureList[procedureSendList.size])
            else*/
            sendListDocuments(viewModelSenDocuments.AddFileInformationRequest[procedureSendList.size])
        }else{
           /* if(procedureList[0].traspasoConTitular){
                val resultIntent = Intent()
                resultIntent.putExtra("YoungerFromality", "TRAMITE_FINALIZADO")
                requireActivity().setResult(Activity.RESULT_OK, resultIntent)
                requireActivity().finish()
            }else{*/
               /* if(procedureList[0].traspasoConTitular){
                    Navigation.findNavController(binding.root).navigate(R.id.action_envioDeDocumentosDeMenorFragment_to_envioDeTramiteExitosoFragment)
                }else{
                    Navigation.findNavController(binding.root).navigate(R.id.action_sendDocumentsFragment_to_envioDeTramiteExitosoFragment2)
                }*/

                //TODO Aqui finaliza el envio del tramite
            //}

            //binding.btContinue.visibility = View.VISIBLE
        }
    }

    private fun reloadDocument() {
        binding.btRetry.visibility = View.GONE
        documentsContentList.remove(documentsContentList.last())
        //adapter.notifyDataSetChanged()
        sendDocument(documentsTotalList[documentsSendList.size])
    }

    private fun sendDocument(document: UploadFileRequest) {
        val documentToSend = document
        documentToSend.rqt?.estatusDeEnvio = 0
        documentsContentList.add(documentToSend.rqt?: RqtUpload())
        adapter.notifyItemChanged(documentsContentList.size - 1)
        scrollDown()
        viewModelSenDocuments.uploadFileYounger(
            document,
            document.rqt?: RqtUpload()
        )
    }

    private fun sendListDocuments(ListDocumentsToSend: AddFileInformationRequest) {
        //viewModelSenDocuments.setToolTitle("Enviando lista de documentos del menor ", "CURP DE MENOR: " + procedureList[procedureSendList.size].prospecto?.curp + "    NOMBRE DE MENOR: " + procedureList[procedureSendList.size].prospecto?.nombre + " " + procedureList[procedureSendList.size].prospecto?.apellidoPaterno + " " + procedureList[procedureSendList.size].prospecto?.apellidoMaterno)
        if(ListDocumentsToSend != null){
            documentsToSendList.clear()
            documentsContentList.clear()
            documentsSendList.clear()
            documentsTotalList.clear()
            adapter.notifyDataSetChanged()
            viewModelSenDocuments.UploadFileRequest[procedureSendList.size].forEach {
                if (it != null) {
                    documentsTotalList.add(it)
                }
            }
            viewModelSenDocuments._addFileInformation.value = null
            viewModelSenDocuments.addFileInformation(
                ListDocumentsToSend
            )
        }
    }

    private fun getTotalProcedures() {
        if(isValidateYounger){
            if(!viewModelSenDocuments.sendFinalFormalityRequest.isNullOrEmpty()){
                procedureList.addAll(viewModelSenDocuments.sendFinalFormalityRequest)
                if(!procedureList[procedureSendList.size].estatusEnvio) sendFormalityYounger(procedureList[procedureSendList.size])
                else sendListDocuments(viewModelSenDocuments.AddFileInformationRequest[procedureSendList.size])
                Toast.makeText(requireContext(), "Tramite de menores", Toast.LENGTH_LONG).show()
            }else{
                sendListDocuments(viewModelSenDocuments.AddFileInformationRequest[procedureSendList.size])
                Toast.makeText(requireContext(), "Tramite de Registro", Toast.LENGTH_LONG).show()
            }
            isValidateYounger = false
        }

    }

    private fun sendFormalityYounger(procedureRequest: SendFinalFormalityRequest){
        //viewModelSenDocuments.setToolTitle("Enviando información del menor", "CURP DE MENOR: " + procedureList[procedureSendList.size].prospecto?.curp + "    NOMBRE DE MENOR: " + procedureList[procedureSendList.size].prospecto?.nombre + " " + procedureList[procedureSendList.size].prospecto?.apellidoPaterno + " " + procedureList[procedureSendList.size].prospecto?.apellidoMaterno)
        try {
            viewModelSenDocuments.sendFinalFormality(
                procedureRequest,
                procedureRequest.prospecto?.curp?:""
            )
        }catch (exception:Exception){
            Log.d("CALIDAD", "ERROR EN ENVIO DE TRAMITE --> ${exception.message}")
            Toast.makeText(requireContext(), "Ocurrio un error al generar el trámite", Toast.LENGTH_LONG).show()
        }
    }


    private fun scrollDown(){
        if (adapter != null && adapter.itemCount > 0) {
            val lastPosition = adapter.itemCount - 1
            binding.rvSendDocuments.smoothScrollToPosition(lastPosition)
        }
    }

    private fun sendDocumentsRecyclerView() {
        adapter = ProgressDocumentsAdapter(viewModelSenDocuments, documentsContentList){ reSendDocument ->
            reloadDocument()
        }
        binding.rvSendDocuments.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}