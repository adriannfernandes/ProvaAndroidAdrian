package com.maximatech.provaandroid.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clients")
data class ClientEntity(
    @PrimaryKey val id: Int,
    val codigo: String,
    val razaoSocial: String,
    val nomeFantasia: String,
    val cnpj: String,
    val ramoAtividade: String,
    val endereco: String,
    val status: String
)