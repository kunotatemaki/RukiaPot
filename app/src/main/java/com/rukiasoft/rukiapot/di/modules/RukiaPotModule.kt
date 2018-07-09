package com.rukiasoft.rukiapot.di.modules

import android.content.Context
import androidx.room.Room
import com.rukiasoft.rukiapot.ApplicationBase
import com.rukiasoft.rukiapot.persistence.PersistenceManager
import com.rukiasoft.rukiapot.persistence.PersistenceManagerImpl
import com.rukiasoft.rukiapot.persistence.databases.RukiaPotDatabase
import com.rukiasoft.rukiapot.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/* Copyright (C) Rukiasoft - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Raul <raulfeliz@gmail.com>, July 2018
 */

@Module(includes = [(ViewModelModule::class)])
class RukiaPotModule {

    @Provides
    fun providesPersistenceManager(persistenceManager: PersistenceManagerImpl): PersistenceManager {
        return persistenceManager
    }

    @Provides
    fun providesContext(application: ApplicationBase): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideDb(app: ApplicationBase): RukiaPotDatabase {

        return Room.databaseBuilder(app,
                RukiaPotDatabase::class.java, Constants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

    }

}