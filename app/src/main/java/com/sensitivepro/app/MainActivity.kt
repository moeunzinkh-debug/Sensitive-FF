package com.sensitivepro.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sensitivepro.app.ui.fragments.DeviceFragment
import com.sensitivepro.app.ui.fragments.GeneratorFragment
import com.sensitivepro.app.ui.fragments.HomeFragment
import com.sensitivepro.app.ui.fragments.WeaponsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottom_nav)

        // Load default fragment
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        bottomNav.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.navigation_home -> HomeFragment()
                R.id.navigation_device -> DeviceFragment()
                R.id.navigation_weapons -> WeaponsFragment()
                R.id.navigation_generator -> GeneratorFragment()
                else -> HomeFragment()
            }
            loadFragment(fragment)
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }

    fun navigateToDevice() {
        bottomNav.selectedItemId = R.id.navigation_device
    }

    fun navigateToWeapons() {
        bottomNav.selectedItemId = R.id.navigation_weapons
    }
}
