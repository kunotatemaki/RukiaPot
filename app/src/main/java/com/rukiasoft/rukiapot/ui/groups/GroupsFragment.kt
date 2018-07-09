package com.rukiasoft.rukiapot.ui.groups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.rukiasoft.rukiapot.R
import com.rukiasoft.rukiapot.ui.groups.common.BaseFragment

class GroupsFragment : BaseFragment() {

    companion object {
        fun newInstance() = GroupsFragment()
    }

    private lateinit var viewModel: GroupsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.groups_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GroupsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
