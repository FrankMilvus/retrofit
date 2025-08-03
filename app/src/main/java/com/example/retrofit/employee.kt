package com.example.retrofit

import com.google.gson.annotations.SerializedName

data class Employee(
    @SerializedName ("first_name") val mFirstName: String,
    @SerializedName ("age") val mAge: Int,
    @SerializedName ("mail") val mMail: String,
    @SerializedName ("address") val mAddress: Address,
){

}