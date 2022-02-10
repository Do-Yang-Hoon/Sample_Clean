package com.dyh.data

import com.dyh.domain.entity.Post
import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    suspend fun getPosts(): List<Post>
}