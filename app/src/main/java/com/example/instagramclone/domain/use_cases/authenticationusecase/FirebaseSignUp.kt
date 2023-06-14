package com.example.instagramclone.domain.use_cases.authenticationusecase

import com.example.instagramclone.domain.repository.AuthRepository
import javax.inject.Inject

class FirebaseSignUp  @Inject constructor(
    private val repository : AuthRepository
    ){
    operator  fun invoke(email: String,password: String,userName:String)
    = repository.firebaseSignUp(email, password,userName)
}