package com.example.labact8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.labact8.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.register.setOnClickListener(){
            val Screen2: Intent = Intent(this, MainActivity2::class.java)
            val name = binding.nameTxt.text.toString()
            val conNum = binding.numberTxt.text.toString()
            val simNet = binding.simNetTxt.text.toString()
            val address = binding.addressTxt.text.toString()


            if(name.isNotEmpty() && conNum.isNotEmpty() && simNet.isNotEmpty() && address.isNotEmpty()){
                Screen2.putExtra("nameData", name)
                Screen2.putExtra("contactNumber", conNum)
                Screen2.putExtra("simNetork", simNet)
                Screen2.putExtra("address", address)
                startActivity(Screen2)
            } else
                Toast.makeText(applicationContext,"Please Fill up the Form", Toast.LENGTH_SHORT).show()
        }
    }
}