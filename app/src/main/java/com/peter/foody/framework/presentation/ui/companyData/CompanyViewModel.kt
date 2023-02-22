package com.peter.foody.framework.presentation.ui.companyData

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peter.foody.data.roomContacts.AccountInfo.LoginModel
import com.peter.foody.data.roomContacts.onlineProduct.ItemsModel
import com.peter.foody.di.BavariaDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CompanyViewModel @Inject constructor(private val dataBase: BavariaDataBase) :
    ViewModel() {


    var getComDataLiveData = MutableLiveData<LoginModel>()
    fun getComDataFromLocalDB() {
        viewModelScope.launch {
            dataBase.loginInfoDao().getContacts().collect {
                getComDataLiveData.value=it.get(0)
                  Log.d("accountviewModel",it.get(0).BranchName)

            }
        }

    }

    var getItemsLiveData = MutableLiveData<List<ItemsModel>>()
    fun getItemsFromLocalDB() {
        viewModelScope.launch {

            dataBase.itemOnlineDao().getAllProducts().collect {
                getItemsLiveData.value = it

//                Log.d("accountviewModel",it.get(0).Barcode.toString())

            }
        }
    }
}