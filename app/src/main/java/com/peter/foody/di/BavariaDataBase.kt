package com.peter.foody.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hend.calldetailsrecorder.data.roomContacts.*
import com.hend.calldetailsrecorder.data.roomContacts.backup.ItemBackupDao
import com.hend.calldetailsrecorder.data.roomContacts.backup.ItemsBackup
import com.peter.foody.data.roomContacts.AccountInfo.LoginInfoDao
import com.peter.foody.data.roomContacts.AccountInfo.LoginModel
import com.peter.foody.data.roomContacts.HeaderBill
import com.peter.foody.data.roomContacts.HeaderBillDao
import com.peter.foody.data.roomContacts.backup.HeaderBackup
import com.peter.foody.data.roomContacts.backup.HeaderBackupDao
import com.peter.foody.data.roomContacts.onlineProduct.ItemOnlineDao
import com.peter.foody.data.roomContacts.onlineProduct.ItemsModel
import com.peter.foody.data.roomContacts.productRoom.*

@Database(
    entities = [  ContactsRoom::class, HeaderBill::class, typebill::class, ItemsBill::class, Products::class, HeaderBackup::class, ItemsBackup::class,  ItemsModel::class, LoginModel::class,],
    version = 1
)
abstract class BavariaDataBase : RoomDatabase() {
    abstract fun contactsDao(): ContactsDao
    abstract fun headerBillDao(): HeaderBillDao


    abstract fun typeBillDao1(): typeBillDao
    abstract fun ItemsBillDao(): ItemsBillDao
    abstract fun productsDao(): ProductsDao
    abstract fun HeaderBackupDao(): HeaderBackupDao
    abstract fun ItemBackupDao(): ItemBackupDao
   // abstract fun headerBillOnlineDao(): HeaderBillOnlineDao
   // abstract fun itemsBillOnlineDao(): ItemsBillOnlineDao
    abstract fun itemOnlineDao(): ItemOnlineDao
    abstract fun loginInfoDao(): LoginInfoDao




}