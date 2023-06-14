package com.example.instagramclone.data

import com.example.instagramclone.domain.model.User
import com.example.instagramclone.domain.repository.UserRepository
import com.example.instagramclone.utils.Response
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
): UserRepository {

    private var operationSuccessful  = false

    override fun getUserDetails(userId: String): Flow<Response<User>> = callbackFlow {
        Response.Loading
        val snapShotListener = firebaseFirestore.collection("user")
            .document(userId)
            .addSnapshotListener{ snapShot, error ->
                val response = if (snapShot!=null) {
                    val userInfo = snapShot.toObject(User::class.java)
                    if (userInfo != null) {
                        Response.Success<User>(userInfo)
                    } else {
                        Response.Error("User info is null")
                    }
                }
                else {
                    Response.Error(error?.message?:error.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose{
            snapShotListener.remove()
        }
    }


    override fun setUserDetails(
        userId: String,
        name: String,
        userName: String,
        bio: String,
        webSiteUrl: String
    ): Flow<Response<Boolean>>  = flow{
        operationSuccessful = false
        try {
            val userObj = mutableMapOf<String,String>()
            userObj["name"] = name
            userObj["user Name"] = userName
            userObj["bio"] = bio
            userObj["website url"] = webSiteUrl
           firebaseFirestore.collection("users").document(userId).update(userObj as Map<String, Any>)
               .addOnSuccessListener {

               }.await()
            if (operationSuccessful){
                emit(Response.Success(operationSuccessful))
            }
            else{
                emit(Response.Error("Edit Didn't Success"))
            }
        }
        catch (e: Exception){
            Response.Error(e.localizedMessage?:"An Unexpected Error")
        }
    }
}