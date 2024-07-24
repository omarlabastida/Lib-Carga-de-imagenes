package com.mx.profuturo.android_file_upload_lib

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mx.profuturo.android_file_upload_lib.databinding.ActivityMainBinding
import com.mx.profuturo.android_file_upload_lib.models.dataModels.documentsList.RequestDocuments
import com.mx.profuturo.android_file_upload_lib.models.dataModels.documentsList.RqtDocuments
import com.mx.profuturo.android_file_upload_lib.ui.fileUpload.view.SendDocumentsLib
import com.mx.profuturo.android_file_upload_lib.untils.Tools
import com.mx.profuturo.android_file_upload_lib.utils.Constants

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.btStartLibrary.setOnClickListener {
            initLibrary()
        }
    }

    private fun initLibrary() {

        var listDocuments = listOf(
            RqtDocuments(
                base64 = Tools.BASE_64,
                nombre = "1318_identificacion_anverso_LARE090909HDFBDM09_11644991_1.jpg"
            ),
            RqtDocuments(
                base64 = Tools.BASE_64,
                nombre = "1319_identificacion_reverso_LARE090909HDFBDM09_11644991_1.jpg"
            ),
            RqtDocuments(
                base64 = Tools.BASE_64,
                nombre = "0001_solicitud_de_traspaso_LARE090909HDFBDM09_11644991_1.jpg"
            ),
            RqtDocuments(
                base64 = Tools.BASE_64,
                nombre = "0033_contrato_de_administradora_LARE090909HDFBDM09_11644991_2.jpg"
            ),
            RqtDocuments(
                base64 = Tools.BASE_64,
                nombre = "0033_contrato_de_administradora_LARE090909HDFBDM09_11644991_3.jpg"
            ),
            RqtDocuments(
                base64 = Tools.BASE_64,
                nombre = "0033_contrato_de_administradora_LARE090909HDFBDM09_11644991_1.jpg"
            ),
        )

        var documentsSend = arrayListOf(RequestDocuments(
            listDocuments
        )
        )




        val intent3 = Intent(this@MainActivity, SendDocumentsLib::class.java)

        //intent3.putParcelableArrayListExtra("objListDocuments", documentsSend)
        intent3.putExtra("idOrigin", "9560")
        intent3.putExtra("idFormality", "11644104")
        intent3.putExtra("idProcess", 11251500)
        intent3.putExtra("totalDocuments", 6)
        startActivity(intent3)
    }
}