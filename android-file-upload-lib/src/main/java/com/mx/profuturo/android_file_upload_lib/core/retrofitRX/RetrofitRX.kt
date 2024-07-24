package com.mx.profuturo.android_file_upload_lib.core.retrofitRX
import android.content.Context
import android.util.Log
import com.facebook.shimmer.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mx.profuturo.android_file_upload_lib.utils.Constants
import io.reactivex.Single
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CountDownLatch

class RetrofitRX {

    private val debug: Boolean = BuildConfig.DEBUG
    val countDownLatch: CountDownLatch = CountDownLatch(1)

    /***
     * Funcion general que recibe las peticiones para WS
     */
    fun <T> excecuteService(endpoint:String,baseURL: String, typeMethod: String, request: Any?, response: Class<T>,context: Context): Single<T>
    {
        return Single.create<String>
        {
            try
            {
                val re = executeRetrofitAPIService(endpoint,baseURL,typeMethod,request,context)
                it.onSuccess(re)
            }
            catch (ex: Exception)
            {
                it.onError(ex)
                Log.e(TAG_LOG, "Error De Comunicación -> $ex")
                throw Exception("Error De Comunicación Con El Servidor, Por Favor Inténtalo De Nuevo Más Tarde -> $it")
            }
        }.map{
            try
            {
                Gson().fromJson(it, response)
            }
            catch (ex: Exception)
            {
                Log.e(TAG_LOG, "Error De Comunicación En Mapeo De JSON-> $ex")
                throw JSONException("Error De Comunicación Con El Servidor, Por Favor Inténtalo De Nuevo Más Tarde -> $ex $it")
            }
        }
    }

    @Throws(Exception::class)
    private fun executeRetrofitAPIService(endpoint:String,urlBase: String, typeMethod:String,request: Any?,context: Context):String
    {

        Log.d(TAG_LOG, "<------------------------------>\n")
        Log.d(TAG_LOG, "Peticion al Servicio --> $urlBase")
        Log.d(TAG_LOG, "<------------------------------>\n")
        Log.d(TAG_LOG, "Tipo de petición: $typeMethod")
        Log.d(TAG_LOG, "<------------------------------>\n")
        if(debug) Log.d(TAG_LOG, "Request: $endpoint -> ${getJsonString(request)}")
        Log.d(TAG_LOG, "<------------------------------>\n")
        try
        {

            return when (typeMethod)
            {
                Constants.TYPE_POST_SERVICE -> executePOSTService(urlBase ,endpoint, request)

                Constants.TYPE_GET_SERVICE -> executeGETService(urlBase, endpoint)

                else -> ""
            }
        }
        catch (e: Exception)
        {
            if(debug) Log.d(TAG_LOG, "Error De Comunicación -> $e")
            throw Exception("Error De Comunicación Con El Servidor -> $e")
        }
    }


    /***
     * Función encargada de las peticiones tipo GET
     * */
    private fun executeGETService(baseURL: String, endpoint: String):String
    {
        var retroResponse=""
        RetrofitManager.getAPIService(baseURL)!!.getExcecuteService(endpoint)!!.enqueue(object :
            Callback<Any?>
        {
            override fun onResponse(call: Call<Any?>, response: Response<Any?>)
            {
                if(response.isSuccessful)
                {
                    val retrofitResponse = response.body()
                    retroResponse = getJsonString(retrofitResponse)
                    countDownLatch.countDown()
                }
                else
                {
                    Log.d(TAG_LOG, "Error De Comunicación Con El Servidor -> "+response.errorBody())
                    countDownLatch.countDown()
                }
            }

            override fun onFailure(call: Call<Any?>, throwable: Throwable)
            {
                Log.e(TAG_LOG, "Error De Comunicación -> $throwable")
                countDownLatch.countDown()
            }
        })

        try
        {
            countDownLatch.await()
            Log.d(TAG_LOG, "Response De Método $endpoint -> $retroResponse")
            return retroResponse
        }
        catch (ex: InterruptedException)
        {
            Log.e(TAG_LOG, "Error De Comunicación -> $ex")
            throw RuntimeException("Error De Comunicación Con El Servidor, Por Favor Inténtalo De Nuevo Más Tarde -> $ex")
        }
    }

    /***
     * Función encargada de las peticiones tipo POST
     * */
    private fun executePOSTService(baseURL: String, endpoint: String, requestBean: Any?):String
    {
        var retroResponse=""
        RetrofitManager.getAPIService(baseURL)!!.postExcecuteService(endpoint, requestBean)!!.enqueue(
            object : Callback<Any?>
            {
                override fun onResponse(call: Call<Any?>, response: Response<Any?>)
                {
                    if(response.isSuccessful)
                    {
                        val retrofitResponse = response.body()
                        retroResponse = getJsonString(retrofitResponse)
                        countDownLatch.countDown()
                    }
                    else
                    {
                        Log.e(TAG_LOG, "Error De Comunicación, Response-> "+response.toString())
                        countDownLatch.countDown()
                        retroResponse = response.code().toString()
                    }
                }

                override fun onFailure(call: Call<Any?>, throwable: Throwable)
                {
                    Log.e(TAG_LOG, "Error De Comunicación Con El Servidor throwable -> $throwable")
                    countDownLatch.countDown()
                }
            })

        return try
        {
            countDownLatch.await()
            Log.d(TAG_LOG, "Response De Método $endpoint -> $retroResponse")
            retroResponse
        }
        catch (ex: Exception)
        {
            Log.e(TAG_LOG, "Error De Comunicación -> $ex")
            retroResponse
        }
    }

    private fun getJsonString(bean: Any?):String
    {
        return GsonBuilder()
            .serializeNulls()
            .create()
            .toJson(bean)
    }
    companion object
    {
        val TAG_LOG: String = RetrofitRX::class.java.simpleName
    }

}