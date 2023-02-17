package com.peter.foody.data.roomContacts.onlineProduct

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemOnlineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(user: ItemsModel?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllOrders(order: List<ItemsModel?>?)

    @Query("select*from ItemsModel ")
    fun getContacts(): Flow<List<ItemsModel?>?>?

    @Query("Select * from ItemsModel   WHERE ITEMCODE= :ITEMCODE")
    fun getlistItems(ITEMCODE: String?): Flow<List<ItemsModel?>?>?

    // @Query("UPDATE ItemsModel SET Price='newValue' WHERE Barcode=""")
    @Update
    fun update(itemsModel: ItemsModel?)
}