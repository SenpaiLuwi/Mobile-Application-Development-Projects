package com.example.labact9

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.labact9.databinding.ActivitySongFiveBinding


class SongFive : AppCompatActivity() {
    private lateinit var binding: ActivitySongFiveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongFiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var mediaPlayerFive = MediaPlayer.create(this, R.raw.comethru)

        var LyricsFive = ""

        binding.LyricsFive.text = LyricsFive

        binding.playsongFive.setOnClickListener {
            mediaPlayerFive.start()
        }

        binding.pausesongFive.setOnClickListener {
            mediaPlayerFive.pause()
        }

        binding.pausestopFive.setOnClickListener {
            mediaPlayerFive.stop()
            mediaPlayerFive.release()
            mediaPlayerFive = MediaPlayer.create(this, R.raw.comethru)
        }
    }

}