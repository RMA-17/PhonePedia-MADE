package com.rmaprojects.phonepedia.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaprojects.phonepedia.R
import com.rmaprojects.phonepedia.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController(R.id.nav_host_fragment_content_main)

        val bottomNav = binding.bottomNav

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.favoriteGraph, R.id.nav_settings
            )
        )
        bottomNav.setupWithNavController(navController)
        setSupportActionBar(binding.topAppBar)

        navController.addOnDestinationChangedListener { _, destination ,_ ->
            val isTopBarVisible: Boolean
            val topBarTitle: String
            when (destination.id) {
                R.id.nav_home -> {
                    isTopBarVisible = true
                    topBarTitle = "Home"
                }
                R.id.nav_search -> {
                    isTopBarVisible = false
                    topBarTitle = "Search"
                }
                R.id.nav_settings -> {
                    isTopBarVisible = true
                    topBarTitle = "Settings"
                }
                R.id.nav_detail -> {
                    isTopBarVisible = false
                    topBarTitle = "Detail"
                }
                else -> {
                    isTopBarVisible = true
                    topBarTitle = "Favorite"
                }
            }
            binding.topAppBar.isVisible = isTopBarVisible
            binding.topAppBar.title = topBarTitle
            binding.bottomNav.isVisible = isTopBarVisible
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_appbar_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                navController.navigate(R.id.nav_search)
                true
            }
            else -> false
        }
    }

}