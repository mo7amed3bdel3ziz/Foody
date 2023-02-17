package com.peter.foody.framework.presentation.ui.account

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peter.foody.business.repositories.implementation.LoginRepositoryImpl
import com.peter.foody.business.usecases.State
import com.peter.foody.data.remote.model.models.*
import com.peter.foody.data.roomContacts.AccountInfo.LoginModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel  @Inject constructor(private val repository: LoginRepositoryImpl) : ViewModel() {

    val loginLiveData: MutableLiveData<State<Task3<LoginModel?>?>> by lazy {
        MutableLiveData<State<Task3<LoginModel?>?>>()
    }

     val GetBranchLiveData: MutableLiveData<State<Task2<BranchModel?>?>> by lazy {
        MutableLiveData<State<Task2<BranchModel?>?>>()
    }
    val BranchLiveData=MutableLiveData<Task2<BranchModel?>?>()
    val RequestLiveData=MutableLiveData<Task<RequestModel>>()

    fun LoginViewModel(androidID: String){
        viewModelScope.launch {

            repository.LoginAoiRepo(androidID).collect{
                loginLiveData.value=it

            }
        }
    }


   // val mn=   repository.GetBranchAPIRepo(ComID).
    fun GetBranchViewModel(ComID: String){
        Log.d("GetBranchViewModel",ComID)
        viewModelScope.launch {
            repository.GetBranchAPIRepo2(ComID).collect{
                BranchLiveData.value=it
                Log.d("GetBranchLiveData", it!!.Branches[0]!!.Name)

            }

        //    repository.GetBranchAPIRepo(ComID).collect{
        //        Log.d("GetBranchLiveData1", it.toData()!!.Message.toString()+"ddddd")
        //        GetBranchLiveData.value=it
        //     //   it.toData()!!.Message?.let { it1 -> Log.d("GetBranchLiveData", it1) }
        //   //     Log.d("GetBranchLiveData", it.toData()!!.Message.toString()+"ddddd")
        //    }
        }
    }


    fun setRequestViewModel(add: RequestModel){
        viewModelScope.launch {
            repository.GetRequestAPIRepo(add).collect{
                RequestLiveData.value=it
                Log.d("RequestLiveData",it.Message)
            }


        }
    }


}