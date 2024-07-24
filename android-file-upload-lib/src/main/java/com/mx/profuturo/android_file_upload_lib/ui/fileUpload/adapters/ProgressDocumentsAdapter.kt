package com.mx.profuturo.android_file_upload_lib.ui.fileUpload.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mx.profuturo.android_file_upload_lib.R
import com.mx.profuturo.android_file_upload_lib.databinding.ItemDocumentsSendBinding
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaArchivos.RqtUpload
import com.mx.profuturo.android_file_upload_lib.tools.ToolsSendDocuments
import com.mx.profuturo.android_file_upload_lib.ui.fileUpload.viewModel.SendDocumentsViewModel
import com.mx.profuturo.android_file_upload_lib.utils.Constants
import com.squareup.picasso.Picasso

class ProgressDocumentsAdapter(
    sendDocumentsViewModel: SendDocumentsViewModel,
    var listDocuments: List<RqtUpload>,
    var reSendDocument: (RqtUpload) -> Unit
):RecyclerView.Adapter<ProgressDocumentsAdapter.ProgressDocumentsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressDocumentsHolder {
        return ProgressDocumentsHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_documents_send, parent, false))
    }

    override fun getItemCount(): Int {
        return listDocuments.size
    }

    override fun onBindViewHolder(holder: ProgressDocumentsHolder, position: Int) {
        holder.bindView(listDocuments[position], reSendDocument)
    }

    inner class ProgressDocumentsHolder(val view: View): RecyclerView.ViewHolder(view){

        private val binding = ItemDocumentsSendBinding.bind(view)
        fun bindView(
            dataDocuments: RqtUpload,
            reSendDocument: (RqtUpload) -> Unit,
        ){

            binding.llCardAddFile.visibility = View.GONE
            binding.llCardFileImage.visibility = View.GONE
            binding.llCardFileChargue.visibility = View.GONE
            var totalPages = 0
            try {
                var listName = dataDocuments.nombre.split("_")
                totalPages = listName.last().split(".").first().toInt()
            }catch (e: Throwable){
                Log.e("CALIDAD", "Error encontrado en ---> $e")
            }

            var documentName =
            if(dataDocuments.nombre.contains(Constants.ACTA_DE_NACIMIENTO)){
                Constants.ACTAS_DE_NACIMIENTO_TITULO + " " + if(totalPages>1) " PAGINA " + totalPages.toString() else ""
            }else if(dataDocuments.nombre.contains(Constants.ACUSE_DE_EXPEDIENTE_MENOR)){
                Constants.ACUSE_TITULO + " " + if(totalPages>1) " PAGINA " + totalPages.toString() else ""
            }else if(dataDocuments.nombre.contains(Constants.CARTA_DE_DERECHOS_MENOR)){
                Constants.CARTA_DE_DERECHOS_TITULO + " " + if(totalPages>1) " PAGINA " + totalPages.toString() else ""
            }else if(dataDocuments.nombre.contains(Constants.CONTRATO_ADMINISTRADORA)){
                Constants.CONTRATO_DE_ADMINISTRADORA_TITULO + " " + if(totalPages>1) " PAGINA " + totalPages.toString() else ""
            }else if(dataDocuments.nombre.contains(Constants.CREDENCIAL_CONSAR)){
                Constants.CREDENCIAL_CONSAR_TITULO + " " + if(totalPages>1) " PAGINA " + totalPages.toString() else ""
            }else if(dataDocuments.nombre.contains(Constants.DOCUMENTO_PATRIA_PROTESTAD)){
                Constants.DOCUMENTO_QUE_ACREDITA_COMO_MAMA_O_PAPA + " " + if(totalPages>1) " PAGINA " + totalPages.toString() else ""
            }else if(dataDocuments.nombre.contains(Constants.IDENTIFICACION_OFICIAL_ANVERSO)){
                Constants.IDENTIFICACION_OFICIAL_ANVERSO_PAPA_O_MAMA + " " + if(totalPages>1) " PAGINA " + totalPages.toString() else ""
            }else if(dataDocuments.nombre.contains(Constants.IDENTIFICACION_OFICIAL_REVERSO)){
                Constants.IDENTIFICACION_OFICIAL_REVERSO_PAPA_O_MAMA + " " + if(totalPages>1) " PAGINA " + totalPages.toString() else ""
            }else if(dataDocuments.nombre.contains(Constants.SOLICITUD_DE_TRASPASO)){
                Constants.SOLICITUD_DE_TRASPASO_TITULO + " " + if(totalPages>1) " PAGINA " + totalPages.toString() else ""
            }else if(dataDocuments.nombre.contains(Constants.COMPROBANTE_DE_DOMICILIO)) {
                Constants.COMRPOBANTE_DE_DOMICILIO_DEL_MENOR + " " + if (totalPages > 1) " PAGINA " + totalPages.toString() else ""
            }else ""

            binding.addNewDocument.text = documentName
            binding.tvNameDocumentToSend.text = documentName
            binding.tvErrorDocument.text  = documentName
            when(dataDocuments.estatusDeEnvio){
                0 ->{
                    binding.llCardFileChargue.visibility = View.VISIBLE
                    if (dataDocuments.nombre.split(".").last() == "jpg" || dataDocuments.nombre.split(".").last() == "jpeg" || dataDocuments.nombre.split(".").last() == "png") {
                        binding.ivImageFileToSend.setImageResource(R.drawable.ic_jpg_default)
                    } else if (dataDocuments.nombre.split(".").last() == "pdf") {
                        binding.ivImageFileToSend.setImageResource(R.drawable.ic_pdf_default)
                    }
                }
                1 ->{
                    binding.llCardFileImage.visibility = View.VISIBLE
                    if (!dataDocuments.base64.isNullOrEmpty()) {
                        try {
                            val file = ToolsSendDocuments.getFileFromBase64(view.context, dataDocuments.base64)
                            if (dataDocuments.nombre.split(".").last() == "jpg" || dataDocuments.nombre.split(".").last() == "jpeg" || dataDocuments.nombre.split(".").last() == "png") {
                                binding.ivImageFile.setVisibility(View.VISIBLE)
                                ToolsSendDocuments.getFileFromBase64(view.context, dataDocuments.base64)
                                    ?.let {
                                        Picasso.get().load(it)
                                            .placeholder(R.drawable.ic_jpg_default)
                                            .error(R.drawable.ic_jpg_default)
                                            .into(binding.ivImageFile)
                                    }
                            } else if (dataDocuments.nombre.split(".").last() == "pdf") {

                                binding.ivImageFile.visibility = View.VISIBLE
                                binding.ivImageFile.setImageResource(R.drawable.ic_pdf_default)

                            }
                        } catch (e: Exception) {
                            Log.e("ERROR", "Error ocurrido en --> " + e.message)
                        }
                    }
                }
                2 ->{
                    binding.llCardAddFile.visibility = View.VISIBLE
                }
                else ->{
                    binding.llCardFileChargue.visibility = View.VISIBLE
                }
            }

            binding.ivReload.setOnClickListener {
                reSendDocument(dataDocuments)
            }

        }

    }

}