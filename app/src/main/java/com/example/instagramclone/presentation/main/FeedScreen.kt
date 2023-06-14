package com.example.instagramclone.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.instagramclone.presentation.BottomNavigationItem
import com.example.instagramclone.presentation.BottomNavigationMenu
import com.example.instagramclone.presentation.Toast
import com.example.instagramclone.presentation.main.post.PostViewModel
import com.example.instagramclone.R
import com.example.instagramclone.domain.model.PostModel
import com.example.instagramclone.utils.Response

@Composable
fun FeedScreen(navController: NavController) {

    val postViewModel: PostViewModel = hiltViewModel()
    postViewModel.getAllPost("")
    when (val response = postViewModel.postData.value) {
        is Response.Loading -> {
            CircularProgressIndicator()
        }
        is Response.Success -> {

            val obj = response.data
            Scaffold(topBar = {
                TopAppBar(title = { Text(text = "Instagram",) },
                    backgroundColor = MaterialTheme.colors.surface,
                    elevation = 10.dp,
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(id = R.drawable.instagram_clone),
                                contentDescription = "logo"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(
                                painter = painterResource(id = R.drawable.message),
                                contentDescription = "Message",
                                modifier = Modifier.size(30.dp),
                                tint = Color.Black
                            )
                        }
                    }
                )
            }, content = {
           InstagramHomeContent(obj)
            }, bottomBar = {
                BottomNavigationMenu(
                    selectedItem = BottomNavigationItem.FEED,
                    navController = navController
                )
            }
            )
        }
        is Response.Error -> {
            Toast(message = response.message)
        }
    }
}

@Composable
fun InstagramHomeContent(postList: List<PostModel>) {
   LazyColumn {
       items(postList, itemContent = {
           InstagramListContent(it)
       })
   }
}

@Composable
fun InstagramListContent(it: PostModel) {
   Column {
       ProfileInfoSection(it)
       PostImage(it)
       PostDesc(it)
   }
}

@Composable
fun PostDesc(it: PostModel) {
    Text(
        text = it.postDesc,
        style = MaterialTheme.typography.body1,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(30.dp)
    )
}

@Composable
fun PostImage(it: PostModel) {

    Icon(rememberImagePainter(
        data = it.postImage),
        contentDescription = null,
     modifier = Modifier
         .fillMaxWidth()
         .height(450.dp)
    )
}

@Composable
fun ProfileInfoSection(it: PostModel) {
    Row(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically
        ) {
        Icon(rememberImagePainter(data = it.userImage),
            contentDescription = "profileImage", tint = Color.Unspecified )
        Text(
            text = it.userName,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
            textAlign = TextAlign.Left
        )
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "null")
    }
}
