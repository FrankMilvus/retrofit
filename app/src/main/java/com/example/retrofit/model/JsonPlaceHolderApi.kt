package com.example.retrofit.model

import retrofit2.Call
import retrofit2.http.*

interface JsonPlaceHolderApi {

    @GET("posts")
    fun getPosts(@Query("userId") userId: Int): Call<MutableList<Post>>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id")postId: Int): Call<MutableList<Comment>>

}