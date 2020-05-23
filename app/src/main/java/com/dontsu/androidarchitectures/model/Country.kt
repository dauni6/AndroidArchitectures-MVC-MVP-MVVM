package com.dontsu.androidarchitectures.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val country: String
)