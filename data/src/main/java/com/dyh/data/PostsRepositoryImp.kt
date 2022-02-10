package com.dyh.data

import com.dyh.domain.entity.Post
import com.dyh.domain.repository.PostsRepository

class PostsRepositoryImp(private val apiService: ApiService) : PostsRepository {

    override suspend fun getPosts(): List<Post> {
        return apiService.getPosts()
    }
}