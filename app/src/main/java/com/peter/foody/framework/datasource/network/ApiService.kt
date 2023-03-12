package com.peter.foody.framework.datasource.network


import com.hend.calldetailsrecorder.data.remote.model.CallResponse
import com.peter.foody.data.remote.model.classes.Receipts
import com.peter.foody.data.remote.model.classes.Root
import com.peter.foody.data.remote.model.models.*
import com.peter.foody.data.roomContacts.AccountInfo.LoginModel
import com.peter.foody.data.roomContacts.onlineProduct.ItemsModel
import com.peter.foody.framework.datasource.responses.TaskAPI
import com.peter.foody.framework.presentation.addProduct.AddproductModel
import com.peter.foody.framework.presentation.editProduct.EditModel
import com.peter.foody.framework.presentation.reports.ReportModel
import com.peter.foody.framework.presentation.reports.TotalReportModel

import kotlinx.coroutines.Deferred
import retrofit2.http.*


interface ApiService {
    @POST("api/CallsActions/ring")
    suspend fun sentRingingAction(
        @Query("key") key: String,
        @Query("phoneNumber") phoneNumber: String
    ): CallResponse

    @POST("api/CallsActions/answer")
    suspend fun sentAnswerAction(
        @Query("key") key: String,
        @Query("ringCallId") ringCallId: String
    ): CallResponse

    @POST("api/CallsActions/close")
    suspend fun sentCloseAction(
        @Query("key") key: String,
        @Query("ringCallId") ringCallId: String
    ): CallResponse

    @POST("api/CallsActions/noAnswer")
    suspend fun sentNoAnswerAction(
        @Query("key") key: String,
        @Query("ringCallId") ringCallId: String
    ): CallResponse


    // @Headers("Content-Type: application/json")
    // @POST("api/Account/Login")
    // fun LoginAPI(@Body s: String?): Deferred<Task3<LoginModel?>?>?


    //  @Headers("Content-Type: application/json")
    //  @GET("api/Account/GetTodayItems")
    //  fun GetTodayItemsAPI(
    //      @Query("ComID") ComID: Int, @Query("date") date: String?,
    //      @Query("AndroidID") AndroidID: String?
    //  ): Observable<Task<TodayItemsModel?>?>?


//    @Headers("Content-Type: application/json")
//    @GET("api/Account/GetItems")
//    Observable<Task4<ItemsModel>> GetItemsAPI(@Query("comID") String ComID);


//   @Headers("Content-Type: application/json")
//   @POST("api/Account/ClientLogin")
//   Single<LoginModel>login(
//           @Body String s);


//   @Headers("Content-Type: application/json")
//   @POST("api/Account/Client_Registration_Request")
//   Single<LoginModel>creatAcc(
//           @Body HashMap Map);


//   @Headers("Content-Type: application/json")
//   @GET("GetProductsTestPagination")
//   Call<ItemsModel> getItemsPagnation(
//           @Query("Count") int count ,
//           @Query("Cursor") int Cursor);


//   @Headers("Content-Type: application/json")
//   @GET("api/Product/GetProducts")
//   Observable<Task> getItems();


//   @Headers("Content-Type: application/json")
//   @POST("api/Product/GetProductByBarCade")
//   Single<ItemsModel> getItemsQr(@Body String s);

//   @Headers("Content-Type: application/json")
//   @POST("api/Product/SetpillTest")
//   Single<ModelStatMg> sentInvoice(@Body SetBillModel list);

//   @Headers("Content-Type: application/json")
//   @POST("api/Product/SetpillTest")
//   Single<ModelStatMg> sentInvoice2(@Body SetBillModel list);

//   @Headers("Content-Type: application/json")
//   @POST("api/Product/SetpillTest")
//   Single<ModelStatMg> senReturn(@Body SetBillRutern list);


//   @Headers("Content-Type: application/json")
//   @POST("api/Product/UpdateProductByBarCade")
//   Single<ItemsModel> udateProduct(@Body UpdateProductModel update);


//  // api/Product/SetClosingDay
//   @Headers("Content-Type: application/json")
//   @POST("api/Product/PostProduct")
//   Single<ItemsModel> addProduct(@Body AddProductModel add);

//   @Headers("Content-Type: application/json")
//   @GET("api/Product/SetClosingDay")
//   Single<ItemsModel> setClosingDay(@Query("ComID")String ComID);


//   @Headers("Content-Type: application/json")
//   @GET("api/Report/GetTotalItemForCom")
//   Observable<Task1Closed> sale(@Query("ComID") String ComID);

//   @Headers("Content-Type: application/json")
//   @GET("api/Report/GetTotalForEachReturnItem")
//   Observable<Task1Closed> ReturnItem(@Query("ComID") String ComID);

//   @Headers("Content-Type: application/json")
//   @GET("api/Report/GetSalesReportForCom")
//   Single<Task1Closed> getReport(@Query("DateFrom")String DateFrom
//           ,@Query("DateTo")String DateTo,@Query("ComID") String ComID);
///https://api.nasa.gov/planetary/apod?api_key=pEVGumcFSQ44T56oqswrfi0V9OEMqnRvZDkI8sCb

//   //   @Headers("Content-Type: application/json")
//   //   @GET("planetary/apod")
//   //   Single<اللى هيرجع>get nasa(
//   //             @Query("api_key") int key);
///


