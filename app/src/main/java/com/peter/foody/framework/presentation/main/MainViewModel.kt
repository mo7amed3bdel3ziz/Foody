package com.peter.foody.framework.presentation.main

import android.content.ContentResolver
import android.os.Build
import android.provider.CallLog
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hend.calldetailsrecorder.common.extensions.getPhoneNumber
import com.peter.foody.data.roomContacts.HeaderBill
import com.peter.foody.business.model.Category
import com.peter.foody.business.model.Offer
import com.peter.foody.business.model.Slider
import com.peter.foody.business.model.foods.ModelStatMg
import com.peter.foody.business.model.foods.SetBillModel
import com.peter.foody.business.repositories.abstraction.FoodRepository
import com.peter.foody.business.repositories.implementation.CustomerRepositoryImpl
import com.peter.foody.business.usecases.State
import com.peter.foody.data.remote.model.classes.*
import com.peter.foody.data.remote.model.models.*
import com.peter.foody.data.roomContacts.AccountInfo.LoginModel
import com.peter.foody.data.roomContacts.onlineProduct.ItemsModel
import com.peter.foody.data.roomContacts.productRoom.ItemsBill
import com.peter.foody.di.BavariaDataBase
import com.peter.foody.framework.datasource.responses.FoodResponse
import com.peter.foody.framework.datasource.responses.TaskAPI
import com.peter.foody.framework.presentation.main.reports.utilShared.SharedPreferencesBillStatu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.experimental.and

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FoodRepository,
    private val customerRepositoryImpl: CustomerRepositoryImpl,
    private val dataBase: BavariaDataBase
//    contentResolver: ContentResolver
) : ViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>>
        get() = _categories

    private val _sliders = MutableLiveData<List<Slider>>()
    val sliders: LiveData<List<Slider>>
        get() = _sliders

    private val _offers = MutableLiveData<List<Offer>>()
    val offers: LiveData<List<Offer>>
        get() = _offers


    var ruternBill = MutableLiveData<State<ModelStatMg>>()

    fun setBill(list: SetBillModel) {
        viewModelScope.launch {
            repository.setBill(list).collect {
                ruternBill.value = it
                Log.d("setBill", it.toData().toString())
                // noteee
                // Log.d("setBill",it.Url.toString())
            }

        }
    }

    private val _Food = MutableLiveData<State<TaskAPI<FoodResponse>>>()
    val Food: LiveData<State<TaskAPI<FoodResponse>>>
        get() = _Food

    init {
        viewModelScope.launch {
            repository.getFood().collect {
                _Food.value = it
            }

        }
    }

    var getItemsLiveData = MutableLiveData<List<ItemsModel>>()
    fun getItemsFromLocalDB() {
        viewModelScope.launch {

            dataBase.itemOnlineDao().getAllProducts().collect {
                getItemsLiveData.value = it

            }


        }
    }

    fun insertBill(header: HeaderBill, listOfItemsModels: ArrayList<ItemsBill>){
        viewModelScope.launch {
            Log.d("lxkwtorpr","insertBill")
         // Log.d("hxiuuysxp",listOfItemsModels[0].PName)
            dataBase.headerBillDao().insertHeaderBill(header)
            dataBase.ItemsBillDao().insertList(listOfItemsModels)
        }
    }


    /**convert product from apis or any ways to ItemDatum to create uuid and root */

    fun converterProductToItemDatum(
        Quantity: Double,
        unitPrice: Double,
        totalSale: Double,
        PName: String,
        internalCode: String,
        unitType: String,
        itemType: String,
        itemCode: String
    ): ItemDatum {

        val numberFormat = DecimalFormat("#.00")

        val netSale = unitPrice * Quantity
        val totalSales = netSale * 14.0 / 100.0
        val total = netSale + totalSales
        val commercialDiscountData: ArrayList<CommercialDiscountDatum> =
            ArrayList<CommercialDiscountDatum>()
        commercialDiscountData.add(CommercialDiscountDatum())

        val itemDiscountData: ArrayList<ItemDiscountDatum> = ArrayList<ItemDiscountDatum>()
        itemDiscountData.add(ItemDiscountDatum())

        val taxableItems: ArrayList<TaxableItem> = ArrayList<TaxableItem>()

        taxableItems.add(
            TaxableItem(
                java.lang.Double.valueOf(numberFormat.format(totalSales)), 14.0
            )
        )

        val z = ItemDatum(
            internalCode,
            PName,
            itemType,
            itemCode,
            unitType,
            java.lang.Double.valueOf(numberFormat.format(Quantity)),
            java.lang.Double.valueOf(numberFormat.format(unitPrice)),
            java.lang.Double.valueOf(numberFormat.format(netSale)),
            java.lang.Double.valueOf(numberFormat.format(netSale)),
            java.lang.Double.valueOf(numberFormat.format(total)),
            commercialDiscountData,
            itemDiscountData,
            0,
            taxableItems
        )
        Log.d(
            "onSuccess1",
            "internalCode" + internalCode + "PName" + PName + "itemType" + itemType + "itemCode" + itemCode + "unitType" + unitType + "Quantity" + java.lang.Double.valueOf(
                numberFormat.format(Quantity)
            ) + "unitPrice" + java.lang.Double.valueOf(numberFormat.format(unitPrice)) + "netSale" + java.lang.Double.valueOf(
                numberFormat.format(netSale)
            ) + "netSale" + java.lang.Double.valueOf(numberFormat.format(netSale)) + "total" + java.lang.Double.valueOf(
                numberFormat.format(total)
            )
        )




        return z
    }


    private var companyInfo = dataBase.loginInfoDao()
    suspend fun getCompanyInfo(): Seller {
        var loginModel: LoginModel
        var sellerModel = Seller()

        val b = viewModelScope.async {
            companyInfo.getContacts().collect {
                sellerModel = getSeller(it[0])

                loginModel = it[0]
                val branch = BranchAddress(
                    loginModel.country,
                    loginModel.governate,
                    loginModel.regionCity,
                    loginModel.street,
                    loginModel.buildingNumber,
                    "",
                    "",
                    "",
                    "",
                    ""
                )

                val sellerModesl = Seller(
                    loginModel.tax_id.toString(),
                    loginModel.BranchName, loginModel.branchID.toString(),
                    branch,
                    loginModel.posserial,
                    "",
                    loginModel.taxpayerActivityCode
                )
            }
            return@async sellerModel
        }
        return b.await()
    }


    // Function that makes the network request, blocking the current thread

