package com.example.instagramclone.data

import com.example.instagramclone.domain.model.PostModel
import com.example.instagramclone.domain.repository.PostRepository
import com.example.instagramclone.utils.Response
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
): PostRepository {

    private var operationSuccessful  = false

    override fun getAllPosts(userId: String): Flow<Response<List<PostModel>>> = callbackFlow {
        Response.Loading
        val snapShotListener = firebaseFirestore.collection("Posts")
            .whereNotEqualTo("userId",userId)
            .addSnapshotListener{ snapshot, e->
                val response = if(snapshot!=null){
                    val postsList = snapshot.toObjects(PostModel::class.java)
                    Response.Success<List<PostModel>>(postsList)
                }
                else {
                    Response.Error(e?.message?:e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapShotListener.remove()
        }
    }

    override fun uploadPost(
        postImage: String,
        postDesc: String,
        time: Long,
        userId: String,
        userName: String,
        userImage: String
    ): Flow<Response<Boolean>>  = flow{
        operationSuccessful = false
        try {
            val postId = firebaseFirestore.collection("Posts").document().id
            val post = PostModel(
                postImage = postImage,
                postDesc = postDesc,
                postId = postId,
                time = time,
                userName = userName,
                userId = userId,
                userImage = userImage
            )
            firebaseFirestore.collection("Posts").document(postId).set(post)
                .addOnSuccessListener {
                    operationSuccessful = true
                }.await()
            if (operationSuccessful) {
                emit(Response.Success(operationSuccessful))
            }
            else {
                emit(Response.Error("Post Upload Unsuccesful"))
            }
        }
        catch (e:Exception){
            emit(Response.Error(e.localizedMessage?:"An Unexpected Error"))
        }
    }
}