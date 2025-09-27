package com.maximatech.provaandroid.app.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Clients : Screen ("clients")
    object Orders : Screen ("orders")
    object InfoClient : Screen ("infoClient/{id}")
}