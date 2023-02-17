package com.peter.foody.data.roomContacts.productRoom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.peter.foody.data.roomContacts.productRoom.ItemsBill
import kotlinx.coroutines.flow.Flow

@Dao

interface ItemsBillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(user: ItemsBill?)

    // @Query("Select*from ItemsBill")
            @Query("Select * from ItemsBill")
    fun getContacts(): Flow<List<ItemsBill>>

    @Query("Select * from ItemsBill  WHERE IDBill= :ID")
    fun getlistItems(ID: String?): Flow<List<ItemsBill?>?>?

    @Query("Select * from ItemsBill  WHERE IDBill= :ID")
    fun getOneItems(ID: String?): Flow<ItemsBill?>?

    @Query("DELETE FROM ItemsBill")
    fun delete()

}