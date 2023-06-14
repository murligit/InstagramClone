package com.example.instagramclone.domain.use_cases.user_usecase

import com.example.instagramclone.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetails @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(userId : String) = repository.getUserDetails(userId = userId)
}