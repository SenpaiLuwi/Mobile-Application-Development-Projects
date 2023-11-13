package com.example.act10pricelistappsqlite

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteStatement
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class edit : AppCompatActivity() {
    private lateinit var txtrecno: TextView
    private lateinit var produkto: EditText
    private lateinit var presyo: EditText
    private lateinit var editbutton: Button
    private lateinit var deletebutton: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        txtrecno = findViewById(R.id.record_id)
        produkto = findViewById(R.id.product_id)
        presyo = findViewById(R.id.price_id)
        editbutton = findViewById(R.id.btn_edit_id)
        deletebutton = findViewById(R.id.btn_delete_id)

        val i_edel = intent
        val col1 = i_edel.getStringExtra("id").toString()
        val col2 = i_edel.getStringExtra("prod").toString()
        val col3 = i_edel.getStringExtra("price").toString()

        txtrecno.text = col1
        produkto.setText(col2)
        presyo.setText(col3)

        editbutton.setOnClickListener {
            editrec()
        }

        deletebutton.setOnClickListener {
            deleterec()
        }
    }

    private fun editrec() {
        val newName = produkto.text.toString()
        val newPrice = presyo.text.toString()

            if (newName.isNotEmpty() && newPrice.isNotEmpty()) {
                try {
                    val recnum = txtrecno.text.toString()
                    val db = openOrCreateDatabase("dbaseprod", Context.MODE_PRIVATE, null)

                    val mysql = "update tblproduct set f_prodname = ?, f_prodprice = ? where id = ?"
                    val statement: SQLiteStatement = db.compileStatement(mysql)
                    statement.bindString(1, newName)
                    statement.bindString(2, newPrice)
                    statement.bindString(3, recnum)
                    statement.execute()

                    Toast.makeText(
                        applicationContext, "Record Saved Successfully", Toast.LENGTH_SHORT).show()
                    finish()
                    val intentback = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intentback)
                } catch (exception: Exception) {
                    Toast.makeText(applicationContext, "Record Saved Unsuccessfully", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "Please Input all the Fields", Toast.LENGTH_SHORT).show()
            }
    }


    private fun deleterec() {
        val recnum = txtrecno.text.toString()

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirm Deletion")
        builder.setMessage("Are you sure you want to delete this record?")

        builder.setPositiveButton("Yes") { _, _ ->
            try {
                val db = openOrCreateDatabase("dbaseprod", Context.MODE_PRIVATE, null)

                val mysql = "delete from tblproduct where id = ?"
                val statement: SQLiteStatement = db.compileStatement(mysql)
                statement.bindString(1, recnum)
                statement.execute()

                Toast.makeText(applicationContext, "Record Deleted Successfully", Toast.LENGTH_SHORT).show()
                finish()
                val intentback = Intent(applicationContext, MainActivity::class.java)
                startActivity(intentback)
            } catch (exception: Exception) {
                Toast.makeText(applicationContext, "Record Deleted Unsuccessfully", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

}
