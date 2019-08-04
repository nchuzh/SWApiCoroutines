package com.nchuzh.swapikotlincoroutines.data.source.net

import com.nchuzh.swapikotlincoroutines.data.model.Planet
import com.nchuzh.swapikotlincoroutines.data.model.StarWarsCharacter
import com.nchuzh.swapikotlincoroutines.data.model.StarWarsCharacterList
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface StarWarsApiInterface {
    @GET("people")
    fun getCharacterList(): Deferred<Response<StarWarsCharacterList>>

    @GET("people/{id}")
    fun getCharacterById(@Path("id") id: Int): Deferred<Response<StarWarsCharacter>>

    @GET
    fun getCharacterList(@Url url: String): Deferred<Response<StarWarsCharacterList>>

    @GET
    fun getPlanet(@Url url: String): Deferred<Response<Planet>>
}