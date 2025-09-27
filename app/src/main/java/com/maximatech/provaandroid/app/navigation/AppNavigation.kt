package com.maximatech.provaandroid.app.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.maximatech.provaandroid.app.presentation.components.MaxAppBottomBar
import com.maximatech.provaandroid.app.presentation.components.MaxAppTopBar
import com.maximatech.provaandroid.app.presentation.components.MaxClientAppTopBar
import com.maximatech.provaandroid.feature.splash.SplashScreen
import com.maximatech.provaandroid.feature.client.ClientsScreen
import com.maximatech.provaandroid.feature.client.InfoClientScreen
import com.maximatech.provaandroid.feature.order.OrdersScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val uiViewModel: SharedUiViewModel = viewModel()

    Scaffold(
        topBar = {
            when (currentRoute) {
                Screen.Clients.route, Screen.Orders.route -> {
                    MaxAppTopBar()
                }
                "infoClient/{id}" -> {
                    MaxClientAppTopBar(
                        title = uiViewModel.topBarTitle,
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        },
        bottomBar = {
            when (currentRoute) {
                Screen.Clients.route, Screen.Orders.route -> {
                    MaxAppBottomBar(navController)
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) { SplashScreen {
                navController.navigate(Screen.Clients.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            } }
            composable(Screen.Clients.route) { ClientsScreen(navController = navController) }
            composable(Screen.Orders.route) { OrdersScreen() }
            composable(Screen.InfoClient.route) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 1
                InfoClientScreen(idClient = id, uiViewModel = uiViewModel)
            }
        }
    }
}

class SharedUiViewModel : ViewModel() {
    var topBarTitle by mutableStateOf("")
        private set

    fun setTitle(newTitle: String) {
        topBarTitle = newTitle
    }
}


