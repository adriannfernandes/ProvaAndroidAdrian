package com.maximatech.provaandroid.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maximatech.provaandroid.app.presentation.screens.HomeScreen
import com.maximatech.provaandroid.app.presentation.screens.SplashScreen

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") { SplashScreen(navController) }
        composable("home") { HomeScreen(navController) }
    }
}