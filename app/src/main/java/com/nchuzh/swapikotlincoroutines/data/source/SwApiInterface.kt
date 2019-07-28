package com.nchuzh.swapikotlincoroutines.data.source

import com.nchuzh.swapikotlincoroutines.data.model.StarWarsCharacter
import com.nchuzh.swapikotlincoroutines.data.model.StarWarsCharacterList
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SwApiInterface {
    @GET("people")
    fun getCharacterList(): Deferred<Response<StarWarsCharacterList>>

    @GET("people/{id}")
    fun getCharacterById(@Path("id") id: Int): Deferred<Response<StarWarsCharacter>>
}