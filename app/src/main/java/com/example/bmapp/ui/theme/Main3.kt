package com.example.bmapp.ui.theme

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import com.example.bmapp.R
import com.example.bmapp.ui.theme.fragment.FavoriteFragment
import com.example.bmapp.ui.theme.fragment.HomeFragment
import com.example.bmapp.ui.theme.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Main3 : AppCompatActivity() {
    @SuppressLint("EmptyNavDeepLink")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navgraph)
        val navBar: BottomNavigationView = findViewById(R.id.bottomNav)
        val fragment = findViewById<FragmentContainerView>(R.id.fragmentContainerView)

        navBar.setOnItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.menu_home -> {
                        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,HomeFragment.newInstance("","")).commit()
                    }

                    R.id.menu_fav -> {
                        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,FavoriteFragment.newInstance("","")).commit()
                    }

                    R.id.menu_pro -> {
                        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,ProfileFragment.newInstance("","")).commit()
                    }
                }
                return true
            }
        })
    }
}
