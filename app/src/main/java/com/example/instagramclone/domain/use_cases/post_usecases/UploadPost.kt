package com.example.instagramclone.domain.use_cases.post_usecases

import com.example.instagramclone.domain.repository.PostRepository
import javax.inject.Inject

class UploadPost @Inject constructor(
    private val repository: PostRepository
) {
    operator fun invoke(
        postImage : String,
        postDesc: String,
        time : Long,
        userId: String,
        userName : String,
        userImage: String
    ) = repository.uploadPost(
        postImage = postImage,
        postDesc = postDesc,
        time = time,
        userName = userName,
        userId = userId,
        userImage = userImage
    )
}