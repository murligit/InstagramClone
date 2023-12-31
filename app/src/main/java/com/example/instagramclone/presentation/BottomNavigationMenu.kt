package com.example.instagramclone.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.instagramclone.utils.Screens


enum class BottomNavigationItem (val icon : ImageVector,val route : Screens){
    FEED(Icons.Default.Home,Screens.FeedsScreen),
    SEARCH(Icons.Default.Search,Screens.SearchScreen),
   PROFILE(Icons.Default.Person,Screens.ProfileScreen),
}


@Composable
fun BottomNavigationMenu(
    selectedItem : BottomNavigationItem,navController : NavController
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Gray)
    ) {
         for(item in  BottomNavigationItem.values()){
             Image(
                 imageVector = item.icon,
                 contentDescription =  "Image Item",
                  modifier = Modifier.size(40.dp)
                      .weight(1f)
                      .padding(5.dp)
                      .clickable {
                          navController.navigate(item.route.route)
                      },
                 colorFilter = if (item == selectedItem) ColorFilter.tint(Color.Black)
             else ColorFilter.tint(Color.White)
             )
         }
    }
}