package com.example.act1_loginscreen

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.act1_loginscreen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener(){
        val userName:String = binding.editUser.text.toString()
        val userPass:String = binding.editPass.text.toString()

        if(userName.isNotEmpty() && userPass.isNotEmpty()) {
            Toast.makeText(applicationContext, "Logging in...", Toast.LENGTH_LONG).show()
            binding.editUser.text.clear()
            binding.editPass.text.clear()
        }
        else
        Toast.makeText(applicationContext, "Please Enter Valid Details", Toast.LENGTH_LONG).show()
        }
    }
}