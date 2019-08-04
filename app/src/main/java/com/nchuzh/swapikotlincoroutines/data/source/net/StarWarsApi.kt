package com.nchuzh.swapikotlincoroutines.data.source.net

import android.util.Log
import com.nchuzh.swapikotlincoroutines.network.NetworkClient
import retrofit2.HttpException
import retrofit2.Response

class StarWarsApi {
    val endpoint: StarWarsApiInterface = NetworkClient.retrofit().create(
        StarWarsApiInterface::class.java)

    companion object {
        suspend fun <T : Any> call(call: suspend () -> Response<T>): T? {
            var response: Response<T>? = null
            try {
                response = call.invoke()
            } catch (httpException: HttpException) {
                Log.d("StarWarsApi","HttpException")
            } catch (t: Throwable) {
                Log.d("StarWarsApi","Throwable")
            }

            if (response?.isSuccessful ?: false)
                return response!!.body()
            else
                return null
        }
    }
}