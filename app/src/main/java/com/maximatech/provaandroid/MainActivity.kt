package com.maximatech.provaandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.maximatech.provaandroid.app.navigation.AppNavigation
import com.maximatech.provaandroid.app.theme.ProvaAndroidTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProvaAndroidTheme {
                AppNavigation()
            }
        }
    }
}