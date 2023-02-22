package com.peter.foody.data.roomContacts.onlineProduct

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemOnlineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(user: ItemsModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProducts(order: List<ItemsModel>)

    @Query("select*from ItemsModel ")
    fun getAllProducts(): Flow<List<ItemsModel>>

    @Query("Select * from ItemsModel   WHERE ITEMCODE= :ITEMCODE")
    fun getlistProduct(ITEMCODE: String?): Flow<List<ItemsModel>>

    // @Query("UPDATE ItemsModel SET Price='newValue' WHERE Barcode=""")
    @Update
    fun update(itemsModel: ItemsModel)

    @Query(value = "DELETE from ItemsModel")
    suspend  fun deleteAll()


}