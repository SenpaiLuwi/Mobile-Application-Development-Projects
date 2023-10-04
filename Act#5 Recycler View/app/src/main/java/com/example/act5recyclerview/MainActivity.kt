package com.example.act5recyclerview

import Adapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.act5recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: Adapter
    private lateinit var list: ArrayList<Info>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.setHasFixedSize(true)

        list = ArrayList()
        list.add(Info(R.drawable.imageone,"'Kamiya, Hiroshi' \nVoice of 'Levi Ackerman' \nfrom the anime 'Attack on Titan'"))
        list.add(Info(R.drawable.imagetwo,"'Ishikawa, Yui' \nVoice of 'Violet Evergarden' \nfrom the anime 'Violet Evergarden'"))
        list.add(Info(R.drawable.imagethree,"'Hayami, Saori' \nVoice of 'Yor Forger' \nfrom the anime 'Spy x Family'"))
        list.add(Info(R.drawable.imagefour,"'Itou, Miku' \nVoice of  'Miku Nakano' \nfrom the anime 'The Quintessential Quintuplets'"))
        list.add(Info(R.drawable.imagefive,"'Taketatsu, Ayana' \nVoice of 'Azusa Nakano' \nfrom the anime 'K-On!'"))
        list.add(Info(R.drawable.imagesix,"'Sakura, Ayane' \nVoice of 'Nao Tomori' \nfrom the anime 'Charlotte'"))
        list.add(Info(R.drawable.imageseven,"'Matsuoka, Yoshitsugu' \nVoice of 'Kazuto Kirigaya' \nfrom the anime 'Sword Art Online'"))
        list.add(Info(R.drawable.imageeight,"'Masahiro, Tabuchi' \nFamous Japanese Actor"))
        list.add(Info(R.drawable.imagenine,"'Minase, Inori' \nVoice of 'Rem' \nfrom the anime 'Re:Zero'"))
        list.add(Info(R.drawable.imageten,"'Hanazawa, Kana' \nVoice of 'Kosaki Onodera' \nfrom the anime 'Nisekoi'"))
        list.add(Info(R.drawable.imageleven,"'Suzu, Honjo' \nFamous Japanese Actress"))
        list.add(Info(R.drawable.imagetwelve,"'Fukada, Eimi'  \nFamous Japanese Idol"))
        list.add(Info(R.drawable.imagetheen,"'Koyasu, Takehito'  \nVoice of 'Dio Brando' \nfrom the anime 'JoJo's Bizarre Adventure'"))


        adapter = Adapter(list)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
    }
}
