package com.nchuzh.swapikotlincoroutines.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterDetails(val essentials: MovieCharacter,
                            val planet: String): Parcelable