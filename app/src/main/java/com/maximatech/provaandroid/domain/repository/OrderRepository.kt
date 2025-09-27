package com.maximatech.provaandroid.domain.repository

import com.maximatech.provaandroid.data.RetrofitInstance
import com.maximatech.provaandroid.data.dao.OrderDao
import com.maximatech.provaandroid.data.entity.OrderEntity
import com.maximatech.provaandroid.data.response.Order

class OrderRepository (private val dao: OrderDao){



    suspend fun findAndSaveOrders() {
        val response = RetrofitInstance.api.getAllOrders()
        val orders = response.orders

        orders.forEach { order ->
            val orderEntity = OrderEntity(
                id = order.id,
                numeroPedErp = order.numeroPedErp!!,
                numeroPedRca = order.numeroPedRca!!,
                codigoCliente = order.codigoCliente!!,
                nomeCliente = order.nomeCliente,
                data = order.data!!,
                status = order.status,
                critica = order.critica,
                tipo = order.tipo,
                legendas = order.legendas
            )
            dao.insertOrder(orderEntity)
        }
    }

    suspend fun getOrders(): List<Order>{
        return dao.getAllOrders()
    }



}