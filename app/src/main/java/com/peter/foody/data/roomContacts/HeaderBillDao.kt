package com.hend.calldetailsrecorder.data.roomContacts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao

interface HeaderBillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHeaderBill(user: HeaderBill?)


    @Query("select*from HeaderBill")
    fun getHeaderBill(): Flow<List<HeaderBill?>?>?

    @Query("Select * from HeaderBill  WHERE BillNumber= :invoiceType")
    fun getBillNumber(invoiceType: String?): Flow<List<HeaderBill?>?>?

    @Query("Select * from HeaderBill  WHERE BillNumber= :invoiceType")
    fun getOneBillNumber(invoiceType: String?): Flow<HeaderBill?>?

    @Query("DELETE FROM HeaderBill")
    fun delete()
}