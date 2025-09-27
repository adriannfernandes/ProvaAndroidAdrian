package com.maximatech.provaandroid.feature.order

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maximatech.provaandroid.R
import com.maximatech.provaandroid.app.presentation.components.OrderMainItem

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrdersScreen(viewModel: OrderViewModel = viewModel()){

    val orders = viewModel.orders.value
    val isLoadingOrdersData by viewModel.loadingOrderData.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    var searchText by remember { mutableStateOf("") }

    val filteredOrders = remember(searchText, orders) {
        if (searchText.isBlank()) {
            orders
        } else {
            orders.filter { order ->
                order.numeroPedRca?.contains(searchText, ignoreCase = true) ?: false
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadOrders()
    }

    LaunchedEffect(errorMessage) {
        errorMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.clearError()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->

        Column (modifier = Modifier.padding(padding)) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { input ->
                    searchText = input.filter { it.isDigit() } },
                label = { Text(text = stringResource(R.string.place_hold_text_filter_order)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(Color.LightGray))
            Box(modifier = Modifier.fillMaxSize()) {
                when {
                    isLoadingOrdersData -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = stringResource(R.string.text_searching_orders))
                        }
                    }

                    orders.isEmpty() -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = stringResource(R.string.text_not_found_orders))
                        }
                    }

                    else -> {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(filteredOrders.size) { index ->
                                val order = filteredOrders[index]
                                OrderMainItem(order = order)
                            }
                        }
                    }
                }
            }
        }
    }
}