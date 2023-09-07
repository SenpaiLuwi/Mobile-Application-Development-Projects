package com.example.act3_radiobutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.act3_radiobutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val defaultCharacterInfo = CharacterInfo(
        R.drawable.gordon,
        "",
        "",
        "",
        "",
        "",
        ""
    )
    private var selectedCharacterInfo = defaultCharacterInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the checkboxes
        val checkboxes = arrayOf(
            binding.amberBox,
            binding.eulaBox,
            binding.chongyunBox,
            binding.hutaoBox,
            binding.gorouBox,
            binding.yoimiyaBox,
            binding.cynoBox,
            binding.nahidaBox
        )

        // Initialize the radio buttons
        val radioButtons = arrayOf(
            binding.themeOne,
            binding.themeTwo,
            binding.themeThree
        )

        // Set the default character info and image
        displayCharacterInfo(defaultCharacterInfo)

        // Set a click listener for each checkbox
        for (checkbox in checkboxes) {
            checkbox.setOnClickListener {
                if (checkbox.isChecked) {
                    // Uncheck all other checkboxes
                    for (otherCheckbox in checkboxes) {
                        if (otherCheckbox != checkbox) {
                            otherCheckbox.isChecked = false
                        }
                    }
                    // Display character info based on the clicked checkbox
                    selectedCharacterInfo = when (checkbox) {
                        binding.amberBox -> CharacterInfo(
                            R.drawable.amber,
                            "Date Released - September 28, 2020",
                            "Gliding Champion",
                            "Amber",
                            "Pyro",
                            "Bow",
                            "Mondstadt"
                        )
                        binding.eulaBox -> CharacterInfo(
                            R.drawable.eula,
                            "Date Released - September 28, 2020",
                            "Dance of the Shimmering Wave",
                            "Eula",
                            "Cryo",
                            "Claymore",
                            "Mondstadt"
                        )
                        binding.chongyunBox -> CharacterInfo(
                            R.drawable.chongyun,
                            "Date Released - September 28, 2020",
                            "Frozen Ardor",
                            "Chongyun",
                            "Cryo",
                            "Claymore",
                            "Liyue"
                        )
                        binding.hutaoBox -> CharacterInfo(
                            R.drawable.hutao,
                            "Date Released - March 02, 2021",
                            "Fragrance in Thaw",
                            "Hu Tao",
                            "Pyro",
                            "Polearm",
                            "Liyue"
                        )
                        binding.gorouBox -> CharacterInfo(
                            R.drawable.gorou,
                            "Date Released - December 14, 2021",
                            "Canine Warrior",
                            "Gorou",
                            "Geo",
                            "Bow",
                            "Inazuma"
                        )
                        binding.yoimiyaBox -> CharacterInfo(
                            R.drawable.yoimiya,
                            "Date Released - August 10, 2021",
                            "Frolicking Flames",
                            "Yoimiya",
                            "Pyro",
                            "Bow",
                            "Inazuma"
                        )
                        binding.cynoBox -> CharacterInfo(
                            R.drawable.cyno,
                            "Date Released - September 28, 2022",
                            "Judicator of Secrets",
                            "Cyno",
                            "Electro",
                            "Polearm",
                            "Sumeru"
                        )
                        binding.nahidaBox -> CharacterInfo(
                            R.drawable.nahida,
                            "Date Released - November 02, 2022",
                            "Physic of Purity",
                            "Nahida",
                            "Dendro",
                            "Catalyst",
                            "Sumeru"
                        )
                        else -> defaultCharacterInfo
                    }
                    displayCharacterInfo(selectedCharacterInfo)
                } else if (selectedCharacterInfo == defaultCharacterInfo) {
                    // If the checkbox is unchecked and no character is selected, keep it unchecked
                    checkbox.isChecked = false
                } else {
                    // If the checkbox is unchecked and a character is selected, reset to default
                    selectedCharacterInfo = defaultCharacterInfo
                    displayCharacterInfo(selectedCharacterInfo)
                }
            }
        }

        // Set a click listener for each radio button
        for (radioButton in radioButtons) {
            radioButton.setOnClickListener {
                // Uncheck all other radio buttons
                for (otherRadioButton in radioButtons) {
                    if (otherRadioButton != radioButton) {
                        otherRadioButton.isChecked = false
                    }
                }

                // Display background image based on the clicked radio button
                when (radioButton) {
                    binding.themeOne -> binding.backgroundImg.setBackgroundResource(R.drawable.mondstadt)
                    binding.themeTwo -> binding.backgroundImg.setBackgroundResource(R.drawable.inazuma)
                    binding.themeThree -> binding.backgroundImg.setBackgroundResource(R.drawable.sumeru)
                }
            }
        }
    }

    private fun displayCharacterInfo(characterInfo: CharacterInfo) {
        binding.imageView.setImageResource(characterInfo.imageResId)
        binding.releaseDate.text = characterInfo.releaseDate
        binding.titleDesc.text = characterInfo.title
        binding.nameDesc.text = characterInfo.name
        binding.elementDesc.text = characterInfo.element
        binding.weaponDesc.text = characterInfo.weapon
        binding.regionDesc.text = characterInfo.region
    }
}

data class CharacterInfo(
    val imageResId: Int,
    val releaseDate: String,
    val title: String,
    val name: String,
    val element: String,
    val weapon: String,
    val region: String
)
