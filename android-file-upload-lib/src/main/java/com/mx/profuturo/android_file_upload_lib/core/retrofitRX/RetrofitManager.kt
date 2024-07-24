package com.mx.profuturo.android_file_upload_lib.core.retrofitRX
import android.util.Log
import com.mx.profuturo.android_file_upload_lib.core.profuturo.ProfuturoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class RetrofitManager {

    companion object{
        private var retrofit: Retrofit? = null
        private var TAG = RetrofitManager::class.java.simpleName

        private fun getRetrofitParams(): OkHttpClient {


            return OkHttpClient.Builder()
                .callTimeout(180, TimeUnit.SECONDS)
                .readTimeout(180, TimeUnit.SECONDS)
                .connectTimeout(180, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .writeTimeout(180, TimeUnit.SECONDS)
                .addInterceptor(ProfuturoInterceptor("uS7c0xksp", "dp7sibek**wnx"))
                .build()
        }

        fun getAPIService(url: String): RetrofitServices?
        {
            retrofit.let {
                Log.e(TAG, "URL Base --> $url")
                retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .client(getRetrofitParams())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(RetrofitServices::class.java)
        }

    }

}