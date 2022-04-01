package com.laurennguyen.parstagram

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.laurennguyen.parstagram.fragments.ComposeFragment
import com.laurennguyen.parstagram.fragments.FeedFragment
import com.laurennguyen.parstagram.fragments.ProfileFragment
import com.parse.*
import java.io.File

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottom_navigation)
        val fragmentManager: FragmentManager = supportFragmentManager

        bottomNavigation.setOnItemSelectedListener {
            item ->

            var fragmentToShow: Fragment? = null

            when (item.itemId) {
                R.id.action_home -> {
                    fragmentToShow = FeedFragment()
                }
                R.id.action_compose -> {
                    fragmentToShow = ComposeFragment()
                }
                R.id.action_profile -> {
                    fragmentToShow = ProfileFragment()
                }
            }

            if (fragmentToShow != null) {
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }
            //Return true to say that we've handled the user interaction on this item
            true
        }

        // Set default selection
        bottomNavigation.selectedItemId = R.id.action_home

   //     queryPosts()
    }

    companion object {
        const val TAG = "MainActivity"
    }
}