//    suspend fun makeLoginRequest(
//        jsonBody: String
//    ):  Seller {
//
//        // Move the execution of the coroutine to the I/O dispatcher
//        return withContext(Dispatchers.IO) {
//            // Blocking network request code
//            companyInfo.getContacts().collect {
//            val    loginModel = it[0]
//             //   sellerModel=     getSeller(it[0])
//
//               // loginModel = it[0]
//                val branch = BranchAddress(
//                    loginModel.country,
//                    loginModel.governate,
//                    loginModel.regionCity,
//                    loginModel.street,
//                    loginModel.buildingNumber,
//                    "",
//                    "",
//                    "",
//                    "",
//                    ""
//                )
//
//                val   sellerModesl = Seller(
//                    loginModel.tax_id.toString(),
//                    loginModel.BranchName, loginModel.branchID.toString(),
//                    branch,
//                    loginModel.posserial,
//                    "",
//                    loginModel.taxpayerActivityCode
//                )
//            }
//            return@withContext sellerModesl
//        }
//    }


    // suspend
    var getItemsLiveDatas = MutableLiveData<Seller>()
    var sellerInfo = MutableStateFlow(Seller())

      var _companyInfo = dataBase.loginInfoDao()
    fun getUserIdByEmail() {

        viewModelScope.launch {
            _companyInfo.getContacts().map {
                var sellerModesl = Seller()
                val loginModel = it[0]
                val branch = BranchAddress(
                    loginModel.country,
                    loginModel.governate,
                    loginModel.regionCity,
                    loginModel.street,
                    loginModel.buildingNumber,
                    "",
                    "",
                    "",
                    "",
                    ""
                )

                sellerModesl = Seller(
                    loginModel.tax_id.toString(),
                    loginModel.BranchName, loginModel.branchID.toString(),
                    branch,
                    loginModel.posserial,
                    "",
                    loginModel.taxpayerActivityCode
                )
                return@map sellerModesl
            }
                .collect {
                    sellerInfo.value = it
                }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createHeader(
        listOfItemsModels: ArrayList<ItemsModels>
    ) {

     //   viewModelScope.launch {
        Log.d("lxkwtorpr","createHeader")
            val numberRicet = SharedPreferencesBillStatu.getInstance().numberOFBill
            val TimeRicet = getTimeBill()
       var      listOfItems= ArrayList<ItemsBill>()
            val price = 0.0
            val Tax = 0.0
            val totalPrice = 0.0
            val itemId = 0
            for (ItemsModels in listOfItemsModels) {
                Log.d("lxkwtorpr","loop")

                //   Log.d("lxkwtorpr",ItemsModels.ItemName)
                listOfItems.add(
                    setItemsRoom(
                        ItemsModels.Description,
                        ItemsModels.Price,
                        numberRicet,
                        itemId.toString(),
                        ItemsModels.Quantity.toDouble(),
                        ItemsModels.UnitType,
                        ItemsModels.ItemType,
                        ItemsModels.ItemCode.toString(),
                        ItemsModels.Barcode

                    )

                )

            }


            val url =
                "https://preprod.invoicing.eta.gov.eg/receipts/search/a700243730510ebd8499d9d895ad7eb4ee4ba9d22b3bf155d81552f7e31dd93d";
            //To Create New Bill in Room db
            val headerBill = HeaderBill(
                0,
                numberRicet,
                TimeRicet!!,
                Tax, price, totalPrice, url, "S"

            )
        Log.d("lxkwtorpr",listOfItems.size.toString())
            insertBill(headerBill,listOfItems)

       // }


    }

    fun setItemsRoom(
        nameItem: String, price: Double, ID: String, itemId: String,
        Quantity: Double,  // double unitPrice,
        // double totalSale,
        // String PName,
        // String internalCode,
        unitType: String,
        itemType: String,
        itemCode: String,
        barCode: String
    ): ItemsBill {
        Log.d("lxkwtorpr","setItemsRoom")
        val bill = ItemsBill(
            1,
            ID,
            itemId,
            Quantity,
            price,
            nameItem,
            itemType,
            itemCode,
            unitType,
            barCode,

            )


        return bill
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun getTimeBill(): String? {

        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

        var cal = Calendar.getInstance()
        cal = Calendar.getInstance()
        cal.add(Calendar.HOUR, -2)
        return dateFormat.format(cal.time).toString()
    }

    fun getSeller(loginModel: LoginModel): Seller {
        var sellerModel: Seller
        val branch = BranchAddress(
            loginModel.country,
            loginModel.governate,
            loginModel.regionCity,
            loginModel.street,
            loginModel.buildingNumber,
            "",
            "",
            "",
            "",
            ""
        )
        sellerModel = Seller(
            loginModel.tax_id.toString(),
            loginModel.BranchName, loginModel.branchID.toString(),
            branch,
            loginModel.posserial,
            "",
            loginModel.taxpayerActivityCode
        )

        return sellerModel
    }

    fun CreateUUID(
        BillNumber: String,
        LastUUID: String,
        date: String,
        list: java.util.ArrayList<ItemDatum>
    ): String {

        getUserIdByEmail()
        var seller = sellerInfo.value
        //   Date df = new Date();
        val numberFormat = DecimalFormat("#.00")
        var TotalSale = 0.0
        var Total = 0.0
        for (itemData in list) {
            TotalSale += itemData.getTotalSale()
            Total += itemData.getTotal()
        }
        //itemData
        val TaxTotal = Total - TotalSale
        var UUID: String = ""

        //  Date c = Calendar.getInstance().getTime();
        //  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        //  String formattedDate = df.format(c);
        //  //   Date df = new Date("yyyy-MM-dd'T'HH:mm:ss'Z'");
        val header = Header(
            date,  //df,
            BillNumber,
            "",
            "",
            ""
        )
        val documentType = DocumentType()


        val buyer = Buyer(
            "p",
            "",
            "",
            "",
            "0"
        )
        val extraReceiptDiscountData: java.util.ArrayList<ExtraReceiptDiscountDatum> =
            java.util.ArrayList<ExtraReceiptDiscountDatum>()
        extraReceiptDiscountData.add(ExtraReceiptDiscountDatum())
        val taxTotals: java.util.ArrayList<TaxTotal> = java.util.ArrayList<TaxTotal>()
        taxTotals.add(
            TaxTotal(
                java.lang.Double.valueOf(numberFormat.format(TaxTotal)) //  TaxTotal
            )
        )
        val contractor = Contractor()
        val beneficiary = Beneficiary()
        val r = Root(
            header, documentType, seller, buyer, list,  //                TotalSale
            java.lang.Double.valueOf(numberFormat.format(TotalSale)),
            0.0,
            0.0,
            extraReceiptDiscountData,  //TotalSale
            java.lang.Double.valueOf(numberFormat.format(TotalSale)),
            0,  //                Total
            java.lang.Double.valueOf(numberFormat.format(Total)),
            taxTotals,
            "C",
            0,
            contractor,
            beneficiary
        )
        try {
            // val g = Gson()
            // g.toJson(r)
            // Log.d("onSuccess", "1" + r.getString())
            // Log.d("onSuccess", "1" + g.toJson(r))
           // UUID = computeHash(r.getString())
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return UUID
    }

    @Throws(NoSuchAlgorithmException::class, UnsupportedEncodingException::class)
    fun computeHash(input: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        digest.reset()
        val byteData = digest.digest(input.toByteArray(charset("UTF-8")))
        //   StringBuffer sb = new StringBuffer();
        val sb = StringBuilder()
        for (i in byteData.indices) {
            sb.append(Integer.toString((byteData[i] and 0xff.toByte()) + 0x100, 16).substring(1))
        }
        return sb.toString()
    }


    // var setCustomeAddOrders = MutableLiveData<State<TaskOrder>>()
    var setCustomeAddOrder = MutableLiveData<TaskOrder>()

    fun setCustomeAddOrdersVM(
        Number: String, Name: String,
        ComID: Int,
        AndroidID: String, NewAddress: String,
        ExistAddress: String, add: List<AddOrderModels>
    ) {
        viewModelScope.launch {
            customerRepositoryImpl.seCutomersDetailsRepo(
                Number,
                Name,
                ComID,
                AndroidID,
                NewAddress,
                ExistAddress,
                add
            )
                .collect {

                    Log.d("setCustomeAddOrders", it.Order)
                    setCustomeAddOrder.value = it

                }
        }
    }


    var customerDetails = MutableLiveData<State<TaskCustomer<CustomerDetailsModel>>>()
    var customerDetailsOrders = MutableLiveData<List<CustomerOrders>>()
    var customerDetailsPhone = MutableLiveData<List<CustomerPhone>>()
    var customerDetailsAddress = MutableLiveData<List<CustomerAddress>>()
    // var CustomerDetails = MutableLiveData<TaskCustomer<CustomerDetailsModel>>()

    fun getCustomerDetailsVM(Number: String, ComID: Int, AndroidID: String) {
        viewModelScope.launch {
            customerRepositoryImpl.getCustomerDetailsRepo(Number, ComID, AndroidID)

                .collect {
                    when (it) {
                        is State.Loading -> {

                        }
                        is State.Success -> {
                            customerDetailsOrders.value = it.data.data.CustomerOrders
                            customerDetailsAddress.value = it.data.data.CustomerAddress

                        }
                        is State.Error -> {

                        }
                    }

                    //  customerDetailsOrders.value = it.toData()!!.data.CustomerOrders
                    //  customerDetailsPhone.value = it.toData()!!.data.CustomerPhone
                    //  customerDetailsAddress.value = it.toData()!!.data.CustomerAddress


                    //  customerDetails.value = it
                    //  Log.d("viewModelScope", it.data.get(0).CustomerOrders.get(0).OrderNumber)
//                Log.d("zzz", it.toData()!!.data.get(0).CustomerPhone.toString())


                }
        }
    }



}

