package com.example.numaboaterapia.appNavigation.pacient.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.pacient.fragments.PacientHomeFragment
import com.example.numaboaterapia.appNavigation.pacient.fragments.PacientPsiList
import com.example.numaboaterapia.appNavigation.pacient.fragments.ProfileFragment
import com.example.numaboaterapia.databinding.ActivityPacientAppViewBinding
import com.google.android.material.navigation.NavigationBarView


class PacientApp : AppCompatActivity() {
    private lateinit var binding: ActivityPacientAppViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPacientAppViewBinding.inflate(layoutInflater)
        setUpBottomNavigation()
        loadFragment(PacientHomeFragment())
        setContentView(binding.root)
    }

    private fun setUpBottomNavigation() {

        var bottomNavigation = binding.bottomNavigationView
        bottomNavigation.setOnItemSelectedListener(object :
            NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.search->{
                        loadFragment(PacientPsiList())
                    }
                    R.id.home->{
                        loadFragment(PacientHomeFragment())
                    }
                    else->{
                        loadFragment(ProfileFragment())
                    }
                }
                return true
            }

        })
    }

    private fun loadFragment(fragment: Fragment) {
        // carrega o fragmento na view
        supportFragmentManager.beginTransaction()
            .replace(R.id.pacient_home_layout, fragment)
            .commit()
    }
}



