package com.example.astra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputBinding
import com.example.astra.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        binding.image.alpha =0f
        binding.image.animate().setDuration(1000).alpha(1f).withEndAction{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }
}