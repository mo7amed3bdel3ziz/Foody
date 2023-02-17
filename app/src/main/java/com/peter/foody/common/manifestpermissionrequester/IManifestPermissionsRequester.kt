package com.hend.calldetailsrecorder.common.manifestpermissionrequester

import androidx.activity.result.ActivityResultLauncher

interface IManifestPermissionsRequester {
     var getPermission: ActivityResultLauncher<Array<String>>
    fun requestPermission(permissions: Array<String>): MutableList<Boolean>
}