package com.hend.calldetailsrecorder.common.extensions

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import com.hend.calldetailsrecorder.common.Constant.Companion.Preferences_DataStore_NAME
import androidx.datastore.preferences.core.Preferences


val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = Preferences_DataStore_NAME)


