package com.nchuzh.swapikotlincoroutines.data.model

import com.google.gson.annotations.SerializedName

data class StarWarsCharacterList(@SerializedName("results") val results: List<StarWarsCharacter>?,
                                 @SerializedName("next") val nextUrl: String?)