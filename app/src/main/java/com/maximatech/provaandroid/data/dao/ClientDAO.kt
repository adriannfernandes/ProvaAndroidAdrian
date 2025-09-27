package com.maximatech.provaandroid.data.dao

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import com.maximatech.provaandroid.data.entity.ClientEntity
import com.maximatech.provaandroid.data.entity.ContactEntity

@Dao
interface ClientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClient(client: ClientEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(contacts: List<ContactEntity>)

    @Transaction
    @Query("SELECT * FROM clients WHERE id = :id")
    suspend fun getClientById(id: Int): ClientWithContacts


    @Transaction
    @Query("SELECT * FROM clients")
    suspend fun getAllClientsWithContacts(): List<ClientWithContacts>
}

data class ClientWithContacts(
    @Embedded val client: ClientEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "clienteId"
    )
    val contacts: List<ContactEntity>
)