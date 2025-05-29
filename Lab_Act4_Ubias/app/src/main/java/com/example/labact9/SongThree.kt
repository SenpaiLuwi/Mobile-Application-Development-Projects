package com.example.labact9

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.labact9.databinding.ActivitySongThreeBinding


class SongThree : AppCompatActivity() {
    private lateinit var binding: ActivitySongThreeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var mediaPlayerThree = MediaPlayer.create(this, R.raw.songthreecatriona)

        var LyricsThree = ""

        binding.LyricsThree.text = LyricsThree

        binding.playsongThree.setOnClickListener {
             mediaPlayerThree.start()
         }

         binding.pausesongThree.setOnClickListener {
             mediaPlayerThree.pause()
         }

         binding.pausestopThree.setOnClickListener {
             mediaPlayerThree.stop()
             mediaPlayerThree.release()
             mediaPlayerThree = MediaPlayer.create(this, R.raw.songthreecatriona)
         }
    }

}