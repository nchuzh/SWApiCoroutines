package com.nchuzh.swapikotlincoroutines.domain.repository

import android.util.Log
import com.nchuzh.swapikotlincoroutines.data.model.Planet
import com.nchuzh.swapikotlincoroutines.data.model.StarWarsCharacterList
import com.nchuzh.swapikotlincoroutines.data.source.net.StarWarsApi
import com.nchuzh.swapikotlincoroutines.data.source.net.StarWarsApi.Companion.call
import com.nchuzh.swapikotlincoroutines.data.source.net.StarWarsApiInterface
import com.nchuzh.swapikotlincoroutines.domain.model.MovieCharacter
import retrofit2.Response
import java.lang.Exception

class CharactersRepository {
    private val swApiInterface: StarWarsApiInterface = StarWarsApi()
        .endpoint
    private var nextUrl: String? = null
    private var initial = true

    suspend fun getCharacterList(): List<MovieCharacter>? {
        var response: StarWarsCharacterList? = null
        try {
            response = if (initial) {
                initial = false
                call { swApiInterface.getCharacterList().await() }
            } else
                call { swApiInterface.getCharacterList(nextUrl!!).await() }
        } catch (e: Exception) {
            Log.d("getCharacterList", "failed " + e.message)
        }

        nextUrl = response?.nextUrl

        return response?.results?.map { MovieCharacter(it.name, it.birthDate, it.gender, it.planetUrl) }
    }

    suspend fun getPlanet(planetUrl: String): String? {
        var response: Planet? = null
        try {
            response = call { swApiInterface.getPlanet(planetUrl).await() }
        } catch (e: Exception) {
            Log.d("getPlanet", "failed " + e.message)
        }

        return response?.name
    }
}