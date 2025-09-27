package com.maximatech.provaandroid.feature.client

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.maximatech.provaandroid.data.AppDatabase
import com.maximatech.provaandroid.data.dao.ClientWithContacts
import com.maximatech.provaandroid.domain.repository.ClientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClientViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).clientDao()
    private val repository = ClientRepository(dao)

    private val _clients = mutableStateOf<List<ClientWithContacts>>(emptyList())
    val clients: MutableState<List<ClientWithContacts>>
        get() = _clients

    private val _loadingClientData = MutableStateFlow(false)
    val loadingClientData: MutableStateFlow<Boolean> = _loadingClientData

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadClients() {
        viewModelScope.launch {
            _loadingClientData.value = true

            runCatching {
                repository.findAndSaveClient()
            }.onFailure {
                _errorMessage.value = "Erro ao atualizar/carregar clientes"
            }

            _clients.value = repository.getClients()
            _loadingClientData.value = false
        }
    }

    suspend fun getClientById(idClient: Int): ClientWithContacts {
        return repository.getClientById(idClient)
    }

    fun clearError() {
        _errorMessage.value = null
    }

}