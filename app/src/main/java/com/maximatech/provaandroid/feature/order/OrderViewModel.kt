package com.maximatech.provaandroid.feature.order

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.maximatech.provaandroid.data.AppDatabase
import com.maximatech.provaandroid.data.response.Order
import com.maximatech.provaandroid.domain.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrderViewModel (application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).orderDao()
    private val repository = OrderRepository(dao)

    private val _orders = mutableStateOf<List<Order>>(emptyList())
    val orders: MutableState<List<Order>> = _orders

    private val _loadingOrderData = MutableStateFlow(false)
    val loadingOrderData: MutableStateFlow<Boolean> = _loadingOrderData

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadOrders() {
        viewModelScope.launch {
            _loadingOrderData.value = true

            runCatching {
                repository.findAndSaveOrders()
            }.onFailure {
                _errorMessage.value = "Erro ao atualizar/carregar pedidos"
            }

            _orders.value = repository.getOrders()
            _loadingOrderData.value = false
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }



}