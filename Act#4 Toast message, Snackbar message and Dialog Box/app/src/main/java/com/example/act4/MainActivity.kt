package com.example.act4

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.act4.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isLightOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pinkBtn.setOnClickListener {
            Toast.makeText(applicationContext, "The Pink Button is Pressed", Toast.LENGTH_SHORT).show()
        }

        binding.yellowBtn.setOnClickListener {
            Toast.makeText(applicationContext, "The Yellow Button is Pressed", Toast.LENGTH_SHORT).show()
        }

        binding.cyanBtn.setOnClickListener {
            Toast.makeText(applicationContext, "The Cyan Button is Pressed", Toast.LENGTH_SHORT).show()
        }


        binding.buttonOne.setOnClickListener { onRadioButtonClicked(it) }
        binding.buttonTwo.setOnClickListener { onRadioButtonClicked(it) }
        binding.buttonThree.setOnClickListener { onRadioButtonClicked(it) }

        binding.lightsBtn.setOnClickListener { showLightDialog() }
    }

    private fun onRadioButtonClicked(view: View) {
        val message: String = when (view.id) {
            R.id.buttonOne -> {
                if (binding.buttonThree.isChecked) {
                    binding.buttonThree.isChecked = false
                }
                "That is the WRONG Answer, This Guy is NOT HANDSOME"
            }

            R.id.buttonTwo -> {
                binding.buttonOne.isChecked = false
                binding.buttonOne.isEnabled = false
                binding.buttonThree.isChecked = false
                binding.buttonThree.isEnabled = false
                "You Are Correct! This Guy IS HANDSOME"
            }

            R.id.buttonThree -> {
                if (binding.buttonOne.isChecked) {
                    binding.buttonOne.isChecked = false
                }
                "That is the WRONG Answer, This Guy is NOT HANDSOME"
            }

            else -> {
                ""
            }
        }

        showSnackbar(message)
    }

    private fun showSnackbar(message: String) {
        val snackBuilder = Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
        snackBuilder.setAction("Close") {
            snackBuilder.dismiss()
        }
        snackBuilder.show()
    }

    private fun showLightDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Attention!")
        builder.setMessage("What do you want me to do for the Torch?")
        builder.setPositiveButton("Turn on the Light") { _, _ ->
            if (!isLightOn) {
                binding.torchPng.setImageResource(R.drawable.bulbon)
                binding.background.setBackgroundColor(android.graphics.Color.parseColor("#000000"))
                changeRadioButtonsColor("#FFFFFF")
                changeTextColors("#FFFFFF") // Change text colors to white
                isLightOn = true
            } else {
                Toast.makeText(this, "The torch is already ON", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Turn off the Light") { _, _ ->
            if (isLightOn) {
                binding.torchPng.setImageResource(R.drawable.bulboff)
                binding.background.setBackgroundColor(android.graphics.Color.parseColor("#FFFFFF"))
                changeRadioButtonsColor("#000000")
                changeTextColors("#000000") // Change text colors to black
                isLightOn = false
            } else {
                Toast.makeText(this, "The torch is already OFF", Toast.LENGTH_SHORT).show()
            }
        }
        builder.show()
    }

    private fun changeTextColors(textColor: String) {
        binding.titleTxt.setTextColor(android.graphics.Color.parseColor(textColor))
        binding.toastTxt.setTextColor(android.graphics.Color.parseColor(textColor))
        binding.snackTxt.setTextColor(android.graphics.Color.parseColor(textColor))
        binding.questionTxt.setTextColor(android.graphics.Color.parseColor(textColor))
        binding.dialogTxt.setTextColor(android.graphics.Color.parseColor(textColor))
    }

    private fun changeRadioButtonsColor(textColor: String) {
        binding.buttonOne.setTextColor(android.graphics.Color.parseColor(textColor))
        binding.buttonTwo.setTextColor(android.graphics.Color.parseColor(textColor))
        binding.buttonThree.setTextColor(android.graphics.Color.parseColor(textColor))
        binding.buttonOne.buttonTintList = android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor(textColor))
        binding.buttonTwo.buttonTintList = android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor(textColor))
        binding.buttonThree.buttonTintList = android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor(textColor))
    }
}
