package com.rukiasoft.rukiapot.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.rukiasoft.rukiapot.persistence.entities.Pot
import rukiasoft.com.androidutilslibrary.persistence.BaseDao


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

@Dao
abstract class PotDao : BaseDao<Pot> {

    @Query("SELECT * FROM pot ORDER BY open DESC, accessed DESC")
    abstract fun getPotsByDescendingAccessedDate(): LiveData<List<Pot>>

    @Query("SELECT * FROM pot ORDER BY open DESC, accessed ASC")
    abstract fun getPotsByAscendingAccessedDate(): LiveData<List<Pot>>

    @Query("SELECT * FROM pot ORDER BY open DESC, name ASC")
    abstract fun getPotsByAscendingName(): LiveData<List<Pot>>

    @Query("SELECT * FROM pot ORDER BY open DESC, name DESC")
    abstract fun getPotsByDescendingName(): LiveData<List<Pot>>

}