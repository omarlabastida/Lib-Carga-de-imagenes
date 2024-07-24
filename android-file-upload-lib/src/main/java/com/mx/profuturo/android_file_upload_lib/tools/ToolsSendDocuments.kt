package com.mx.profuturo.android_file_upload_lib.tools

import android.content.Context
import android.util.Base64
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class ToolsSendDocuments {

    companion object{

        @Throws(IOException::class)
        fun getFileFromBase64(context: Context, imageData: String?): File? {
            if (imageData == null) {
                return null
            }
            val imgBytesData = Base64.decode(imageData, Base64.DEFAULT)
            val file = File.createTempFile("image", null, context.cacheDir.canonicalFile)
            val canonicalPath = file.canonicalPath
            if (!canonicalPath.contains("/data/data")) {
                // Invalid file; handle error
                return null
            }
            val fileOutputStream: FileOutputStream
            fileOutputStream = try {
                FileOutputStream(file)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                return null
            }
            val bufferedOutputStream = BufferedOutputStream(fileOutputStream)
            try {
                bufferedOutputStream.write(imgBytesData)
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            } finally {
                try {
                    bufferedOutputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            return file
        }
    }


}


