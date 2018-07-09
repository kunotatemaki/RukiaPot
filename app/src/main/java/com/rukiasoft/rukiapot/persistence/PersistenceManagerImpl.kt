package com.rukiasoft.rukiapot.persistence

import androidx.lifecycle.LiveData
import com.rukiasoft.rukiapot.persistence.databases.RukiaPotDatabase
import com.rukiasoft.rukiapot.persistence.entities.Pot
import javax.inject.Inject


/* Copyright (C) Rukiasoft - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Raul <raulfeliz@gmail.com>, July 2018
 */

class PersistenceManagerImpl  @Inject constructor(private val db: RukiaPotDatabase) : PersistenceManager{

    companion object {
        /**
         * A good page size is a value that fills at least a screen worth of content on a large
         * device so the User is unlikely to see a null item.
         * You can play with this constant to observe the paging behavior.
         * <p>
         * It's possible to vary this with list device size, but often unnecessary, unless a user
         * scrolling on a large device is expected to scroll through items more quickly than a small
         * device, such as when the large device uses a grid layout of items.
         */
        private const val PAGE_SIZE = 30
    }

    override fun insertPot(pot: Pot) {
        db.potDao().insert(pot)
    }

    override fun getPotsByDate(descendingSorted: Boolean): LiveData<Pot> {
        return when(descendingSorted){
            true -> db.potDao().getPotsByDescendingAccessedDate()
            false -> db.potDao().getPotsByAscendingAccessedDate()
        }
    }

    override fun getPotsByName(ascendingSorted: Boolean) : LiveData<Pot> {
        return when(ascendingSorted){
            true -> db.potDao().getPotsByAscendingName()
            false -> db.potDao().getPotsByDescendingName()
        }
    }
}