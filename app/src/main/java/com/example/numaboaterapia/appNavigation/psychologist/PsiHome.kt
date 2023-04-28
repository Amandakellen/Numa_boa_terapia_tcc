package com.example.numaboaterapia.appNavigation.psychologist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.pacient.fragments.PacientHomeFragment
import com.example.numaboaterapia.appNavigation.pacient.fragments.PacientPsiList
import com.example.numaboaterapia.databinding.ActivityPsiHomeBinding
import com.google.android.material.navigation.NavigationBarView

class PsiHome : AppCompatActivity() {
    private lateinit var binding: ActivityPsiHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsiHomeBinding.inflate(layoutInflater)
        setUpViews()
        setContentView(binding.root)
    }


    private fun setUpViews() {
        binding.toolBarPsiHome.getBackButton().visibility = View.INVISIBLE
        var bottomNavigation = binding.PsibottomNavigationView
        bottomNavigation.setOnItemSelectedListener(object :
            NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.psi_home->{
                        //loadFragment()
                    }
                    R.id.sessoes->{
                        //loadFragment()
                    }
                    else->{
                        //todo
                    }
                }
                return true
            }

        })
    }

    private fun loadFragment(fragment: Fragment) {
        // carrega o fragmento na view
        supportFragmentManager.beginTransaction()
            .replace(R.id.psi_home_layout, fragment)
            .commit()
    }
}