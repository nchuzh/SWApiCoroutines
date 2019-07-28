package com.nchuzh.swapikotlincoroutines.data.source

import com.nchuzh.swapikotlincoroutines.network.NetworkClient
import retrofit2.Response

class SwApi {
    val endpoint: SwApiInterface = NetworkClient.retrofit().create(SwApiInterface::class.java)

    companion object {
        suspend fun <T : Any> call(call: suspend () -> Response<T>, errorMessage: String): T? {
            val response = call.invoke()
            if (response.isSuccessful)
                return response.body()
            else
                return null
        }
    }
}