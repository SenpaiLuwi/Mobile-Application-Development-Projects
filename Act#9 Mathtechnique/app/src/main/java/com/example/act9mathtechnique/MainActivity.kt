package com.example.act9mathtechnique

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var btnAddition: Button
    private lateinit var btnSubtraction: Button
    private lateinit var btnMultiplication: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddition = findViewById(R.id.btn_id_add)
        btnSubtraction = findViewById(R.id.btn_id_subtract)
        btnMultiplication = findViewById(R.id.btn_id_multiplication)

        btnAddition.setOnClickListener {
            startGameActivity("addition")
        }

        btnSubtraction.setOnClickListener {
            startGameActivity("subtraction")
        }

        btnMultiplication.setOnClickListener {
            startGameActivity("multiplication")
        }
    }

    private fun startGameActivity(operation: String) {
        val intentGame = Intent(this, Game::class.java)
        intentGame.putExtra("operation", operation)
        startActivity(intentGame)
        finish()
    }
}
