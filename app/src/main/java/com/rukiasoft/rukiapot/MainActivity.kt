package com.rukiasoft.rukiapot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rukiasoft.rukiapot.ui.groups.GroupsFragment

class MainActivity : AppCompatActivity() {

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
