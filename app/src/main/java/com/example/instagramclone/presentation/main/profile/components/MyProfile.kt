package com.example.instagramclone.presentation.main.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyProfile(
    displayName:String,
    bio: String,
    url:String
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(text = displayName, fontWeight = FontWeight.Bold, lineHeight = 20.sp)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = bio,  lineHeight = 20.sp)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = url, color = Color(0xFF3D3D91), lineHeight = 20.sp,
            maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}