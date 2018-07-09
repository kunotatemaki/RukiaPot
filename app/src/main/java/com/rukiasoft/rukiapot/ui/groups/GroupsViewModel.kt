package com.rukiasoft.rukiapot.ui.groups

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rukiasoft.rukiapot.persistence.PersistenceManager
import com.rukiasoft.rukiapot.persistence.entities.Pot
import rukiasoft.com.androidutilslibrary.switchMap
import javax.inject.Inject

class GroupsViewModel @Inject constructor(private val persistenceManager: PersistenceManager) : ViewModel() {
    // TODO: Implement the ViewModel
    private val query: MutableLiveData<Long> = MutableLiveData()
    private val pots: LiveData<Pot>
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
