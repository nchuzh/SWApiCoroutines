package com.nchuzh.swapikotlincoroutines.view.character

import com.nchuzh.swapikotlincoroutines.domain.model.CharacterDetails

interface CharacterView {
    fun showDetails(details: CharacterDetails)
}