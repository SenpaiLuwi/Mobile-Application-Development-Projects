package com.example.act10pricelistappsqlite
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteStatement
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var prodname: EditText
    private lateinit var prodprice: EditText
    private lateinit var btnadd: Button
    private lateinit var btnview: Button
    private lateinit var radioFoodBtn: RadioButton
    private lateinit var radioDrinkBtn: RadioButton
    private lateinit var radioOtherBtn: RadioButton
    private lateinit var btnFood: Button
    private lateinit var btnDrink: Button
    private lateinit var btnOther: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prodname = findViewById(R.id.product_id)
        prodprice = findViewById(R.id.price_id)
        btnadd = findViewById(R.id.btn_add_id)
        btnview = findViewById(R.id.btn_view_id)
        radioFoodBtn = findViewById(R.id.radioFoodBtn)
        radioDrinkBtn = findViewById(R.id.radioDrinkBtn)
        radioOtherBtn = findViewById(R.id.radioOtherBtn)
        btnFood = findViewById(R.id.btn_food_id)
        btnDrink = findViewById(R.id.btn_drink_id)
        btnOther = findViewById(R.id.btn_other_id)

        radioFoodBtn.setOnClickListener {
            radioDrinkBtn.isChecked = false
            radioOtherBtn.isChecked = false
        }

        radioDrinkBtn.setOnClickListener {
            radioFoodBtn.isChecked = false
            radioOtherBtn.isChecked = false
        }

        radioOtherBtn.setOnClickListener {
            radioFoodBtn.isChecked = false
            radioDrinkBtn.isChecked = false
        }

        btnadd.setOnClickListener {
            if (!radioFoodBtn.isChecked && !radioDrinkBtn.isChecked && !radioOtherBtn.isChecked) {
                Toast.makeText(applicationContext, "Please Validate the Category on the Buttons and Text", Toast.LENGTH_SHORT).show()
            } else {
                val category = when {
                    radioFoodBtn.isChecked -> "Food"
                    radioDrinkBtn.isChecked -> "Drink"
                    radioOtherBtn.isChecked -> "Other"
                    else -> ""
                }

                if (category.isNotEmpty()) {
                    addRec(category)
                    radioFoodBtn.isChecked = false
                    radioDrinkBtn.isChecked = false
                    radioOtherBtn.isChecked = false
                }
            }
        }

        btnFood.setOnClickListener {
            viewCategory("Food")
            Toast.makeText(applicationContext, "FOOD", Toast.LENGTH_SHORT).show()
        }

        btnDrink.setOnClickListener {
            viewCategory("Drink")
            Toast.makeText(applicationContext, "DRINK", Toast.LENGTH_SHORT).show()
        }

        btnOther.setOnClickListener {
            viewCategory("Other")
            Toast.makeText(applicationContext, "OTHER", Toast.LENGTH_SHORT).show()
        }

        btnview.setOnClickListener {
            val intentview = Intent(applicationContext, view::class.java)
            Toast.makeText(applicationContext, "ALL PRODUCTS", Toast.LENGTH_SHORT).show()
            startActivity(intentview)
        }
    }

    private fun addRec(category: String) {
        val nameofprods = prodname.text.toString()
        val priceofprods = prodprice.text.toString()

        if (nameofprods.isNotEmpty() && priceofprods.isNotEmpty()) {
            try {
                val db = openOrCreateDatabase("dbaseprod", Context.MODE_PRIVATE, null)
                db.execSQL("CREATE TABLE IF NOT EXISTS tblproduct(id INTEGER PRIMARY KEY AUTOINCREMENT,f_prodname VARCHAR, f_prodprice VARCHAR, f_category VARCHAR)")

                val mysql = "INSERT INTO tblproduct(f_prodname, f_prodprice, f_category) VALUES(?, ?, ?)"
                val statement: SQLiteStatement = db.compileStatement(mysql)
                statement.bindString(1, nameofprods)
                statement.bindString(2, priceofprods)
                statement.bindString(3, category)
                statement.execute()

                Toast.makeText(applicationContext, "Record Added Successfully", Toast.LENGTH_SHORT).show()
                prodname.text.clear()
                prodprice.text.clear()
                prodname.requestFocus()
            } catch (exception: Exception) {
                Toast.makeText(applicationContext, "Record Added Unsuccessfully", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(applicationContext, "Please Input all the Fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun viewCategory(category: String) {
        val intent = Intent(applicationContext, view::class.java)
        intent.putExtra("category", category)
        startActivity(intent)
    }
}

