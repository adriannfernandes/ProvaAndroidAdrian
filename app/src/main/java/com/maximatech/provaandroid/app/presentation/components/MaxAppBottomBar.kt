package com.maximatech.provaandroid.app.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.maximatech.provaandroid.R
import com.maximatech.provaandroid.app.theme.ToolbarColor


@Composable
fun MaxAppBottomBar(navController: NavController) {

    var selectedItem by remember { mutableIntStateOf(0) }

    NavigationBar(
        containerColor = ToolbarColor,
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = selectedItem == 0,
            onClick = {
                selectedItem = 0
            },
            icon = {
                Icon(
                    painter = if (selectedItem == 0) painterResource(R.drawable.ic_maxima_pessoa_ativo) else painterResource(R.drawable.ic_maxima_pessoa_inativo),
                    contentDescription = null
                )
            },
            label = { Text(stringResource(R.string.button_text_users)) },
            alwaysShowLabel = true,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.LightGray,
                selectedTextColor = Color.White,
                unselectedTextColor = Color.LightGray,
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            selected = selectedItem == 1,
            onClick = {
                selectedItem = 1
            },
            icon = {
                Icon(
                    painter = if (selectedItem == 1) painterResource(R.drawable.ic_maxima_historico_pedidos_ativo) else painterResource(R.drawable.ic_maxima_historico_pedidos_inativo) ,
                    contentDescription = null
                )
            },
            label = { Text(stringResource(R.string.button_text_history)) },
            alwaysShowLabel = true,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.LightGray,
                selectedTextColor = Color.White,
                unselectedTextColor = Color.LightGray,
                indicatorColor = Color.Transparent
            )
        )
    }
}