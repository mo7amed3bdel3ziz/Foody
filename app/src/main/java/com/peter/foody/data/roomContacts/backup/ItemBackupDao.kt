package com.hend.calldetailsrecorder.data.roomContacts.backup

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao

interface ItemBackupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(user: ItemsBackup?)

    @Query("select *from ItemsBackup")
    fun getContacts(): Flow<List<ItemsBackup>>

    @Query("Select * from ItemsBackup  WHERE IDBill= :ID")
    fun getlistItems(ID: String?): Flow<List<ItemsBackup?>?>?

    @Query("DELETE FROM ItemsBackup")
    fun delete()



}