package com.example.instagramclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instagramclone.presentation.main.FeedScreen
import com.example.instagramclone.presentation.authentication.LoginScreen
import com.example.instagramclone.presentation.authentication.SignUpScreen
import com.example.instagramclone.presentation.Splashscreen
import com.example.instagramclone.presentation.authentication.AuthenticationViewModel
import com.example.instagramclone.presentation.main.ProfileScreen
import com.example.instagramclone.presentation.main.SearchScreen
import com.example.instagramclone.ui.theme.InstagramCloneTheme
import com.example.instagramclone.utils.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramCloneTheme {
           Surface(color = MaterialTheme.colors.background) {
                 val navController = rememberNavController()
               val authViewModel : AuthenticationViewModel = hiltViewModel()
               InstagramClone(navController, authViewModel = authViewModel)
               }
            }
        }
    }
}

@Composable
fun InstagramClone(navController: NavHostController,authViewModel: AuthenticationViewModel){
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(route = Screens.LoginScreen.route){
            LoginScreen(navController = navController,authViewModel)
        }
        composable(route = Screens.SignUpScreen.route){
            SignUpScreen(navController,authViewModel)
        }
        composable(route = Screens.SplashScreen.route){
         Splashscreen(navController = navController, authViewModel = authViewModel)
        }
        composable(route = Screens.FeedsScreen.route){
            FeedScreen(navController = navController)
        }
        composable(route = Screens.ProfileScreen.route){
            ProfileScreen(navController = navController)
        }
        composable(route = Screens.SearchScreen.route){
          SearchScreen(navController = navController)
        }
    }
}

