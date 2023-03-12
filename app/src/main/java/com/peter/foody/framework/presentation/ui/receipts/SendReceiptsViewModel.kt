package com.peter.foody.framework.presentation.ui.receipts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peter.foody.business.repositories.implementation.CustomerRepositoryImpl
import com.peter.foody.business.usecases.State
import com.peter.foody.data.remote.model.classes.*
import com.peter.foody.data.remote.model.models.AddOrderModels
import com.peter.foody.data.remote.model.models.BillReturn
import com.peter.foody.data.remote.model.models.TaskOrder
import com.peter.foody.data.roomContacts.HeaderBill
import com.peter.foody.data.roomContacts.onlineProduct.ItemsModel
import com.peter.foody.data.utils.Response
import com.peter.foody.di.BavariaDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.lang.String
import java.text.DecimalFormat
import javax.inject.Inject
import kotlin.Double

@HiltViewModel

class SendReceiptsViewModel @Inject constructor(
    private val customerRepositoryImpl: CustomerRepositoryImpl,
    private val dataBase: BavariaDataBase
) : ViewModel() {
  //  var receiptslist1 = java.util.ArrayList<Root>()
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   var  receiptslist=ArrayList<Root>()
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 //  private var _items: MutableLiveData<List<Root>> = MutableLiveData(listOf())

 //  val items: LiveData<List<Root>> = _items

   var _shopList =  MutableLiveData<MutableList<Root>>()
    val shopList: LiveData<MutableList<Root>>
        get() = _shopList

    var headerListLiveData = MutableLiveData<List<HeaderBill>>()

    fun getBill() {

        viewModelScope.launch {

            dataBase.headerBillDao().getHeaderBill().collect {
                headerListLiveData.value =it
                Log.d("headerListLiveData",it.get(0).netPrice.toString())

                for (item in it) {


                    getItemsByBillnum(item)


                }

            }

        }

    }

var ItemsByBillnum =MutableLiveData<Root>()
    suspend fun getItemsByBillnum(headerBill: HeaderBill) {
        viewModelScope.launch {
            val itemData = ArrayList<ItemDatum>()

            dataBase.ItemsBillDao().getContacts()
        //    (headerBill.BillNumber)
                .collect {
                Log.d("getB111ill", it[0].PName)
                for (i in it) {
                    Log.d("c", i.PName)
                    itemData.add(
                        getItems(
                            i.Quantity,
                            i.unitPrice,
                            i.unitPrice,
                            i.PName,
                            i.internalCode,
                            i.unitType,
                            i.itemType,
                            String.valueOf(i.itemCode)
                        )
                    )
                }
           var root=     CreateUUID(headerBill.BillNumber,"",headerBill.InvoiceDate,itemData)
                 //   Log.d("CreateUUID",root.header.receiptNumber)
                    ItemsByBillnum.value =root
                  //  receiptslist.add(root)
                  //  _shopList.value = mutableListOf(root)


            }
        }

    }



    fun getItems(
        Quantity: Double,
        unitPrice: Double,
        totalSale: Double,
        PName: kotlin.String,
        internalCode: kotlin.String,
        unitType: kotlin.String,
        itemType: kotlin.String,
        itemCode: kotlin.String
    ): ItemDatum {


        val numberFormat = DecimalFormat("#.00")

        // Double Quantitys= 2.0;
        // double unitPrices= 50.0;
        //         "netSale": 100.0000,
        //         "totalSale": 100.0000,
        //         "total": 114.0000,
        val netSale = unitPrice * Quantity
        val totalSales = netSale * 14.0 / 100.0
        val total = netSale + totalSales
        val commercialDiscountData: java.util.ArrayList<CommercialDiscountDatum> =
            java.util.ArrayList<CommercialDiscountDatum>()
        commercialDiscountData.add(CommercialDiscountDatum())
        val itemDiscountData: java.util.ArrayList<ItemDiscountDatum> =
            java.util.ArrayList<ItemDiscountDatum>()
        itemDiscountData.add(ItemDiscountDatum())
        val taxableItems: java.util.ArrayList<TaxableItem> = java.util.ArrayList<TaxableItem>()
        // Double.valueOf(numberFormat.format(Quantity ))
        // Double.valueOf(numberFormat.format(unitPrice))
        // Double.valueOf(numberFormat.format(totalSale))
        // Double.valueOf(numberFormat.format(totalSale))
        // Double.valueOf(numberFormat.format(total    ))
        taxableItems.add(
            TaxableItem( //totalSales
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
            "onSuccess1", "internalCode" + internalCode +
                    "PName" + PName +
                    "itemType" + itemType +
                    "itemCode" + itemCode +
                    "unitType" + unitType +
                    "Quantity" + java.lang.Double.valueOf(numberFormat.format(Quantity)) +
                    "unitPrice" + java.lang.Double.valueOf(numberFormat.format(unitPrice)) +
                    "netSale" + java.lang.Double.valueOf(numberFormat.format(netSale)) +
                    "netSale" + java.lang.Double.valueOf(numberFormat.format(netSale)) +
                    "total" + java.lang.Double.valueOf(numberFormat.format(total))
        )


        return z
    }
    var sellerInfo = MutableStateFlow(Seller())
    fun CreateUUID(
        BillNumber: kotlin.String,
        LastUUID: kotlin.String,
        date: kotlin.String,
        list: java.util.ArrayList<ItemDatum>
    ): Root{

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
        var UUID: kotlin.String = ""

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



        return r
    }

    private var _companyInfo = dataBase.loginInfoDao()
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


    //  fun getItemsByBillnum( listOfHeaderBill: ArrayList<HeaderBill>) = flow<ItemsBill> {
    //      var  listOfItemsModels : Flow<ItemsBill>

    //          for (item in listOfHeaderBill) {


    //            //  var billnumber =listOfHeaderBill.get(item).BillNumber
    //         //     listOfItemsModels=  dataBase.ItemsBillDao().getlistItems(billnumber)


    //          }


    //  }.flowOn(Dispatchers.IO)


    var setCustomeAddOrder = MutableLiveData<State<BillReturn>>()

    fun setListBillRepoVM(root: Receipts, AndroidID: kotlin.String) {
        viewModelScope.launch {
            customerRepositoryImpl.setListBillRepo(root,AndroidID)
                .collect {
                    setCustomeAddOrder.value = it
               //     Log.d("setCustomersDetailsRepo",it.toData()!!.status)


                }
        }
    }
}