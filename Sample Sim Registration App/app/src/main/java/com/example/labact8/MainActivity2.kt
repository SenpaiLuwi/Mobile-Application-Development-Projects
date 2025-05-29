package com.example.labact8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.labact8.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val regName = intent.getStringExtra("nameData")
        val regNum = intent.getStringExtra("contactNumber")
        val regSim = intent.getStringExtra("simNetork")
        val regAddress =  intent.getStringExtra("address")
        val regDetails = "Here are Your Details!\n\n\n$regName\n\n$regNum\n\n$regSim\n\n$regAddress"

        Toast.makeText(applicationContext, "Sim Registration Complete!", Toast.LENGTH_LONG).show()
        binding.regDetails.setText(regDetails)
    }
}