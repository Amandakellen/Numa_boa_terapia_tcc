package com.example.numaboaterapia.appNavigation.psychologist.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityMyProfilePsiBinding

class MyProfilePsiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyProfilePsiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfilePsiBinding.inflate(layoutInflater)
        setUpViews()
        setUpProfileImage()
        setContentView(binding.root)
    }

    private fun setUpViews(){
        binding.myProfilePsiToolBar.getBackButton().setOnClickListener {
            finish()
        }
        binding.myProfilePsiToolBar.setTitleText(R.string.my_profile)
    }

    private fun setUpProfileImage(){
        Glide.with(this)
            .load(R.mipmap.psi_gray)
            .transform(CircleCrop())
            .into(binding.myprofilePsiPhoto)
    }
}