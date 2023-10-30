package com.example.act9mathtechnique

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class result : AppCompatActivity() {
    private lateinit var resultText: TextView
    private lateinit var playAgainButton: Button
    private lateinit var exitButton: Button
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        resultText = findViewById(R.id.txtview_id_result)
        playAgainButton = findViewById(R.id.btn_id_play)
        exitButton = findViewById(R.id.btn_id_exit)

        val intentResult = intent
        score = intentResult.getIntExtra("score", 0)
        resultText.text = score.toString()

        playAgainButton.setOnClickListener {
            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
            finish()
        }

        exitButton.setOnClickListener {
            finish()
        }
    }
}
