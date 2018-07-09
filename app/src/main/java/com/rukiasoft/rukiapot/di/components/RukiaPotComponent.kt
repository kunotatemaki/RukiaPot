package com.rukiasoft.rukiapot.di.components

import com.rukiasoft.rukiapot.ApplicationBase
import com.rukiasoft.rukiapot.di.modules.ActivityBuilder
import com.rukiasoft.rukiapot.di.modules.FragmentProvider
import com.rukiasoft.rukiapot.di.modules.RukiaPotModule
import com.rukiasoft.rukiapot.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/* Copyright (C) Rukiasoft - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Raul <raulfeliz@gmail.com>, July 2018
 */

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class),
    (ActivityBuilder::class),
    (FragmentProvider::class),
    (RukiaPotModule::class),
    (ViewModelModule::class)])
interface RukiaPotComponent : AndroidInjector<ApplicationBase> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: ApplicationBase): Builder

        fun build(): RukiaPotComponent
    }

    override fun inject(app: ApplicationBase)
}
