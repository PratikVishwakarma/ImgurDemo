package com.pratik.imgurdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pratik.imgurdemo.ui.view.ImageListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        try {
            val frag = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            frag?.let {
                val fragment = it.childFragmentManager.fragments[0]
                if (fragment != null && fragment is ImageListFragment) {
                    finish()
                }
            }
            super.onBackPressed()
        } catch (e: Exception) {
            super.onBackPressed()
        }
    }

}