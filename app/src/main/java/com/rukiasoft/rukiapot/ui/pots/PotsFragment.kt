package com.rukiasoft.rukiapot.ui.pots

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rukiasoft.rukiapot.R
import com.rukiasoft.rukiapot.ui.common.BaseFragment
import timber.log.Timber

class PotsFragment : BaseFragment() {

    companion object {
        fun newInstance() = PotsFragment()
    }

    private lateinit var viewModel: PotsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.groups_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PotsViewModel::class.java)
        // TODO: Use the ViewModel
        viewModel.getPots().observe(this, Observer {
            it?.let {
                Timber.d("hay alguno")
            }
        })
    }

}
