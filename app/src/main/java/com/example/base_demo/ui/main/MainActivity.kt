package com.example.base_demo.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.base_demo.R
import com.example.base_demo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private val topLevelDestinations = setOf(R.id.nav_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment).apply {
                navController.addOnDestinationChangedListener(this@MainActivity)
                toolbar.setupWithNavController(navController, AppBarConfiguration(topLevelDestinations))
            }
        }
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {

    }

    //Navigating for BottomNavigation
    private val navigateDestination: (Int, NavController) -> Unit = { id, navController ->
        val currentDestinationId = navController.currentDestination?.id
        if (id != currentDestinationId)
            navController.navigate(id, null, navOptions { popUpTo(id) { inclusive = true } })
    }


}