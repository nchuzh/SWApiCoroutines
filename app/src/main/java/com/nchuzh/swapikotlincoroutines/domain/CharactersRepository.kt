package com.nchuzh.swapikotlincoroutines.domain

import com.nchuzh.swapikotlincoroutines.data.model.StarWarsCharacter
import com.nchuzh.swapikotlincoroutines.data.source.SwApi
import com.nchuzh.swapikotlincoroutines.data.source.SwApi.Companion.call
import com.nchuzh.swapikotlincoroutines.data.source.SwApiInterface

class CharactersRepository {
    private val swApiInterface: SwApiInterface = SwApi().endpoint

    suspend fun getCharacterList() : List<StarWarsCharacter>? {
        val response = call(
            call = {swApiInterface.getCharacterList().await()},
            errorMessage = "Error"
        )

        return response?.characters
    }
}