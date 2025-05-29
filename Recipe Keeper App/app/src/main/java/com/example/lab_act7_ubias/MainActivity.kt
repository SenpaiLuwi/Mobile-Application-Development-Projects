package com.example.lab_act7_ubias

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Toast
import com.example.lab_act7_ubias.databinding.ActivityMainBinding


class MainActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var recipeName = ""
        var recipeIngredients = ""
        var recipeInstructions = ""
        var recipeAreaTxt = ""

        binding.btnAddRecipe.setOnClickListener {
            var recipeName = binding.edtRecipeName.text.toString()

            if (recipeName.isEmpty()) {
                Toast.makeText(this, "Please Enter the Recipe Name", Toast.LENGTH_LONG).show()
            } else {
                val edtRecipeArea = binding.edtRecipeArea.text.toString()
                binding.edtRecipeArea.setText("Recipe Name:$recipeName\n\n$edtRecipeArea")
                binding.edtRecipeName.text.clear()
                binding.edtRecipeName.setText("")
            }




            binding.btnAddIngredients.setOnClickListener {
                if (binding.edtIngredients.text.isEmpty()) {
                    Toast.makeText(this, "Please Enter the Recipe Ingredients", Toast.LENGTH_LONG).show()
                } else {
                    recipeIngredients += binding.edtIngredients.text.toString() + "\n"
                    recipeAreaTxt = "Recipe for: $recipeName\n\nIngredients:\n$recipeIngredients\n\nInstructions:\n$recipeInstructions"
                    binding.edtRecipeArea.setText(recipeAreaTxt)
                    binding.edtIngredients.text.clear()
                }
            }




            binding.btnAddInstructions.setOnClickListener {
                if (binding.edtInstructions.text.isEmpty()) {
                    Toast.makeText(this, "Please Enter the Recipe Instructions", Toast.LENGTH_LONG).show()
                } else {
                    recipeInstructions += binding.edtInstructions.text.toString() + "\n"
                    recipeAreaTxt = "Recipe for: $recipeName\n\nIngredients:\n$recipeIngredients\n\nInstructions:\n$recipeInstructions"
                    binding.edtRecipeArea.setText(recipeAreaTxt)
                    binding.edtInstructions.text.clear()
                }
            }




            binding.btnSaveRecipe.setOnClickListener {
                val recipeName = binding.edtRecipeName.text.toString()
                val ingredients = binding.edtIngredients.text.toString()
                val instructions = binding.edtInstructions.text.toString()
                val recipeArea = binding.edtRecipeArea.text.toString()
                if (recipeArea.isNotEmpty()) {
                    Toast.makeText(this, "Recipe Saved", Toast.LENGTH_SHORT).show()
                    binding.edtRecipeName.setText("")
                    binding.edtIngredients.setText("")
                    binding.edtInstructions.setText("")
                    binding.edtRecipeArea.setText("")
                } else {
                    Toast.makeText(
                        this,
                        "Please complete all Required Information",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }





            binding.btnClear.setOnClickListener {
                binding.edtRecipeName.text.clear()
                binding.edtIngredients.text.clear()
                binding.edtInstructions.text.clear()
                binding.edtRecipeArea.text.clear()
            }
        }

    }
}