package rukiasoft.com.androidutilslibrary.permissions

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import rukiasoft.com.androidutilslibrary.R
import rukiasoft.com.androidutilslibrary.resources.ResourcesManager
import rukiasoft.com.androidutilslibrary.ui.AndroidViewUtils
import java.lang.ref.WeakReference
import javax.inject.Inject


/**
Copyright (C) Rukiasoft - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Raul <raulfeliz@gmail.com>, July 2018
 *
 *
 */

class PermissionManager @Inject constructor(private val viewUtils: AndroidViewUtils,
                                            private val resourcesManager: ResourcesManager) {



    private var requestPermission = false
    private var needToShowDialogs = false

    fun checkPermission(activity: Activity, callbackAllPermissionsGranted: ((m: Unit?) -> Unit?),
                        permissions: MutableList<String>, messageRationale: String, code: Int){
        // Here, thisActivity is the current activity
        requestPermission = false
        needToShowDialogs = false

        permissions.forEach {
            if (ContextCompat.checkSelfPermission(activity,
                            it)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermission = true
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                                it)) {
                    needToShowDialogs = true
                }
            }
        }
        if(needToShowDialogs) {
            viewUtils.showAlertDialog(WeakReference(activity), false,
                    resourcesManager.getString(R.string.permission_explanation), messageRationale,
                    resourcesManager.getString(R.string.accept_rationale), {askForPermission(activity, permissions, code)},
                    null, null)
        }else {
            if (requestPermission) {
                askForPermission(activity, permissions, code)
            } else {
                callbackAllPermissionsGranted.invoke(null)
            }
        }
    }

    private fun askForPermission(activity: Activity, permissions: MutableList<String>, code: Int){

        ActivityCompat.requestPermissions(activity,
                permissions.toTypedArray(),
                code)

    }

    fun arePermissionsGrantedByTheUser(grantResults: IntArray): Boolean{
        return grantResults.isNotEmpty() && !grantResults.contains(PackageManager.PERMISSION_DENIED)
    }
}