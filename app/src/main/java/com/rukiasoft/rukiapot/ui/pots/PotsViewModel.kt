package com.rukiasoft.rukiapot.ui.pots

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rukiasoft.rukiapot.persistence.PersistenceManager
import com.rukiasoft.rukiapot.persistence.entities.Pot
import rukiasoft.com.androidutilslibrary.switchMap
import javax.inject.Inject

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


class PotsViewModel @Inject constructor(private val persistenceManager: PersistenceManager) : ViewModel() {
    // TODO: Implement the ViewModel
    private val query: MutableLiveData<Long> = MutableLiveData()
    private val pots: LiveData<List<Pot>>
    private var byDate: Boolean = true
    private var ascending: Boolean = true

    init {
        query.value = System.currentTimeMillis()
        pots = query.switchMap{
            when(byDate){
                true -> persistenceManager.getPotsByDate(descendingSorted = !ascending)
                false -> persistenceManager.getPotsByName(ascendingSorted = ascending)
            }
        }
    }

    fun getPots() = pots
}
