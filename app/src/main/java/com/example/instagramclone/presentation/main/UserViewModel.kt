package com.example.instagramclone.presentation.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramclone.domain.model.User
import com.example.instagramclone.domain.use_cases.user_usecase.UserUseCases
import com.example.instagramclone.utils.Response
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val userUseCases: UserUseCases
): ViewModel() {

    private val userid = auth.currentUser?.uid

    private val _getUserData = mutableStateOf<Response<User?>>(Response.Success(null))
    val getUserData: State<Response<User?>> = _getUserData

    private val _setUserData = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val setUserData: State<Response<Boolean>> = _setUserData

    fun getUserInfo() {
        if (userid != null) {
            viewModelScope.launch {
                userUseCases.getUserDetails(userId = userid).collect {
                    _getUserData.value = it
                }
            }
        }
    }

    fun setUserInfo(
        name: String,
        userName: String,
        bio: String,
        webSiteUrl: String
    ) {
        if (userid != null) {
            viewModelScope.launch {
                userUseCases.setUserDetails(
                    userId = userid,
                    name = name,
                    userName = userName,
                    bio = bio,
                    webSiteUrl = webSiteUrl
                ).collect {
                    _setUserData.value = it
                }
            }
        }
    }
}