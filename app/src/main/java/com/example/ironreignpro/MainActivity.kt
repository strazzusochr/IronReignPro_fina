package com.example.ironreignpro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ironreignpro.databinding.ActivityMainBinding

/**
 * The main activity sets up the navigation host and bottom navigation bar.  All
 * primary fragments are managed via the Navigation component.  View binding
 * eliminates boilerplate findViewById calls.  Errors during navigation are
 * logged for debugging purposes.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout using view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the NavController from the host fragment
        val navController = findNavController(R.id.nav_host_fragment)
        // Link the BottomNavigationView with the NavController so that selections
        // navigate to the correct destinations defined in nav_graph.xml
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}