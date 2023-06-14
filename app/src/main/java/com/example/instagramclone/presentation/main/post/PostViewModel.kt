package com.example.instagramclone.presentation.main.post

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramclone.domain.model.PostModel
import com.example.instagramclone.domain.use_cases.post_usecases.PostUseCases
import com.example.instagramclone.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postUseCases: PostUseCases
): ViewModel() {

    private val _postData = mutableStateOf<Response<List<PostModel>>>(Response.Loading)
    val postData : State<Response<List<PostModel>>> =_postData

    private val _uploadPostData = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val uploadPostData : State<Response<Boolean>> = _uploadPostData

    fun getAllPost(userId : String) {
        viewModelScope.launch {
            postUseCases.getAllPost(userId = userId).collect{
                _postData.value = it
            }
        }
    }

    fun uploadPost(
        postImage : String,
        postDesc: String,
        time : Long,
        userId: String,
        userName : String,
        userImage: String
    ){
        viewModelScope.launch {
            postUseCases.uploadPost(
                postImage = postImage,
                postDesc = postDesc,
                time = time,
                userName = userName,
                userId = userId,
                userImage = userImage
            ).collect{
                _uploadPostData.value = it
            }
        }
    }

}