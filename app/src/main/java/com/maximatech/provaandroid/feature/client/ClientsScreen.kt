package com.maximatech.provaandroid.feature.client

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.maximatech.provaandroid.R
import com.maximatech.provaandroid.app.presentation.components.ClientMainItem
import com.maximatech.provaandroid.app.theme.ScreenWithCardsBackground

@Composable
fun ClientsScreen(viewModel: ClientViewModel = viewModel(), navController: NavController) {

    val clients = viewModel.clients.value
    val isLoadingClientData by viewModel.loadingClientData.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.loadClients()
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
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
        ) {

            Box(modifier = Modifier.fillMaxSize()) {
                when {
                    isLoadingClientData -> {
                        Box(
                            modifier = Modifier.fillMaxSize().background(ScreenWithCardsBackground),
                            contentAlignment = Alignment.Center

                        ) {
                            Text(text = stringResource(R.string.text_searching_clients))
                        }
                    }

                    clients.isEmpty() -> {
                        Box(
                            modifier = Modifier.fillMaxSize().background(ScreenWithCardsBackground),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = stringResource(R.string.text_not_found_clients))
                        }
                    }

                    else -> {
                        LazyColumn(modifier = Modifier.fillMaxSize().background(ScreenWithCardsBackground)) {
                            items(clients) { client ->
                                ClientMainItem(
                                    clientWithContacts = client,
                                    onClientSelect = {
                                        navController.navigate("infoClient/${client.client.id}")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}