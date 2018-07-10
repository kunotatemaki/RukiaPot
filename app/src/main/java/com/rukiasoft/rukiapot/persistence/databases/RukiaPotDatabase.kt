package com.rukiasoft.rukiapot.persistence.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rukiasoft.rukiapot.persistence.daos.PotDao
import com.rukiasoft.rukiapot.persistence.entities.Pot
import rukiasoft.com.androidutilslibrary.persistence.RoomConverters


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

@Database(entities = [(Pot::class)], exportSchema = false,
        version = 1)
@TypeConverters(RoomConverters::class)
abstract class RukiaPotDatabase : RoomDatabase() {
    abstract fun potDao(): PotDao
}