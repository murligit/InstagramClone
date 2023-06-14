package com.example.instagramclone.utils

sealed class Screens(val route  : String) {

    object SplashScreen: Screens("Splash_screen")
    object LoginScreen: Screens("Login_Screen")
    object SignUpScreen: Screens("SignUp_Screen")
    object FeedsScreen: Screens("Feeds_Screen")
    object SearchScreen: Screens("Search_Screen")
    object ProfileScreen: Screens("Profile_Screen")
}
