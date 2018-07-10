package rukiasoft.com.androidutilslibrary.storage

import android.annotation.SuppressLint
import android.content.Context
import android.os.Environment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
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

class StorageManager @Inject constructor(private val context: Context){

    @SuppressLint("SimpleDateFormat")
    fun createImageFile(): File? {
        // Create an image file name
        return try {
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val imageFileName = "JPEG_" + timeStamp + "_"
            createImageFile(imageFileName)
        }catch (ex: IOException){
            null
        }
    }

    fun createImageFile(name: String): File? {
        // Create an image file name
        return try {
            val storageDir = context.filesDir
            val image = File.createTempFile(
                    name, /* prefix */
                    ".jpg", /* suffix */
                    storageDir      /* directory */
            )

            // Save a file: path for use with ACTION_VIEW intents
            image
        } catch (ex: IOException) {
            null
        }
    }


    @SuppressLint("SimpleDateFormat")
    fun createVideoFile(): File? {
        // Create an image file name
        return try {
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val imageFileName = "VIDEO_" + timeStamp + "_"
            createVideoFile(imageFileName)
        }catch (ex: IOException){
            null
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun createVideoFile(name: String): File? {
        // Create an image file name
        return try {
            val storageDir = context.filesDir
            val image = File.createTempFile(
                    name, /* prefix */
                    ".mp4", /* suffix */
                    storageDir      /* directory */
            )

            // Save a file: path for use with ACTION_VIEW intents
            image
        }catch (ex: IOException){
            null
        }
    }

    fun deleteInternalStorage(){
        context.filesDir.listFiles().toList().forEach{
            it.delete()
        }
    }

    fun deleteExternalStorage(){
        context.getExternalFilesDir(null)?.listFiles()?.toList()?.forEach{
            it?.delete()
        }
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.listFiles()?.toList()?.forEach{
            it?.delete()
        }
        context.getExternalFilesDir(Environment.DIRECTORY_MOVIES)?.listFiles()?.toList()?.forEach{
            it?.delete()
        }
    }

}