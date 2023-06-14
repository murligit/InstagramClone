package com.example.instagramclone.domain.model

data class PostModel(
    var postId: String= "",
    var postImage : String ="",
    var postDesc : String = "",
    var time : Long? = null,
    var userId : String = "",
    var userImage : String = "",
    var userName : String = ""
)
