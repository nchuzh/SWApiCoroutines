package com.nchuzh.swapikotlincoroutines.data.model

import com.google.gson.annotations.SerializedName

data class StarWarsCharacter(@SerializedName("name") val name: String,
                             @SerializedName("url") val url: String,
                             @SerializedName("birth_year") val birthDate: String,
                             @SerializedName("gender") val gender: String,
                             @SerializedName("homeworld") val planetUrl: String)