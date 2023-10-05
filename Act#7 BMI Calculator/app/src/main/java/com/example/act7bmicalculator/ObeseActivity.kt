package com.example.act7bmicalculator

import android.content.Intent
import android.view.View
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ObeseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.obese)

        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val middleInitial = intent.getStringExtra("middleInitial")
        val gender = intent.getStringExtra("gender")
        val bmi = intent.getFloatExtra("bmi", 0.0f)

        val nameView = findViewById<TextView>(R.id.nameView)
        val genderView = findViewById<TextView>(R.id.genderView)
        val BMIView = findViewById<TextView>(R.id.BMIView)
        val imageView = findViewById<ImageView>(R.id.imageView)

        val fullName = "$firstName $middleInitial $lastName"

        nameView.text = fullName
        genderView.text = gender
        BMIView.text = String.format("%.2f", bmi)

        if (gender == "Mr") {
            imageView.setImageResource(R.drawable.lalaki)
        } else if (gender == "Ms") {
            imageView.setImageResource(R.drawable.babae)
        }
    }

    fun goToMainActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
