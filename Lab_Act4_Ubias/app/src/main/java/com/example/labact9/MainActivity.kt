package com.example.labact9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.labact9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SongOne.setOnClickListener() {
            val ScreenSongOne: Intent = Intent(this, SongOne::class.java)
            startActivity(ScreenSongOne)
        }

        binding.SongTwo.setOnClickListener() {
            val ScreenSongTwo: Intent = Intent(this, SongOne::class.java)
            startActivity(ScreenSongTwo)
        }

        binding.SongThree.setOnClickListener() {
            val ScreenSongThree: Intent = Intent(this, SongOne::class.java)
            startActivity(ScreenSongThree)
        }

        binding.SongFour.setOnClickListener() {
            val ScreenSongFour: Intent = Intent(this, SongOne::class.java)
            startActivity(ScreenSongFour)
        }

        binding.SongFive.setOnClickListener() {
            val ScreenSongFive: Intent = Intent(this, SongOne::class.java)
            startActivity(ScreenSongFive)
        }
    }
}