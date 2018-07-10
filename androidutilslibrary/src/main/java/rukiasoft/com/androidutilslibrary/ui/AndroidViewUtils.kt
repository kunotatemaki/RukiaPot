package rukiasoft.com.androidutilslibrary.ui

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog
import rukiasoft.com.androidutilslibrary.resources.ResourcesManager
import rukiasoft.com.androidutilslibrary.safe
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

@Suppress("unused")
class AndroidViewUtils  @Inject constructor(private val context: Context, private val resourcesManager: ResourcesManager){

    fun showAlertDialog(activity: WeakReference<Activity>, allowCancelWhenTouchingOutside: Boolean, title: String, message: String?,
                        positiveButton: String?, callbackPositive: ((m: Unit?) -> Any?)?,
                        negativeButton: String?, callbackNegative: ((m: Unit?) -> Any?)?){
        activity.safe {
            val builder = AlertDialog.Builder(activity.get()!!)
            builder.setTitle(title)
            message?.let { builder.setMessage(message) }
            positiveButton?.let {
                builder.setPositiveButton(positiveButton
                ) { _, _ ->
                    callbackPositive?.invoke(null)
                }
            }
            negativeButton?.let {
                builder.setNegativeButton(negativeButton
                ) { _, _ ->
                    callbackNegative?.invoke(null)
                }
            }
            builder.setCancelable(allowCancelWhenTouchingOutside)
            builder.create().show()
        }

    }

    fun calculateNoOfColumnsWithItemWidth(itemWidth: Int, margins: Int): Int {
        //take into account margins
        val displayMetrics = context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels - margins
        return (screenWidth / itemWidth)
    }

    fun calculateNoOfColumns(): Int {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        return (dpWidth / 180).toInt()
    }

    fun calculateNoOfColumns(reductionRate: Float): Int {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels * reductionRate / displayMetrics.density
        return (dpWidth / 180).toInt()
    }

}