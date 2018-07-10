package com.rukiasoft.rukiapot

import android.os.Bundle
import com.rukiasoft.rukiapot.ui.common.BaseActivity
import com.rukiasoft.rukiapot.ui.pots.PotsFragment

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
