package com.example.instagramclone.domain.repository

import com.example.instagramclone.domain.model.PostModel
import com.example.instagramclone.utils.Response
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getAllPosts(userId: String): Flow<Response<List<PostModel>>>

    fun uploadPost(
        postImage : String,
        postDesc: String,
        time : Long,
        userId: String,
        userName : String,
        userImage: String
    ): Flow<Response<Boolean>>
}