package com.example.act7bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.act7bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var chooseGender: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.maleBtn.setOnClickListener {
            if (binding.maleBtn.isChecked) {
                chooseGender = "Sir"
                binding.femaleBtn.isChecked = false
            } else {
                binding.maleBtn.isChecked = true
            }
        }

        binding.femaleBtn.setOnClickListener {
            if (binding.femaleBtn.isChecked) {
                chooseGender = "Ma'am"
                binding.maleBtn.isChecked = false
            } else {
                binding.femaleBtn.isChecked = true
            }
        }

        binding.subBtn.setOnClickListener {
            val firstName = binding.editFirst.text.toString()
            val middleInitial = binding.editMI.text.toString()
            val lastName = binding.editLast.text.toString()
            val heightStr = binding.editCM.text.toString()
            val weightStr = binding.editKG.text.toString()

            if (firstName.isNotEmpty() && lastName.isNotEmpty() && middleInitial.isNotEmpty() &&
                heightStr.isNotEmpty() && weightStr.isNotEmpty()) {
                val height = heightStr.toFloat()
                val weight = weightStr.toFloat()

                if (chooseGender == null) {
                    Toast.makeText(applicationContext, "Please select a Gender", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val compbmi = computeBMI(height, weight)
                val intent = when {
                    compbmi < 18.5 -> Intent(this, UnderweightActivity::class.java)
                    compbmi < 25   -> Intent(this, HealthyActivity::class.java)
                    compbmi < 30   -> Intent(this, OverweightActivity::class.java)
                    else       -> Intent(this, ObeseActivity::class.java)
                }


                intent.putExtra("firstName", firstName)
                intent.putExtra("middleInitial", middleInitial)
                intent.putExtra("lastName", lastName)
                intent.putExtra("gender", chooseGender)
                intent.putExtra("bmi", compbmi)
                startActivity(intent)
                this.finish()
            } else {
                Toast.makeText(applicationContext, "Please fill out all fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun computeBMI(height: Float, weight: Float): Float {
        val heightInMeters = height / 100
        return if (heightInMeters > 0) {
            weight / (heightInMeters * heightInMeters)
        } else {
            0.0f
        }
    }
}
