package com.rukiasoft.rukiapot

import com.rukiasoft.rukiapot.di.components.ComponentFactory
import com.rukiasoft.rukiapot.di.components.RukiaPotComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 *  Copyright (C) Rukiasoft - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Raul <raulfeliz@gmail.com>, July 2018
 *
 *
 */


class ApplicationBase: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val mComponent: RukiaPotComponent = ComponentFactory.component(this)
        mComponent.inject(this)
        return mComponent
    }


}