package com.rukiasoft.rukiapot

import com.rukiasoft.rukiapot.di.components.ComponentFactory
import com.rukiasoft.rukiapot.di.components.RukiaPotComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class ApplicationBase: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val mComponent: RukiaPotComponent = ComponentFactory.component(this)
        mComponent.inject(this)
        return mComponent
    }


    override fun onCreate() {
        super.onCreate()
    }
}