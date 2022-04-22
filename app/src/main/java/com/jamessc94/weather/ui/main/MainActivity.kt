package com.jamessc94.weather.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.jamessc94.weather.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.activity_main_frag))

    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.activity_main_frag)
//        val appBarConfiguration : AppBarConfiguration = AppBarConfiguration(navController.graph)
//
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//
//    }

}
