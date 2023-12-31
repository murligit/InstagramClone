package com.example.instagramclone.domain.repository

import com.example.instagramclone.utils.Response
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun isUserAuthenticatedInFirebase(): Boolean

    fun getFirebaseAuthState (): Flow<Boolean>

    fun firebaseSignIn(email:String,password:String): Flow<Response<Boolean>>

    fun firebaseSignOut(): Flow<Response<Boolean>>

    fun firebaseSignUp(email:String,password:String,userName:String): Flow<Response<Boolean>>
}