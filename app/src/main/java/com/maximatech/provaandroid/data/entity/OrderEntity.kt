package com.maximatech.provaandroid.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey val codigoCliente: String,
    val id: Int,
    val numeroPedRca: String,
    val numeroPedErp: String,
    val nomeCliente: String?,
    val data: String,
    val status: String?,
    val critica: String?,
    val tipo: String?,
    val legendas: List<String>? = emptyList()
)
