package com.mx.profuturo.android_file_upload_lib.ui.fileUpload.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import com.google.gson.Gson
import com.mx.profuturo.android_file_upload_lib.R
import com.mx.profuturo.android_file_upload_lib.app.KoinInitializer
import com.mx.profuturo.android_file_upload_lib.databinding.ActivityLibSendDocumentsBinding
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaArchivos.RqtUpload
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaArchivos.UploadFileRequest
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaListaDeArchivos.AddFileInformationRequest
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaListaDeArchivos.DocumentoNombre
import com.mx.profuturo.android_file_upload_lib.models.dataModels.cargaListaDeArchivos.Rqt
import com.mx.profuturo.android_file_upload_lib.models.dataModels.documentsList.RequestDocuments
import com.mx.profuturo.android_file_upload_lib.models.dataModels.envioDeTramiteFinal.SendFinalFormalityRequest
import com.mx.profuturo.android_file_upload_lib.ui.fileUpload.viewModel.SendDocumentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SendDocumentsLib : AppCompatActivity() {

    private lateinit var binding: ActivityLibSendDocumentsBinding
    private lateinit var bundleParams: Bundle
    var init = KoinInitializer.init(this@SendDocumentsLib)
    val sendDocumentsViewModel by viewModel<SendDocumentsViewModel>()


    private var objListDocuments = listOf<RequestDocuments>()
    private var objListMinorFormality = listOf<SendFinalFormalityRequest>()
    private var idOrigin = ""
    private var idFormality = ""
    private var idProcess = 0
    private var totalDocuments = 0
    private var listDocumentsNameMutable = mutableListOf<List<DocumentoNombre>>()
    private var listDocumentsB54Mutable = mutableListOf<List<UploadFileRequest>>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLibSendDocumentsBinding.inflate(layoutInflater)
        bundleParams = this.intent.extras as Bundle
        setContentView(binding.root)
        setInitialValues()
    }

    private fun setInitialValues() {
        getBundleData()
    }

    private fun getBundleData() {
        //TODO La lista objListDocuments es la lista en la que originalmente se recibian los base64 a travez del bundle
        objListDocuments = bundleParams.getParcelableArrayList("objListDocuments")?: listOf<RequestDocuments>()
        objListMinorFormality = bundleParams.getParcelableArrayList("objListMinorFormality")?: listOf<SendFinalFormalityRequest>()
        idOrigin = bundleParams.getString("idOrigin")?:""
        idFormality = bundleParams.getString("idFormality")?:""
        idProcess = bundleParams.getInt("idProcess")?:0
        totalDocuments =bundleParams.getInt("totalDocuments")?:0
        createRequest(objListDocuments, objListMinorFormality)
        setNavController()
    }

    private fun createRequest(
        listDocuments: List<RequestDocuments>?,
        objListMinorFormality: List<SendFinalFormalityRequest>
    ) {
        listDocumentsNameMutable.clear()
        listDocumentsB54Mutable.clear()
        var listDocumentsName = mutableListOf<DocumentoNombre>()
        var listDocumentsB54 = mutableListOf<UploadFileRequest>()

        Log.e("RECIBIDOS", "$listDocuments")

        listDocuments?.forEach { rqtDocuments ->
            listDocumentsName.clear()
            listDocumentsB54.clear()
            rqtDocuments.rqt?.forEach {
                listDocumentsName.add(DocumentoNombre(it.nombre))
                listDocumentsB54.add(UploadFileRequest(RqtUpload(it.base64, idProcess, idFormality, it.nombre?:"", 0)))
            }
            listDocumentsNameMutable.add(listDocumentsName)
            listDocumentsB54Mutable.add(listDocumentsB54)
        }

        var listAddFileMutable = mutableListOf<AddFileInformationRequest>()

        listDocumentsNameMutable.forEach { listDocuments ->
            listAddFileMutable.add(
                AddFileInformationRequest(
                    rqt = Rqt(
                        documentos = listDocuments,
                        idProceso = idProcess.toString(),
                        idTramite = idFormality,
                        total = totalDocuments
                    )
                )
            )
        }

        sendDocumentsViewModel.AddFileInformationRequest = listAddFileMutable

        sendDocumentsViewModel.UploadFileRequest = listDocumentsB54Mutable

        sendDocumentsViewModel.sendFinalFormalityRequest = objListMinorFormality
    }


    private fun setBundleData(): Bundle {
        val bundleData = Bundle()
        return bundleData
    }

    private fun setNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.ngSendDocuments) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph_send_documents)
        navController.setGraph(navGraph, setBundleData())
    }

    private fun setObservers(){
        /*sendDocumentsViewModel.toolTitle.observe(this){title ->
            binding.toolBar.tvTitleSection.text = title.first
            binding.toolBar.tvSubTitle.visibility =  if (title.second.isEmpty()) View.GONE else View.VISIBLE
            binding.toolBar.tvSubTitle.text = title.second
            binding.toolBar.tvTitleSection.textSize = if(title.second.isEmpty())  26.0F else 22.0F
        }

        sendDocumentsViewModel.backPressed.observe(this){backPressed ->
            if(backPressed) super.onBackPressed()
        }

        sendDocumentsViewModel.loading.observe(this){
            if(it.first){
                Log.d(TAG_LOG, "Iniciando Loading")
                if(it.second == "Enviando tramite de menor"){
                    binding.ivLoader.setImageResource(R.drawable.ic_send_formality)
                }else{
                    binding.ivLoader.setImageResource(R.drawable.ic_send_file)
                }
                binding.tvLoading.text = it.second
                binding.llLoaderSendDocuments.visibility = View.VISIBLE
            }else{
                Log.d(TAG_LOG, "Cerrando Loading")
                binding.llLoaderSendDocuments.visibility = View.GONE
            }

        }*/
    }

    fun getJsonObject(objListDocuments: String): RequestDocuments? {
        val gson = Gson()
        return gson.fromJson(objListDocuments, RequestDocuments::class.java)
    }

}