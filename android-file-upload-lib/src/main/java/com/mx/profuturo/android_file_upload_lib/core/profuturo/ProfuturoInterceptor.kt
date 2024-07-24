package com.mx.profuturo.android_file_upload_lib.core.profuturo

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class ProfuturoInterceptor(user: String, password: String) : Interceptor {

    private val credentials: String = Credentials.basic(user, password)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
            .header("Authorization", credentials)
            .build()
        return chain.proceed(authenticatedRequest)
    }
}
