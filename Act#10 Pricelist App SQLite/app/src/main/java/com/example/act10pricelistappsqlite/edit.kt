package com.example.act10pricelistappsqlite

import android.annotation.SuppressLint
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
    private var originalName = ""
    private var originalPrice = ""

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

        //txtrecno.text = "ITEM ID: $col1"
        txtrecno.text = col1
        produkto.setText(col2)
        presyo.setText(col3)
        originalName = produkto.text.toString()
        originalPrice = presyo.text.toString()

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

        if (newName != originalName || newPrice != originalPrice) {
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

                    Toast.makeText(applicationContext, "Record Saved Successfully", Toast.LENGTH_SHORT).show()
                    finish()
                    val intentback = Intent(applicationContext, view::class.java)
                    startActivity(intentback)
                }
                catch (exception: Exception) {
                    Toast.makeText(applicationContext, "Record Saved Unsuccessfully", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(applicationContext, "Please Input all the Fields", Toast.LENGTH_SHORT).show()
            }
        }
        else {
            Toast.makeText(applicationContext, "Please Edit Either the Name or the Price", Toast.LENGTH_SHORT).show()
        }
    }


    private fun deleterec() {
        try {
            val recnum = txtrecno.text.toString().toInt()
            val db = openOrCreateDatabase("dbaseprod", Context.MODE_PRIVATE, null)

            // Delete the record
            val deleteSql = "DELETE FROM tblproduct WHERE id = ?"
            val deleteStatement: SQLiteStatement = db.compileStatement(deleteSql)
            deleteStatement.bindLong(1, recnum.toLong())
            deleteStatement.execute()

            // Update the IDs of the remaining records
            val updateSql = "UPDATE tblproduct SET id = id - 1 WHERE id > ?"
            val updateStatement: SQLiteStatement = db.compileStatement(updateSql)
            updateStatement.bindLong(1, recnum.toLong())
            updateStatement.execute()

            Toast.makeText(applicationContext, "Record Deleted Successfully", Toast.LENGTH_SHORT).show()
            finish()
            val intentback = Intent(applicationContext, view::class.java)
            startActivity(intentback)
        } catch (exception: Exception) {
            Toast.makeText(applicationContext, "Record Deleted Unsuccessfully", Toast.LENGTH_SHORT).show()
        }
    }


}

//IF THE ID WILL STILL REMAIN THE SAME
/*private fun deleterec(){
    try {
        val recnum = txtrecno.text.toString()
        val db = openOrCreateDatabase("dbaseprod", Context.MODE_PRIVATE, null)

        val mysql = "delete from tblproduct where id = ?"
        val statement: SQLiteStatement = db.compileStatement(mysql)
        statement.bindString(1, recnum)
        statement.execute()

        Toast.makeText(applicationContext, "Record Deleted Successfully", Toast.LENGTH_SHORT).show()
        finish()
        val intentback = Intent(applicationContext, view::class.java)
        startActivity(intentback)
    }
    catch (exception: Exception) {
        Toast.makeText(applicationContext, "Record Deleted Unsuccessfully", Toast.LENGTH_SHORT).show()
    }
}
 */

/*

    private fun deleterec() {
        try {
            val recnum = txtrecno.text.toString().toInt()
            val db = openOrCreateDatabase("dbaseprod", Context.MODE_PRIVATE, null)

            // Delete the record
            val deleteSql = "DELETE FROM tblproduct WHERE id = ?"
            val deleteStatement: SQLiteStatement = db.compileStatement(deleteSql)
            deleteStatement.bindLong(1, recnum.toLong())
            deleteStatement.execute()

            // Update the IDs of the remaining records
            val updateSql = "UPDATE tblproduct SET id = id - 1 WHERE id > ?"
            val updateStatement: SQLiteStatement = db.compileStatement(updateSql)
            updateStatement.bindLong(1, recnum.toLong())
            updateStatement.execute()

            Toast.makeText(applicationContext, "Record Deleted Successfully", Toast.LENGTH_SHORT).show()
            finish()
            val intentback = Intent(applicationContext, view::class.java)
            startActivity(intentback)
        } catch (exception: Exception) {
            Toast.makeText(applicationContext, "Record Deleted Unsuccessfully", Toast.LENGTH_SHORT).show()
        }
    }
*/