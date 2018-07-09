package com.rukiasoft.rukiapot.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rukiasoft.rukiapot.VMFactory
import com.rukiasoft.rukiapot.di.interfaces.ViewModelKey
import com.rukiasoft.rukiapot.ui.groups.GroupsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import rukiasoft.com.androidutilslibrary.ViewModelFactory

/* Copyright (C) Rukiasoft - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Raul <raulfeliz@gmail.com>, July 2018
 */

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GroupsViewModel::class)
    internal abstract fun bindGroupsViewModel(groupsViewModel: GroupsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: VMFactory): ViewModelProvider.Factory
}