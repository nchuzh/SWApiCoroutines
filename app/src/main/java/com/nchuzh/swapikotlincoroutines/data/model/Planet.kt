package com.nchuzh.swapikotlincoroutines.data.model

import com.google.gson.annotations.SerializedName

data class Planet(@SerializedName("name") val name: String,
                  @SerializedName("climate") val climate: String)