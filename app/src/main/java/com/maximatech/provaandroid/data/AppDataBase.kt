package com.maximatech.provaandroid.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.maximatech.provaandroid.data.dao.ClientDao
import com.maximatech.provaandroid.data.dao.OrderDao
import com.maximatech.provaandroid.data.entity.ClientEntity
import com.maximatech.provaandroid.data.entity.ContactEntity
import com.maximatech.provaandroid.data.entity.OrderEntity

@Database(entities = [ClientEntity::class, ContactEntity::class, OrderEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clientDao(): ClientDao

    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "maxapp_db"
                )
                .fallbackToDestructiveMigration()
                .build().also { INSTANCE = it }
            }
    }
}