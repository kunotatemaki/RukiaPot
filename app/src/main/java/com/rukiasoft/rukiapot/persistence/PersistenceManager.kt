package com.rukiasoft.rukiapot.persistence

import androidx.lifecycle.LiveData
import com.rukiasoft.rukiapot.persistence.entities.Pot


/* Copyright (C) Rukiasoft - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Raul <raulfeliz@gmail.com>, July 2018
 */

interface PersistenceManager {
    fun insertPot(pot: Pot)
    fun getPotsByDate(descendingSorted: Boolean = true): LiveData<List<Pot>>
    fun getPotsByName(ascendingSorted: Boolean = true): LiveData<List<Pot>>
}