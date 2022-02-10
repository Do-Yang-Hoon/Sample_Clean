package com.dyh.domain.repository

import com.dyh.domain.entity.Post

interface PostsRepository {

    suspend fun getPosts(): List<Post>
}