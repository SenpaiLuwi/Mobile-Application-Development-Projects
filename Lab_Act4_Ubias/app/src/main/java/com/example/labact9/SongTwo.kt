package com.example.labact9

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.labact9.databinding.ActivitySongTwoBinding

class SongTwo : AppCompatActivity() {
    private lateinit var binding: ActivitySongTwoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var mediaPlayer = MediaPlayer.create(this, R.raw.)

        var LyricsTwo = ""

        binding.LyricsTwo.text = LyricsTwo

        binding.playsongTwo.setOnClickListener {
            mediaPlayer.start()
        }

        binding.pausesongTwo.setOnClickListener {
            mediaPlayer.pause()
        }

        binding.pausestopTwo.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

}