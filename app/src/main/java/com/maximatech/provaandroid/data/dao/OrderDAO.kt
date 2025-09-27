package com.maximatech.provaandroid.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.maximatech.provaandroid.data.entity.OrderEntity
import com.maximatech.provaandroid.data.response.Order


@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderEntity)


    @Transaction
    @Query("SELECT * FROM orders")
    suspend fun getAllOrders(): List<Order>


}