package com.nchuzh.swapikotlincoroutines.view.characterlist

import com.nchuzh.swapikotlincoroutines.domain.model.CharacterDetails
import com.nchuzh.swapikotlincoroutines.domain.model.MovieCharacter
import com.nchuzh.swapikotlincoroutines.domain.repository.CharactersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterListPresenter(private val view: CharacterListView) {
    private val repository: CharactersRepository = CharactersRepository()

    fun fetch() {
        view.showProgress()
        CoroutineScope(Dispatchers.IO).launch {
            val list = repository.getCharacterList()
            list?.let {
                withContext(Dispatchers.Main) {
                    view.setList(list)
                    view.hideProgress()
                }
            } ?: view.showError()
        }
    }

    fun getDetails(character: MovieCharacter) {
        view.showDetailsProgress()
        CoroutineScope(Dispatchers.IO).launch {
            val planet = repository.getPlanet(character.planetUrl)
            planet?.let {
                withContext(Dispatchers.Main) {
                    view.hideDetailsProgress()
                    val details = CharacterDetails(character, planet)
                    view.openDetails(details)
                }
            } ?: view.showError()
        }
    }
}