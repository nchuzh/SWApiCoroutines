package com.nchuzh.swapikotlincoroutines.domain.repository

import com.nchuzh.swapikotlincoroutines.data.source.net.StarWarsApi
import com.nchuzh.swapikotlincoroutines.data.source.net.StarWarsApi.Companion.call
import com.nchuzh.swapikotlincoroutines.data.source.net.StarWarsApiInterface
import com.nchuzh.swapikotlincoroutines.domain.model.MovieCharacter

class CharactersRepository {
    private val swApiInterface: StarWarsApiInterface = StarWarsApi()
        .endpoint
    private var nextUrl: String? = null
    private var initial = true

    suspend fun getCharacterList() : List<MovieCharacter>? {
        val response = if (initial) {
            initial = false
            call { swApiInterface.getCharacterList().await() }
        }
        else
            call{ swApiInterface.getCharacterList(nextUrl!!).await() }

        nextUrl = response?.nextUrl

        return response?.results?.map { MovieCharacter(it.name, it.birthDate, it.gender, it.planetUrl) }
    }

    suspend fun getPlanet(planetUrl: String): String? {
        val response = call{ swApiInterface.getPlanet(planetUrl).await() }

        return response?.name
    }
}