package com.nchuzh.swapikotlincoroutines.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieCharacter(val name: String,
                          val birthDate: String,
                          val gender: String,
                          val planetUrl: String): Parcelable