package com.example.act11firebaserealtimedatabase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddNewRecord : AppCompatActivity() {

    private lateinit var v_Name: EditText
    private lateinit var v_Number: EditText
    private lateinit var v_Power: EditText
    private lateinit var v_ImgUrl: EditText
    private lateinit var v_Save: Button
    private lateinit var v_Cancel: Button

    // Start of OnCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_record)

        v_Name = findViewById(R.id.txt_id_name)
        v_Number = findViewById(R.id.txt_id_number)
        v_Power = findViewById(R.id.txt_id_power)
        v_ImgUrl = findViewById(R.id.txt_id_imgurl)

        v_Save = findViewById(R.id.btn_id_save)
        v_Cancel = findViewById(R.id.btn_id_cancel)

        // Save Button
        v_Save.setOnClickListener {
            val name_is = v_Name.text.toString()
            val number_is = v_Number.text.toString()
            val power_is = v_Power.text.toString()
            val image_is = v_ImgUrl.text.toString()

            when {
                name_is.isEmpty() -> f_ValidateMsg("NAME")
                number_is.isEmpty() -> f_ValidateMsg("NUMBER")
                power_is.isEmpty() -> f_ValidateMsg("POWER")
                image_is.isEmpty() -> f_ValidateMsg("IMAGE")
                else -> f_InsertRecord(name_is, number_is, power_is, image_is)
            }
        }

        // Cancel Button
        v_Cancel.setOnClickListener {
            Toast.makeText(this@AddNewRecord, "CANCEL", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }

    // Insertion of Record
    private fun f_InsertRecord(name_is: String, number_is: String, power_is: String, image_is: String) {
        // HASHMAP
        val dataHashMap = HashMap<String, Any>()
        dataHashMap["B_name"] = name_is
        dataHashMap["C_number"] = number_is
        dataHashMap["D_power"] = power_is
        dataHashMap["E_imageurl"] = image_is

        // DB CONNECTION
        val database = FirebaseDatabase.getInstance()
        val tblReference: DatabaseReference = database.getReference("values")

        // AUTO GENERATE KEY
        val idKey = tblReference.push().key
        dataHashMap["A_idno"] = idKey!!

        tblReference.child(idKey).setValue(dataHashMap).addOnCompleteListener {
            Toast.makeText(this@AddNewRecord, "CHARACTER ADDED SUCCESSFULLY!", Toast.LENGTH_SHORT).show()
            v_Name.text.clear()
            v_Number.text.clear()
            v_Power.text.clear()
            v_ImgUrl.text.clear()
        }
    }

    // Validation of Message
    private fun f_ValidateMsg(info: String) {
        Toast.makeText(this@AddNewRecord, "PLEASE ENTER CHARACTERS $info", Toast.LENGTH_SHORT).show()
    }
}