    //    @Headers("Content-Type: application/json")
    //    @GET("api/Account/GetItems")
    //    Observable<Task4<ItemsModel>> GetItemsAPI(@Query("comID") String ComID);
    //   @Headers("Content-Type: application/json")
    //   @POST("api/Account/ClientLogin")
    //   Single<LoginModel>login(
    //           @Body String s);
    //   @Headers("Content-Type: application/json")
    //   @POST("api/Account/Client_Registration_Request")
    //   Single<LoginModel>creatAcc(
    //           @Body HashMap Map);
    //   @Headers("Content-Type: application/json")
    //   @GET("GetProductsTestPagination")
    //   Call<ItemsModel> getItemsPagnation(
    //           @Query("Count") int count ,
    //           @Query("Cursor") int Cursor);
    //   @Headers("Content-Type: application/json")
    //   @GET("api/Product/GetProducts")
    //   Observable<Task> getItems();
    //   @Headers("Content-Type: application/json")
    //   @POST("api/Product/GetProductByBarCade")
    //   Single<ItemsModel> getItemsQr(@Body String s);
    //   @Headers("Content-Type: application/json")
    //   @POST("api/Product/SetpillTest")
    //   Single<ModelStatMg> sentInvoice(@Body SetBillModel list);
    //   @Headers("Content-Type: application/json")
    //   @POST("api/Product/SetpillTest")
    //   Single<ModelStatMg> sentInvoice2(@Body SetBillModel list);
    //   @Headers("Content-Type: application/json")
    //   @POST("api/Product/SetpillTest")
    //   Single<ModelStatMg> senReturn(@Body SetBillRutern list);
    //   @Headers("Content-Type: application/json")
    //   @POST("api/Product/UpdateProductByBarCade")
    //   Single<ItemsModel> udateProduct(@Body UpdateProductModel update);
    //  // api/Product/SetClosingDay
    //   @Headers("Content-Type: application/json")
    //   @POST("api/Product/PostProduct")
    //   Single<ItemsModel> addProduct(@Body AddProductModel add);
    //   @Headers("Content-Type: application/json")
    //   @GET("api/Product/SetClosingDay")
    //   Single<ItemsModel> setClosingDay(@Query("ComID")String ComID);
    //   @Headers("Content-Type: application/json")
    //   @GET("api/Report/GetTotalItemForCom")
    //   Observable<Task1Closed> sale(@Query("ComID") String ComID);
    //   @Headers("Content-Type: application/json")
    //   @GET("api/Report/GetTotalForEachReturnItem")
    //   Observable<Task1Closed> ReturnItem(@Query("ComID") String ComID);
    //   @Headers("Content-Type: application/json")
    //   @GET("api/Report/GetSalesReportForCom")
    //   Single<Task1Closed> getReport(@Query("DateFrom")String DateFrom
    //           ,@Query("DateTo")String DateTo,@Query("ComID") String ComID);
    ///https://api.nasa.gov/planetary/apod?api_key=pEVGumcFSQ44T56oqswrfi0V9OEMqnRvZDkI8sCb
    //   //   @Headers("Content-Type: application/json")
    //   //   @GET("planetary/apod")
    //   //   Single<اللى هيرجع>get nasa(
    //   //             @Query("api_key") int key);
    ///
    //   //   @Headers("Content-Type: application/json")
    //   @POST("api/Employee/EmployeeAttendanceV2")
    //   Single<LoginModel>Location(
    //           @Body LocationModelApi Map);
    //
    //
    ///
    ////  @Headers("Content-Type: application/json")
    ////   @POST("api/Employee/GetEmployeeInfo")
    ////   Single<InFoModel>getInfo(
    ////           @Body int x);
    //


//    @Headers("Content-Type: application/json")
//    @POST("api2/AndroidReciets/SetListBill")
//    fun SetListBill(
//        @Body root: Receipts?,
//        @Query("AndroidID") AndroidID: String?
//    ): Deferred<BillReturn?>?

//   @Headers("Content-Type: application/json")
//   @GET("api/Account/GetItems")
//   fun GetItemsAPI(
//       @Query("ComID") ComID: String?,
//       @Query("AndroidID") AndroidID: String?
//   ): Deferred<TaskAPI<ItemsModel?>?>?

