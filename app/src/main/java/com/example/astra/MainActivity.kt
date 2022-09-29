package com.example.astra

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.astra.databinding.ActivityMainBinding
import com.example.astra.databinding.ActivitySplashBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var firebaseAuth: FirebaseAuth
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)
        binding.signupSlide.setOnClickListener{
         binding.signupSlide.background=resources.getDrawable(R.drawable.switch_trick,null)
            binding.signupSlide.setTextColor(resources.getColor(R.color.textcolor,null))
            binding.loginSlide.background=null
            binding.SignUpLayout.visibility= View.VISIBLE
            binding.LogInLayout.visibility=View.GONE
            binding.loginButton.visibility=View.GONE
            binding.signupButton.visibility=View.VISIBLE
            binding.loginSlide.setTextColor(resources.getColor(R.color.mycolor,null))
        }
        binding.loginSlide.setOnClickListener {
         binding.signupSlide.background=null
            binding.signupSlide.setTextColor(resources.getColor(R.color.mycolor,null))
            binding.loginSlide.background=resources.getDrawable(R.drawable.switch_trick,null)
            binding.SignUpLayout.visibility= View.GONE
            binding.LogInLayout.visibility=View.VISIBLE
            binding.loginButton.visibility=View.VISIBLE
            binding.signupButton.visibility=View.GONE
            binding.loginSlide.setTextColor(resources.getColor(R.color.textcolor,null))
        }
        binding.signupButton.setOnClickListener {
            val email = binding.SignUpEmail.text.toString()
          val password = binding.SignUpPassword.text.toString()
            val confirmPassword = binding.SignUpConfirmPassword.text.toString()
            if (email.isNotEmpty()&&password.isNotEmpty()&&confirmPassword.isNotEmpty())
            {
                if (password==confirmPassword)
                {
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                        if (it.isSuccessful)
                        {
                            Toast.makeText(this,"Account is successfully created",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                        }
                        else
                        {
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else
                {
                    Toast.makeText(this,"Password does not match",Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(this,"Empty fields are not allowed",Toast.LENGTH_SHORT).show()
            }
        }
        binding.loginButton.setOnClickListener {
            val email  = binding.LogInEmail.text.toString()
            val password = binding.LogInPassword.text.toString()

            if (email.isNotEmpty()&&password.isNotEmpty())
            {
                    firebaseAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener {
                        if (it.isSuccessful)
                        {
                                val intent = Intent(this,NavigationDrawer::class.java)
                                startActivity(intent)
                            Toast.makeText(this,"welcome",Toast.LENGTH_SHORT).show()
                        }
                        else
                        {
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            else
            {
                Toast.makeText(this,"Empty fields are not allowed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}