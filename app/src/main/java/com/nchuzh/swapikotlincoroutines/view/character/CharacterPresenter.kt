package com.nchuzh.swapikotlincoroutines.view.character

import com.nchuzh.swapikotlincoroutines.domain.model.CharacterDetails

class CharacterPresenter(private val view: CharacterView) {
    fun setData(data: CharacterDetails?) {
        data?.let {
            view.showDetails(it)
        }
    }
}