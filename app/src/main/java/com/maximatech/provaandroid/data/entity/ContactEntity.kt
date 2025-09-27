package com.maximatech.provaandroid.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "contacts",
    foreignKeys = [ForeignKey(
        entity = ClientEntity::class,
        parentColumns = ["id"],
        childColumns = ["clienteId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ContactEntity(
    @PrimaryKey(autoGenerate = true) val contatoId: Int = 0,
    val clienteId: Int,
    val nome: String?,
    val telefone: String?,
    val celular: String?,
    val conjuge: String?,
    val tipo: String?,
    val time: String?,
    val email: String?,
    val dataNascimento: String?,
    val dataNascimentoConjuge: String?
)