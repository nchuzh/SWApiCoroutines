package com.nchuzh.swapikotlincoroutines.domain.repository

import android.util.Log
import com.nchuzh.swapikotlincoroutines.data.model.Fail
import com.nchuzh.swapikotlincoroutines.data.model.Planet
import com.nchuzh.swapikotlincoroutines.data.model.StarWarsCharacterList
import com.nchuzh.swapikotlincoroutines.data.model.Success
import com.nchuzh.swapikotlincoroutines.data.source.net.StarWarsApi
import com.nchuzh.swapikotlincoroutines.data.source.net.StarWarsApi.Companion.call
import com.nchuzh.swapikotlincoroutines.data.source.net.StarWarsApiInterface
import com.nchuzh.swapikotlincoroutines.domain.model.MovieCharacter

class CharactersRepository {
    private val swApiInterface: StarWarsApiInterface = StarWarsApi()
        .endpoint
    private var nextUrl: String? = null
    private var initial = true

    suspend fun getCharacterList(): List<MovieCharacter>? {
        val response = if (initial) {
            initial = false
            call { swApiInterface.getCharacterList().await() }
        } else {
            call { swApiInterface.getCharacterList(nextUrl!!).await() }
        }

        val list: StarWarsCharacterList? = when (response) {
            is Success<StarWarsCharacterList> -> response.data
            is Fail<*> -> {
                Log.d("getCharacterList", "failed " + response.exception.message)
                null
            }
        }

        nextUrl = list?.nextUrl

        return list?.results?.map {
            MovieCharacter(
                it.name,
                it.birthDate,
                it.gender,
                it.planetUrl
            )
        }
    }

    suspend fun getPlanet(planetUrl: String): String? {
        val response = call { swApiInterface.getPlanet(planetUrl).await() }
        val data: Planet? = when (response) {
            is Success<Planet> -> response.data
            is Fail<*> -> {
                Log.d("getPlanet", "failed " + response.exception.message)
                null
            }
        }

        return data?.name
    }
}