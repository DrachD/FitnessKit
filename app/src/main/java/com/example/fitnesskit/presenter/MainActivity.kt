package com.example.fitnesskit.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.fitnesskit.R
import com.example.fitnesskit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        setSupportActionBar(binding.toolbar)

        val navController = rootNavController()
        prepareRootNavController(navController)
    }

    private fun rootNavController(): NavController {
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        return navHost.navController
    }

    private fun prepareRootNavController(navController: NavController) {
        val graph = navController.navInflater.inflate(getMainNavigationGraph())
        graph.setStartDestination(getTabsDestination())
        navController.graph = graph
    }

    private fun getMainNavigationGraph() = R.navigation.main_graph
    private fun getTabsDestination() = R.id.tabsFragment
}