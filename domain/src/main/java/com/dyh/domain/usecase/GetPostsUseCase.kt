package com.dyh.domain.usecase

import com.dyh.domain.entity.Post
import com.dyh.domain.repository.PostsRepository

class GetPostsUseCase constructor(
    private val postsRepository: PostsRepository
) : UseCase<List<Post>, Any?>() {

    override suspend fun run(params: Any?): List<Post> {
        return postsRepository.getPosts()
    }

}