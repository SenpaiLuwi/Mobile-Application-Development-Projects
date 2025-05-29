package com.example.labact_ubias

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.labact_ubias.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var User:String = "admin123"
    private var Pass:String = "adminpass123"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.LoginBtn.setOnClickListener(){
            val userEmail:String = binding.EmailTxt.text.toString()
            val userPass:String = binding.Passwordtext.text.toString()
            if(userEmail == User){
                //If the User Enters an Email Correct
                Toast.makeText(applicationContext, "Logging in...", Toast.LENGTH_LONG).show()
            }
            if(userPass == Pass){
                //If the User Enters the Password Correct
                Toast.makeText(applicationContext, "Logging in...", Toast.LENGTH_LONG).show()
            }
            else if(userEmail == ""){
                //If the User didn't enter an Email
                Toast.makeText(applicationContext, "Please Enter Your Email/Username", Toast.LENGTH_LONG).show()
            }
            else if(userPass == ""){
                //If the User didn't enter a Password
                Toast.makeText(applicationContext, "Please Enter Your Password", Toast.LENGTH_LONG).show()
            }
            else {
                //If the User Enters the Username and Password Incorrect
                Toast.makeText(applicationContext, "The Email/Username and/or Password is Incorrect", Toast.LENGTH_LONG).show()
            }
        }
    }


}
