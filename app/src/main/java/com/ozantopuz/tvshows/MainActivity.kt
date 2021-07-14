package com.ozantopuz.tvshows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ozantopuz.tvshows.ui.launch.LaunchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LaunchFragment.newInstance())
                .commitNow()
        }
    }
}