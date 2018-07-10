package rukiasoft.com.androidutilslibrary.preferences

import android.content.Context
import android.preference.PreferenceManager
import rukiasoft.com.androidutilslibrary.security.Encryption
import javax.inject.Inject
import javax.inject.Singleton


/**
 *  Copyright (C) Rukiasoft - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Raul <raulfeliz@gmail.com>, July 2018
 *
 * Class for loading and storing values on shared preferences
 */

@Singleton
class PreferencesManager @Inject constructor(private var context: Context) {

    fun getIntFromPreferences(key: String, defaultValue: Int = -1): Int{
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getInt(key, defaultValue)
    }

    fun getStringFromPreferences(key: String, defaultValue: String = ""): String{
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(key, defaultValue)
    }

    fun getEncryptedStringFromPreferences(key: String, alias: String, defaultValue: String = ""): String{
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val encrypted = prefs.getString(key, defaultValue)
        return Encryption.decryptString(encrypted, alias)
    }

    fun getBooleanFromPreferences(key: String, defaultValue: Boolean = false): Boolean{
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getBoolean(key, defaultValue)
    }

    fun getLongFromPreferences(key: String, defaultValue: Long = -1L): Long{
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getLong(key, defaultValue)
    }

    fun getFloatFromPreferences(key: String, defaultValue: Float = -1f): Float{
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getFloat(key, defaultValue)
    }

    fun setIntIntoPreferences(key: String, value: Int){
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putInt(key, value).apply()
    }

    fun setStringIntoPreferences(key: String, value: String){
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putString(key, value).apply()
    }

    fun setEncryptedStringIntoPreferences(key: String, value: String, alias: String): Boolean{
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val encryptedText = Encryption.encryptString(context, value, alias)
        return if(encryptedText.isNotBlank()) {
            prefs.edit().putString(key, encryptedText).apply()
            true
        }else{
            prefs.edit().putString(key, value).apply()
            false
        }
    }

    fun setBooleanIntoPreferences(key: String, value: Boolean){
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putBoolean(key, value).apply()
    }

    fun setLongIntoPreferences(key: String, value: Long){
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putLong(key, value).apply()
    }

    fun setFloatIntoPreferences(key: String, value: Float){
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putFloat(key, value).apply()
    }

    fun deleteVarFromSharedPreferences(key: String){
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().remove(key).apply()
    }

    fun containsKey(key: String) = PreferenceManager.getDefaultSharedPreferences(context).contains(key)

    fun clearAll(){
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().clear().apply()
    }
}