    @Headers("Content-Type: application/json")
    @POST("api2/AndroidReciets/SetBill")
    fun getProductm(@Body root: Root): Deferred<BillReturn>

    @Headers("Content-Type: application/json")
    @POST("api/Account/Login")
    fun LoginAPI(@Body s: String): Deferred<Task3<LoginModel>>

    @Headers("Content-Type: application/json")
    @GET("api/Account/GetBranch")
    fun GetBranchAPI(@Query("comID") ComID: String?): Deferred<Task2<BranchModel>>


    @Headers("Content-Type: application/json")
    @POST("api/Account/Reg_Request")
    fun GetRequestAPI(@Body add: RequestModel): Deferred<Task<RequestModel>>


    @Headers("Content-Type: application/json")
    @GET("api/Account/GetCom")
    fun GetComAPI(): Deferred<Task<CompanyModel>>


    @Headers("Content-Type: application/json")
    @POST("api/Account/AddItem")
    fun AddItemAPI(@Body add: AddproductModel): Deferred<Task<AddproductModel>>


    @Headers("Content-Type: application/json")
    @POST("api/Account/EditItem")
    fun EditItemAPI(@Body add: EditModel): Deferred<Task<EditModel>>


    @Headers("Content-Type: application/json")
    @GET("api/Account/GetTodayItems")
    fun GetTodayItemsAPI(
        @Query("ComID") ComID: Int, @Query("date") date: String,
        @Query("AndroidID") AndroidID: String
    ): Deferred<Task<TodayItemsModel>>

    @Headers("Content-Type: application/json")
    @GET("api/Account/GetItems")
    fun GetItemsAPI(
        @Query("ComID") ComID: String,
        @Query("AndroidID") AndroidID: String
    ): Deferred<TaskAPI<ItemsModel>>


    @Headers("Content-Type: application/json")
    @GET("api2/Cutomers/GetDetails")
    fun GetCutomersDetailsAPI(
        @Query("Number") Number: String,
        @Query("ComID") ComID: Int,
        @Query("AndroidID") AndroidID: String
    ): Deferred<TaskCustomer<CustomerDetailsModel>>


    @Headers("Content-Type: application/json")
    @POST("api2/Cutomers/AddOrder")
    fun setCutomersAddOrderAPI(
        @Query("Number") Number: String,
        @Query("Name") Name: String,
        @Query("ComID") ComID: Int,
        @Query("AndroidID") AndroidID: String,
        @Query("NewAddress") NewAddress: String,
        @Query("ExistAddress") ExistAddress: String,
        @Body add: List<AddOrderModels>
    ): Deferred<TaskOrder>


    //call .....
    @Headers("Content-Type: application/json")
    @POST("api2/AndroidReciets/SetListBill")
    fun SetListBill(
        @Body root: Receipts,
        @Query("AndroidID") AndroidID: String
    ): Deferred<BillReturn>


    @Headers("Content-Type: application/json")
    @GET("api2/AndroidReciets/GetReportsByDay")
    fun GetReportsByDay(
        @Query("AndroidID") AndroidID: String,
        @Query("date") date: String
    ): Deferred<ReportModel>

  @Headers("Content-Type: application/json")
    @GET("api2/AndroidReciets/GetReports")
    fun GetReports(
        @Query("AndroidID") AndroidID: String,
    ): Deferred<TotalReportModel>




}