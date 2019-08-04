package com.nchuzh.swapikotlincoroutines.view.characterlist

import com.nchuzh.swapikotlincoroutines.domain.model.CharacterDetails
import com.nchuzh.swapikotlincoroutines.domain.model.MovieCharacter

interface CharacterListView {
    fun showProgress()
    fun setList(list: List<MovieCharacter>?)
    fun hideProgress()
    fun showDetailsProgress()
    fun hideDetailsProgress()
    fun openDetails(details: CharacterDetails)
    fun showError()
}