package com.rukiasoft.rukiapot.di.modules

import com.rukiasoft.rukiapot.MainActivity
import com.rukiasoft.rukiapot.di.interfaces.CustomScopes
import dagger.Module
import dagger.android.ContributesAndroidInjector


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

@Module
abstract class ActivityBuilder {

    @CustomScopes.ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}