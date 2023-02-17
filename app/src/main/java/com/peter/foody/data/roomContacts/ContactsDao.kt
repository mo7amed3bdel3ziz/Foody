package com.hend.calldetailsrecorder.data.roomContacts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.peter.foody.data.roomContacts.productRoom.ContactsRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertContacts(user: ContactsRoom?)

    @Query("select*from ContactsTable")
    fun getContacts(): Flow<List<ContactsRoom?>?>?

    @Query("Select * from ContactsTable  WHERE ITEMCODE= :ITEMCODE")
    fun getlistItems(ITEMCODE: String?): Flow<List<ContactsRoom?>?>?
}