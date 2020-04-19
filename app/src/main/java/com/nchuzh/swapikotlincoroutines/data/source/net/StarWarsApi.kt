package com.nchuzh.swapikotlincoroutines.data.source.net

import android.util.Log
import com.nchuzh.swapikotlincoroutines.data.model.Fail
import com.nchuzh.swapikotlincoroutines.data.model.NetworkResult
import com.nchuzh.swapikotlincoroutines.data.model.Success
import com.nchuzh.swapikotlincoroutines.network.NetworkClient
import retrofit2.Response

class StarWarsApi {
    val endpoint: StarWarsApiInterface =
        NetworkClient.retrofit().create(StarWarsApiInterface::class.java)

    companion object {
        suspend fun <T : Any> call(call: suspend () -> Response<T>): NetworkResult<T> {
            val response: Response<T>?
            return try {
                response = call.invoke()
                if (response.isSuccessful && response.body() != null)
                    Success(response.body()!!)
                else
                    Fail(Throwable("Server returned null body"))
            } catch (throwable: Throwable) {
                Log.d("StarWarsApi", throwable.message ?: "something wrong")
                return Fail(throwable)
            }
        }
    }
}