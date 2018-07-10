package rukiasoft.com.androidutilslibrary.utils

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import rukiasoft.com.androidutilslibrary.R
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

@Suppress("unused")
class DeviceUtils @Inject constructor(private val context: Context) {



    fun isTablet(): Boolean {
        return context.resources.getBoolean(R.bool.isTablet)
    }

    fun isTabletOldWay(): Boolean {
        return context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
    }

    fun hideKeyboard(view: View?) {
        if (view == null) {
            return
        }
        val imm = view.context.getSystemService(
                Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun isLandscape(): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    fun isInPortrait(): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    }

    fun getOffsetForScreenDensity(): Int {
        return when (context.resources.displayMetrics.densityDpi) {
            DisplayMetrics.DENSITY_LOW -> 0
            DisplayMetrics.DENSITY_MEDIUM -> 0
            DisplayMetrics.DENSITY_HIGH -> 0
            DisplayMetrics.DENSITY_XHIGH -> 50
            DisplayMetrics.DENSITY_XXHIGH -> 45
            DisplayMetrics.DENSITY_XXXHIGH -> 45
            DisplayMetrics.DENSITY_TV -> 0
            else -> 0
        }
    }

    fun getScreenWidth(): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.x
    }

    fun getScreenHeight(): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.y
    }

    fun hasCamera() = context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)

}
