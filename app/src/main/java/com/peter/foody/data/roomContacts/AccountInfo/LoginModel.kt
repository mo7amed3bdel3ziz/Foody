package com.peter.foody.data.roomContacts.AccountInfo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "LoginModel", indices = [Index(value = ["comid"], unique = true)])


data class LoginModel(

    @PrimaryKey(autoGenerate = true)
    var comid: Int,


    @ColumnInfo(name = "branchID")
    var branchID: Int?,

    @ColumnInfo(name = "POS_id")
    var POS_id: Int?,

    @ColumnInfo(name = "Name")
    var Name: String?,

    @ColumnInfo(name = "tax_id")
    var tax_id: Int?,

    @ColumnInfo(name = "type")
    var type: String?,

    @ColumnInfo(name = "taxpayerActivityCode")
    var taxpayerActivityCode: String?,

    @ColumnInfo(name = "BranchName")
    var BranchName: String?,

    @ColumnInfo(name = "country")
    var country: String?,
    @ColumnInfo(name = "governate")
    var governate: String?,

    @ColumnInfo(name = "regionCity")
    var regionCity: String?,

    @ColumnInfo(name = "street")
    var street: String?,

    @ColumnInfo(name = "buildingNumber")
    var buildingNumber: String?,

    @ColumnInfo(name = "postalCode")
    var postalCode: String?,

    @ColumnInfo(name = "LicenseExpiryDate")
    var LicenseExpiryDate: String?,

    @ColumnInfo(name = "POSName")
    var POSName: String?,

    @ColumnInfo(name = "posserial")
    var posserial: String?,

    @ColumnInfo(name = "clintId")
    var clintId: String?,

    @ColumnInfo(name = "scId")
    var scId: String?,

    @ColumnInfo(name = "pososversion")
    var pososversion: String?,

    @ColumnInfo(name = "ENVIRONMENT")
    var ENVIRONMENT: String?,

    @ColumnInfo(name = "AndroidID")
    var AndroidID: String?,

    @ColumnInfo(name = "EnvironmentStatues")
    var EnvironmentStatues: String?,

    @ColumnInfo(name = "TypeVersion")
    var TypeVersion: String?,

    @ColumnInfo(name = "SendToEtaWay")
    var SendToEtaWay: String?,

    @ColumnInfo(name = "SendWayName")
    var SendWayName: String?,

    @ColumnInfo(name = "ItemFlag")
    var ItemFlag: String?,

    @ColumnInfo(name = "ItemFlagName")
    var ItemFlagName: String?,

    @ColumnInfo(name = "PriceFlag")
    var PriceFlag: String?,

    @ColumnInfo(name = "PriceFlagName")
    var PriceFlagName: String?,

    )



