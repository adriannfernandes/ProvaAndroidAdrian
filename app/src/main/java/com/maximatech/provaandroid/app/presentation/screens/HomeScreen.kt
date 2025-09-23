package com.maximatech.provaandroid.app.presentation.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.maximatech.provaandroid.app.presentation.components.MaxAppBottomBar
import com.maximatech.provaandroid.app.presentation.components.MaxAppTopBar

import com.maximatech.provaandroid.app.theme.ToolbarColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){

    Scaffold(
        topBar = {
            MaxAppTopBar()
        },
        bottomBar = {
            MaxAppBottomBar(navController)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text("Sem Clientes Cadastrados", fontSize = 20.sp)
        }
    }

}