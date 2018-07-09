package com.rukiasoft.rukiapot

import android.os.Bundle
import com.rukiasoft.rukiapot.ui.groups.GroupsFragment
import com.rukiasoft.rukiapot.ui.groups.common.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, GroupsFragment.newInstance())
                    .commitNow()
        }
    }

}
