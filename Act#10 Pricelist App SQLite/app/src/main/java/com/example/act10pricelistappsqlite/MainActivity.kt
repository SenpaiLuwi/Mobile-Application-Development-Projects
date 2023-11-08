package com.example.act10pricelistappsqlite

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteStatement
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var prodname: EditText
    private lateinit var prodprice: EditText
    private lateinit var btnadd: Button
    private lateinit var btnview: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prodname = findViewById(R.id.product_id)
        prodprice = findViewById(R.id.price_id)
        btnadd = findViewById(R.id.btn_add_id)
        btnview = findViewById(R.id.btn_view_id)

        btnadd.setOnClickListener {
            addrec()
        }

        btnview.setOnClickListener {
            val intentview = Intent(applicationContext, view::class.java)
            startActivity(intentview)
        }
    }

    private fun addrec() {
        val nameofprods = prodname.text.toString()
        val priceofprods = prodprice.text.toString()

        if (nameofprods.isNotEmpty() && priceofprods.isNotEmpty()) {
            try {
                val nameofprod = prodname.text.toString()
                val priceofprod = prodprice.text.toString()
                val db = openOrCreateDatabase("dbaseprod", Context.MODE_PRIVATE, null)
                db.execSQL("CREATE TABLE IF NOT EXISTS tblproduct(id INTEGER PRIMARY KEY AUTOINCREMENT,f_prodname VARCHAR, f_prodprice VARCHAR)")

                val mysql = "INSERT INTO tblproduct(f_prodname, f_prodprice) VALUES(?, ?)"
                val statement: SQLiteStatement = db.compileStatement(mysql)
                statement.bindString(1, nameofprod)
                statement.bindString(2, priceofprod)
                statement.execute()

                Toast.makeText(applicationContext, "Record Added Successfully", Toast.LENGTH_SHORT).show()
                prodname.text.clear()
                prodprice.text.clear()
                prodname.requestFocus()
            }
            catch (exception: Exception) {
                Toast.makeText(applicationContext, "Record Added Unsuccessfully", Toast.LENGTH_SHORT).show()
            }
        }
        else {
            Toast.makeText(applicationContext, "Please Input all the Fields", Toast.LENGTH_SHORT).show()
        }
    }

}
