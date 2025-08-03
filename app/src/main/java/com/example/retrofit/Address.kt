package com.example.retrofit

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("country") val mCountry: String,
    @SerializedName("city") val mCity: String,
)