package com.peter.foody.data.roomContacts.AccountInfo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao

interface LoginInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(user: LoginModel?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllOrders(order: List<LoginModel?>?)

    @Query("select*from LoginModel")
    fun getContacts(): Flow<List<LoginModel?>?>?

    @Query("Select * from LoginModel  WHERE comid= :ITEMCODE")
    fun getlistItems(ITEMCODE: String?): Flow<List<LoginModel?>?>?


}