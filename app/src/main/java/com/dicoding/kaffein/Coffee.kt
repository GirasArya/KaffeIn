package com.dicoding.kaffein

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coffee(
    val name : String,
    val description : String,
    val photo : String,
    val country : String,
    val ingredients : String
):Parcelable
