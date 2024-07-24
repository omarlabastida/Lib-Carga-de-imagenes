package com.mx.profuturo.android_file_upload_lib.core.retrofitRX

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Url

interface RetrofitServices {
    @GET
    fun getExcecuteService(
        @Url endpoint:String
    ): Call<Any?>?

    @POST
    fun postExcecuteService(
        @Url endpoint:String,
        @Body getRequestBeanObject: Any?
    ): Call<Any?>?

}