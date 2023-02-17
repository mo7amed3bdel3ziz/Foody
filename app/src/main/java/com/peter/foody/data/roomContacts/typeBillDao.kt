package com.hend.calldetailsrecorder.data.roomContacts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface typeBillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserttypebill(type: typebill?)


    @Query("select*from typebill")
    fun gettypebill(): Flow<List<typebill?>?>?
}