package com.example.act2

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Color.parseColor
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.act2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var greeting = "Hello"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Send Button Click Listener
        binding.SendBtn.setOnClickListener {
            val firstName = binding.FirstNameEdit.text.toString()
            val lastName = binding.LastNameEdit.text.toString()
            val miName = binding.MINameEdit.text.toString()

            if (firstName.isBlank() || lastName.isBlank() || miName.isBlank()) {
                Toast.makeText(this, "Please Fill Up the Field", Toast.LENGTH_SHORT).show()
            } else {
                binding.TextArea.text = "$greeting\n$firstName $miName $lastName"
                binding.FirstNameEdit.text.clear()
                binding.LastNameEdit.text.clear()
                binding.MINameEdit.text.clear()
            }
        }

        // Color Button Click Listeners
        binding.RedBtn.setOnClickListener {
            changeColor(Color.RED)
            binding.RedBtn.setBackgroundColor(Color.RED)
        }
        binding.GreenBtn.setOnClickListener {
            changeColor(Color.GREEN)
            binding.GreenBtn.setBackgroundColor(Color.GREEN)
        }
        binding.OrangeBtn.setOnClickListener {
            changeColor(parseColor("#FFA500"))
            binding.OrangeBtn.setBackgroundColor(parseColor("#FFA500"))
        }

        // Size Button Click Listeners
        binding.SizeOne.setOnClickListener { changeTextSize(34f) }
        binding.SizeTwo.setOnClickListener { changeTextSize(24f) }
        binding.SizeThree.setOnClickListener { changeTextSize(20f) }

        // Greeting Button Click Listeners
        binding.WelcomeBtn.setOnClickListener { changeGreetingAndSetText("Welcome") }
        binding.CongratsBtn.setOnClickListener { changeGreetingAndSetText("Congratulations") }
        binding.NiceBtn.setOnClickListener { changeGreetingAndSetText("Have a Nice Day") }
    }

    private fun changeColor(color: Int) {
        binding.TextArea.setTextColor(color)
    }

    private fun changeTextSize(size: Float) {
        binding.TextArea.textSize = size
    }


    @SuppressLint("SetTextI18n")
    private fun changeGreetingAndSetText(newGreeting: String) {
        greeting = newGreeting
        // Check if TextArea already has some text
        val currentText = binding.TextArea.text.toString()
        if (currentText.isNotBlank()) {
            val parts = currentText.split("\n")
            if (parts.size > 1) {
                // Update the TextArea
                binding.TextArea.text = "$greeting\n${parts[1]}"
            }
        }
    }
}
