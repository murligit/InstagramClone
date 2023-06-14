package com.example.instagramclone.domain.use_cases.user_usecase

import com.example.instagramclone.domain.repository.UserRepository
import javax.inject.Inject

class SetUserDetails @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(
        userId: String,
        name: String,
        userName: String,
        bio:  String,
        webSiteUrl: String
    ) = repository.setUserDetails(
        userId = userId,
        name = name,
      userName = userName,
        bio = bio,
        webSiteUrl = webSiteUrl
    )
}
