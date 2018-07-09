package com.rukiasoft.rukiapot.di.modules

import com.rukiasoft.rukiapot.di.interfaces.CustomScopes
import com.rukiasoft.rukiapot.ui.groups.GroupsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


/* Copyright (C) Rukiasoft - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Raul <raulfeliz@gmail.com>, July 2018
 */

@Module
abstract class FragmentProvider {

    @CustomScopes.FragmentScope
    @ContributesAndroidInjector
    abstract fun provideGroupsFragmentFactory(): GroupsFragment

}