package rukiasoft.com.androidutilslibrary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import java.lang.ref.WeakReference


/* Copyright (C) Rukiasoft - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Raul <raulfeliz@gmail.com>, July 2018
 */

fun <T> WeakReference<T>.safe(body : T.() -> Unit) {
    this.get()?.body()
}

fun <X, Y> LiveData<X>.switchMap(func: (X) -> LiveData<Y>)
        = Transformations.switchMap(this, func)!!
/*fun <X, Y> LiveData<X>.switchMap(func: (X) -> LiveData<Y>)
        = Transformations.switchMap(this, func)*/


fun <T> LiveData<T>.getDistinct(): LiveData<T> {
    val distinctLiveData = MediatorLiveData<T>()
    distinctLiveData.addSource(this, object : Observer<T> {
        private var initialized = false
        private var lastObj: T? = null
        override fun onChanged(obj: T?) {
            if (!initialized) {
                initialized = true
                lastObj = obj
                distinctLiveData.postValue(lastObj)
            } else if ((obj == null && lastObj != null)
                    || obj != lastObj) {
                lastObj = obj
                distinctLiveData.postValue(lastObj)
            }
        }
    })
    return distinctLiveData
}