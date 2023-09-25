package com.example.act6gridview


import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var gridView: GridView
    private val textList = ArrayList<String>()
    private val imageList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridView = findViewById(R.id.gridView)
        fillArrays()

        val adapter = GridAdapter(textList, imageList)
        gridView.adapter = adapter

        gridView.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(applicationContext, "TARA LARO NG ${textList[position]}", Toast.LENGTH_LONG).show()
        }
    }

    private fun fillArrays() {
        textList.add("Genshin Impact")
        textList.add("Minecraft")
        textList.add("Apex Legends")
        textList.add("GTA V")
        textList.add("Tekken 7")
        textList.add("The Last of Us")
        textList.add("Valorant")
        textList.add("Yakuza 0")
        textList.add("Plants vs Zombies")
        textList.add("Persona 5")
        textList.add("Fallout New Vegas")
        textList.add("CSGO")
        textList.add("Cuphead")
        textList.add("Fortnite")
        textList.add("Undetale")
        textList.add("Among Us")
        textList.add("Identity V")
        textList.add("MLBB")

        imageList.add(R.drawable.genshin)
        imageList.add(R.drawable.minecraft)
        imageList.add(R.drawable.apexlegends)
        imageList.add(R.drawable.gtav)
        imageList.add(R.drawable.tekken)
        imageList.add(R.drawable.lastofus)
        imageList.add(R.drawable.valorant)
        imageList.add(R.drawable.yakuza)
        imageList.add(R.drawable.pvz)
        imageList.add(R.drawable.persona)
        imageList.add(R.drawable.fallout)
        imageList.add(R.drawable.csgo)
        imageList.add(R.drawable.cuphead)
        imageList.add(R.drawable.fortnite)
        imageList.add(R.drawable.undertale)
        imageList.add(R.drawable.amongus)
        imageList.add(R.drawable.identityv)
        imageList.add(R.drawable.mlbb)

    }
}

