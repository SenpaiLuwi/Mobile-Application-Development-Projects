package com.example.labact9

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.labact9.databinding.ActivitySongFourBinding


class SongFour : AppCompatActivity() {
    private lateinit var binding: ActivitySongFourBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongFourBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var mediaPlayerFour = MediaPlayer.create(this, R.raw.)

        var LyricsFour = ""

        binding.LyricsFour.text = LyricsFour

         binding.playsongFour.setOnClickListener {
             mediaPlayerFour.start()
         }

         binding.pausesongFour.setOnClickListener {
             mediaPlayerFour.pause()
         }

         binding.pausestopFour.setOnClickListener {
             mediaPlayerFour.stop()
             mediaPlayerFour.release()

         }
    }

}