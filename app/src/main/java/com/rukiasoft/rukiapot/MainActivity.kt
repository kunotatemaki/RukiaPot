package com.rukiasoft.rukiapot

import android.os.Bundle
import com.rukiasoft.rukiapot.ui.common.BaseActivity
import com.rukiasoft.rukiapot.ui.pots.PotsFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PotsFragment.newInstance())
                    .commitNow()
        }
    }

}
