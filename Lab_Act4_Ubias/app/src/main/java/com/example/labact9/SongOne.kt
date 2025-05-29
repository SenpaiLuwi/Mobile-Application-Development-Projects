package com.example.labact9

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.labact9.databinding.ActivitySongOneBinding


class SongOne : AppCompatActivity() {
    private lateinit var binding: ActivitySongOneBinding
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongOneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mediaPlayer = MediaPlayer.create(this, R.raw.richflex)

        var lyricsOne = ""

        binding.LyricsOne.text = lyricsOne

        binding.playsongOne.setOnClickListener {
            mediaPlayer.start()
        }

        binding.pausesongOne.setOnClickListener {
            mediaPlayer.pause()
        }

        binding.pausestopOne.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

}
