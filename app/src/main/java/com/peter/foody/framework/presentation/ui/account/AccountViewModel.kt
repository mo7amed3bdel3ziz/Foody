package com.peter.foody.framework.presentation.ui.account

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peter.foody.business.repositories.implementation.LoginRepositoryImpl
import com.peter.foody.business.usecases.State
import com.peter.foody.data.remote.model.models.*
import com.peter.foody.data.roomContacts.AccountInfo.LoginModel
import com.peter.foody.data.roomContacts.onlineProduct.ItemsModel
import com.peter.foody.data.utils.Response
import com.peter.foody.di.BavariaDataBase
import com.peter.foody.framework.presentation.editProduct.EditModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val repository: LoginRepositoryImpl,
    private val dataBase: BavariaDataBase
) :
    ViewModel() {

    // val loginLiveData: MutableLiveData<State<Task3<LoginModel>>> by lazy {
    //     MutableLiveData<State<Task3<LoginModel>>>()
    // }

    val GetBranchLiveData: MutableLiveData<State<Task2<BranchModel>>> by lazy {
        MutableLiveData<State<Task2<BranchModel>>>()
    }

    // val BranchLiveData = MutableLiveData<Task2<BranchModel>>()
    val GetComliveData = MutableLiveData<Task<CompanyModel>>()
    val addItemLiveData = MutableLiveData<Task<AddItemModel>>()

    val loginLiveData1 = MutableLiveData<Task3<LoginModel>>()
    var _exampleText = MutableLiveData<Response<String>>()


    fun fetchBothText(ComID: String, AndroidID: String) = viewModelScope.launch {
        // posting loading state in this livedata for UI
        _exampleText.postValue(Response.Loading())

        repository.getCompanyInfoInRoom(AndroidID).zip(repository.getItemInfo(ComID, AndroidID))
        //  exampleRepo.getFirstText()
        // Here, we're calling zip function
        //  .zip(exampleRepo.getSecondText())
        { firstText, secondText ->
            Log.d("testAPIS", firstText.data!![0].BranchName + "123456")
            Log.d("testAPIS", secondText.data!![0].ItemName + "321654")

            // Here we're getting result of both network calls
            // i.e. firstText & secondText

            // Just returning both the values by simply
            // adding them in this lambda
            // function of zip and then we'll get this
            // result in collect function
            // Whatever we returns from here by specifying it,
            // we can collect that in collect function
            return@zip "$firstText $secondText"
        }
            // Making it run on Dispathers.IO
            // i.e. input/output thread
            .flowOn(Dispatchers.IO)
            .catch { e ->
                // Here, we'll get an exception if
                // any failure occurs in network calls
                // So, we're simply posting error message
                _exampleText.postValue(Response.Error(e))
                Log.d("testAPIS", e.message.toString() + "3gf")

            }
            .collect { it ->
                // Now here we can collect that value
                // which we have passed in lambda
                // function of zip i.e. "$firstText $secondText"
                // Now simply returning result value as a single
                // item/value by wrapping it in Resource.Success class
                _exampleText.postValue(Response.Success(it))
                Log.d("testAPIS", it)

            }
    }

    var saveInfoRoomLiveData = MutableLiveData<Response<List<LoginModel>>>()
    fun saveInfoRoom(androidID: String) {
        viewModelScope.launch {
            repository.getCompanyInfoInRoom(androidID).collect {

                saveInfoRoomLiveData.value = it

            }

        }
    }

    var saveInfoRoomLiveDatapro = MutableLiveData<Response<List<ItemsModel>>>()
    fun saveItems(ComID: String, AndroidID: String) {
        viewModelScope.launch {
            repository.getItemInfo(ComID, AndroidID).collect {

                saveInfoRoomLiveDatapro.value = it

            }

        }
    }

    var loginInfoCombVLiveData = MutableLiveData<State<Task3<LoginModel>>>()

    fun loginInfoCombVM(androidID: String) {
        viewModelScope.launch {

            repository.loginInfoComb(androidID).collect {

                loginInfoCombVLiveData.value = it
            }

        }
    }


    var branchInfoCombVLiveData = MutableLiveData<State<Task2<BranchModel>>>()

    fun branchInfoCombVM(ComID: String) {
        viewModelScope.launch {

            repository.branchInfoComb(ComID).collect {

                branchInfoCombVLiveData.value = it
            }

        }
    }


//   fun GetBranchViewModel(ComID: String){
//      Log.d("GetBranchViewModel",ComID)
//      viewModelScope.launch {
//
//         repository.GetBranchAPIRepo2(ComID).collect{
//
//
//             BranchLiveData.value=it
//              Log.d("GetBranchLiveData", it!!.Branches[0]!!.Name)
//
//
//         }
//
//         repository.GetBranchAPIRepo(ComID).collect{
//
//             Log.d("GetBranchLiveData1", it.toData()!!.Message.toString()+"ddddd")
//             GetBranchLiveData.value=it
//           it.toData()!!.Message?.let { it1 -> Log.d("GetBranchLiveData", it1) }
//           Log.d("GetBranchLiveData", it.toData()!!.Message.toString()+"ddddd")
//         }
//      }
//  }


    val RequestLiveData = MutableLiveData<State<Task<RequestModel>>>()
    fun setRequestViewModel(add: RequestModel) {
        viewModelScope.launch {
            repository.GetRequestAPIRepo(add).collect {
                RequestLiveData.value = it
//                Log.d("RequestLiveData", it.toData()!!.Message)
            }


        }
    }




     fun saveInfoCom(){
         viewModelScope.launch {

         }
     }

    fun GetComtViewModel() {
        viewModelScope.launch {
            repository.GetComRepo().collect {
                GetComliveData.value = it
                Log.d("GetComliveData", it.Message)
            }


        }
    }


//    fun AddItemViewModel(addItemModel: AddItemModel) {
//        viewModelScope.launch {
//            repository.AddItemRepo(addItemModel).collect {
//                addItemLiveData.value = it
//                Log.d("addItemLiveData", it.Message)
//            }
//
//
//        }
//    }

    val editItemLiveData = MutableLiveData<Task<EditModel>>()
    fun EditItemViewModel(add: EditModel) {
        viewModelScope.launch {
            repository.EditItemRepo(add).collect {
                editItemLiveData.value = it
                Log.d("addItemLiveData", it.Message)
            }
        }
    }

    //var saveInfoLoginLiveDatapro = MutableLiveData<LoginModel>()


    fun saveInfoLogin( add: LoginModel) {
        viewModelScope.launch {
            repository.setLoginDataOnDB( add)
        }
    }


    val saveInfoLoginLiveDatapro = MutableLiveData<LoginModel>()
    fun getsavedInfoLogin( )= flow<LoginModel> {
        viewModelScope.launch {
            repository.getLoginDataInDB().collect{
                saveInfoLoginLiveDatapro.value =it
            }
        }
    }



}