//package com.peter.foody.data.roomContacts.backup.item
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.peter.foody.data.remote.model.models.AddItemModel
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface AddItemModelDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertContacts(user: AddItemModel)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAllItemDB(order: List<AddItemModel>)
//
//    @Query("select*from AddItemModel")
//    fun getContacts(): Flow<List<AddItemModel>>
//
//
//
//    @Query(value = "DELETE from AddItemModel")
//    suspend  fun deleteAll()
//}