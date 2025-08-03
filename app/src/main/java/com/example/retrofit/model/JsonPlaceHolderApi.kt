package com.example.retrofit.model

import retrofit2.Call
import retrofit2.http.*

interface JsonPlaceHolderApi {

    @GET("posts")
    fun getPosts(
        @Query("userId") userId: List<Int>,
//        @Query("userId2") userId2: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String
    ): Call<MutableList<Post>>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId: Int): Call<MutableList<Comment>>

}