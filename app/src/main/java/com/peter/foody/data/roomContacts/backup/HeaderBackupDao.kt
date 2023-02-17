package com.peter.foody.data.roomContacts.backup

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HeaderBackupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertHeaderBill(user: HeaderBackup?)


    @Query("select*from HeaderBackup")
     fun getHeaderBill(): Flow<List<HeaderBackup?>?>?

    @Query("Select * from HeaderBackup  WHERE InvoiceType= :invoiceType")
    fun getInvoiceByType(invoiceType: String?): Flow<List<HeaderBackup?>?>?

    @Query("DELETE FROM HeaderBackup")
    fun delete()
}