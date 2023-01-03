package com.peter.foody.framework.datasource.network

import com.peter.foody.business.model.foods.*
import com.peter.foody.business.model.reports.SalesReport
import com.peter.foody.business.usecases.State
import com.peter.foody.framework.datasource.responses.FoodResponse
import com.peter.foody.framework.datasource.responses.TaskAPI
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface FoodAPI {
    @GET("api/branches/homepage")
    fun getFoodAsync(): Deferred<FoodResponse>

    //api/Product/GetproductByComID
    @GET("api/Product/GetproductByComID")
    fun GetproductAPI(@Query("ComID") ComID:String): Deferred <TaskAPI<FoodResponse>>


    @Headers("Content-Type: application/json")
    @POST("api/Product/SetpillTest")
    fun sentInvoice(@Body list: SetBillModel): Deferred <ModelStatMg>

    @Headers("Content-Type: application/json")
    @GET("api/Product/GetSalesReport")
    fun GetSalesReportAPI(@Query("DateFrom") DateFrom:String,@Query("DateTo") DateTo:String): Deferred <TaskAPI<SalesReport>>


    @Headers("Content-Type: application/json")
    @GET("api/Product/SetClosingDay")
    fun setClosingDayAPI(@Query("ComID")ComID:String): Deferred <ItemsModel>

    @Headers("Content-Type: application/json")
    @POST("api/Product/PostProduct")
    fun  addProduct(@Body add: AddProductModel): Deferred <TaskAPI<ItemsModel>>


    @Headers("Content-Type: application/json")
    @GET("api/Report/GetTotalItemForCom")
    fun saleClosingReport(@Query("ComID") ComID: String):Deferred<TaskAPI<SalesReport>>


    @Headers("Content-Type: application/json")
    @GET("api/Category/GetCategories")
    fun GetCategoriesAPI(@Query("ComID") ComID: String):Deferred<TaskAPI<CategoryModel>>



    @Headers("Content-Type: application/json")
    @POST("api/Category/PostCategory")
    fun PostCategoryAPI(@Body list: PostCategoryModel):Deferred<TaskAPI<PostCategoryModel>>

}