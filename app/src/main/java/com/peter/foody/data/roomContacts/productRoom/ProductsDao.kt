package com.peter.foody.data.roomContacts.productRoom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.peter.foody.data.roomContacts.productRoom.Products
import kotlinx.coroutines.flow.Flow

@Dao

interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(user: Products?)

    @Query("select*from Products")
    fun getContacts(): Flow<List<Products?>?>?

}