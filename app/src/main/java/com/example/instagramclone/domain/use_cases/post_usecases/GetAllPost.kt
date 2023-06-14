package com.example.instagramclone.domain.use_cases.post_usecases

import com.example.instagramclone.domain.repository.PostRepository
import javax.inject.Inject

class GetAllPost @Inject constructor(
    private val repository: PostRepository
) {
    operator fun invoke(userId : String) = repository.getAllPosts(userId = userId)
}