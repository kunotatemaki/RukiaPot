package rukiasoft.com.androidutilslibrary.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.text.Html
import android.text.Spanned
import android.util.TypedValue
import android.view.KeyCharacterMap
import android.view.KeyEvent
import android.view.ViewConfiguration
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import rukiasoft.com.androidutilslibrary.resources.ResourcesManager
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
class AndroidUtils @Inject constructor(private val context: Context,
                                              private val resourcesManager: ResourcesManager) {

    @Suppress("DEPRECATION")
    fun fromHtml(html: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }

    fun isAppInstalled(packageName: String): Boolean {

        return try {
            context.packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }

    }

    fun pickFile(screen: Any, text: String, requestCode: Int) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        when (screen){
            is Activity -> screen.startActivityForResult(Intent.createChooser(intent, text), requestCode)
            is Fragment -> screen.startActivityForResult(Intent.createChooser(intent, text), requestCode)
            else -> throw RuntimeException()
        }
    }

    fun pickPicFromGallery(screen: Any, text: String, requestCode: Int) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_PICK
        when (screen){
            is Activity -> screen.startActivityForResult(Intent.createChooser(intent, text), requestCode)
            is Fragment -> screen.startActivityForResult(Intent.createChooser(intent, text), requestCode)
            else -> throw RuntimeException()
        }
    }

    fun pickVideoFromGallery(screen: Any, text: String, requestCode: Int) {
        val intent = Intent()
        intent.type = "video/*"
        intent.action = Intent.ACTION_PICK
        when (screen){
            is Activity -> screen.startActivityForResult(Intent.createChooser(intent, text), requestCode)
            is Fragment -> screen.startActivityForResult(Intent.createChooser(intent, text), requestCode)
            else -> throw RuntimeException()
        }
    }

    /**
     * the uri has to be passed with the content:// scheme, rather than file://
     * If not, the app will crash on devices running MarsMallow and above.
     * See https://inthecheesefactory.com/blog/how-to-share-access-to-file-with-fileprovider-on-android-nougat/en
     * for more details
     */
    fun takePicFromCamera(screen: Any, uri: Uri, requestCode: Int) {
        if(!uri.toString().contains("content://")){
            throw RuntimeException()
        }
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        launchCamera(screen, cameraIntent, requestCode)
    }

    fun takeVideoFromCamera(screen: Any, uri: Uri, requestCode: Int) {
        val cameraIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        launchCamera(screen, cameraIntent, requestCode)
    }

    private fun launchCamera(screen: Any, cameraIntent: Intent, requestCode: Int) {
        if (cameraIntent.resolveActivity(context.packageManager) != null) {
            when (screen){
                is Activity -> screen.startActivityForResult(cameraIntent, requestCode)
                is Fragment -> screen.startActivityForResult(cameraIntent, requestCode)
                else -> throw RuntimeException()
            }
        }
    }

    private fun launchCamera(fragment: Fragment, cameraIntent: Intent, requestCode: Int) {
        if (cameraIntent.resolveActivity(context.packageManager) != null) {
            fragment.startActivityForResult(cameraIntent, requestCode)
        }
    }


    fun canHandleIntent(intent: Intent): Boolean {
        return intent.resolveActivity(context.packageManager) != null
    }

    fun getVectorDrawable(id: Int): Drawable? {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            VectorDrawableCompat.create(resourcesManager.getResources(), id, null)
        } else {
            ContextCompat.getDrawable(context, id)
        }
    }

    fun getBitmapFromDrawable(drawable: Drawable?): Bitmap? {
        drawable?.let {
            val bitmap: Bitmap? = if (it.intrinsicWidth <= 0 || it.intrinsicHeight <= 0) {
                Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888) // Single color bitmap will be created of 1x1 pixel
            } else {
                Bitmap.createBitmap(it.intrinsicWidth, it.intrinsicHeight, Bitmap.Config.ARGB_8888)
            }

            if (it is BitmapDrawable) {
                if (it.bitmap != null) {
                    return it.bitmap
                }
            }

            val canvas = Canvas(bitmap)
            it.setBounds(0, 0, canvas.width, canvas.height)
            it.draw(canvas)
            return bitmap
        }
        return null
    }

    fun getNavBarHeight(): Int {
        val result = 0
        val hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey()
        val hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)

        if (!hasMenuKey && !hasBackKey) {
            //The device has a navigation bar
            val resources = context.resources

            val orientation = context.resources.configuration.orientation
            val resourceId: Int
            resourceId = resources.getIdentifier(if (orientation == Configuration.ORIENTATION_PORTRAIT)
                "navigation_bar_height"
            else
                "navigation_bar_width", "dimen", "android")


            if (resourceId > 0) {
                return context.resources.getDimensionPixelSize(resourceId)
            }
        }
        return result
    }

    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun getWidthInPixels() = context.resources.displayMetrics.widthPixels

    fun getHeightInPixels() = context.resources.displayMetrics.heightPixels

    fun pxToDp(px: Float): Float {
        return px / Resources.getSystem().displayMetrics.density
    }

    fun dpToPx(dp: Float): Float {
        return dp * Resources.getSystem().displayMetrics.density
    }

}