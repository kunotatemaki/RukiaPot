package rukiasoft.com.androidutilslibrary.resources

import android.content.res.Resources
import android.graphics.drawable.Drawable


/**
Copyright (C) Rukiasoft - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Raul <raulfeliz@gmail.com>, July 2018
 *
 * Interface for loading resources in the app
 */

interface ResourcesManager {

    fun getString(resId: Int): String

    fun getColor(resId: Int): Int

    fun getInteger(resId: Int): Int

    fun getDimension(resId: Int): Int

    fun getResources(): Resources

    fun getDpFromSp(dimensionInDp: Int): Int

    fun getDrawable(resId: Int): Drawable?

    fun getResourceIdFromName(resourceName: String): Int

    fun getPlural(resId: Int, value: Int, varargs: Int? = null): String
    fun getPlural(resId: Int, value: Int): String

}