package com.example.act8pricelistapp

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.act8pricelistapp.databinding.ActivityMainBinding
import java.io.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productNameEditText: EditText
    private lateinit var productPriceEditText: EditText
    private lateinit var listView: ListView
    private val productList = ArrayList<String>()
    private val FILE_NAME = "listinfo.dat"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productNameEditText = findViewById(R.id.nameEdt)
        productPriceEditText = findViewById(R.id.priceEdt)
        listView = findViewById(R.id.list)

        binding.saveBtn.setOnClickListener {
            saveProduct()
        }

        loadProductList()
        updateListView()

        binding.searchEdt.setOnKeyListener { _, _, _ ->
            searchProduct(binding.searchEdt.text.toString())
            false
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            showDeleteDialog(position)
        }
    }

    private fun saveProduct() {
        val productName = productNameEditText.text.toString().trim()
        val productPrice = productPriceEditText.text.toString().trim()

        if (productName.isEmpty() || productPrice.isEmpty()) {
            Toast.makeText(this, "Both fields should be filled", Toast.LENGTH_SHORT).show()
            return
        }

        val product = "Name: $productName \nPrice: ₱$productPrice"

        var insertPosition = 0
        while (insertPosition < productList.size && productList[insertPosition] < product) {
            insertPosition++
        }

        productList.add(insertPosition, product)

        productNameEditText.text.clear()
        productPriceEditText.text.clear()

        updateListView()
        saveProductList()
    }


    private fun updateListView() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, productList)
        listView.adapter = adapter
    }

    private fun searchProduct(query: String) {
        val filteredList = productList.filter { it.contains(query, true) }
        updateListViewWithFilter(filteredList)
    }

    private fun updateListViewWithFilter(filteredList: List<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredList)
        listView.adapter = adapter
    }

    private fun showDeleteDialog(position: Int) {
        var posToDel = 0
        val productListSize = productList.size

        for (i in 0 until productListSize) {
            if (listView.getItemAtPosition(position).toString() == productList[i]) {
                break
            }
            posToDel++
        }

        val alert = AlertDialog.Builder(this)
        alert.setTitle("Attention!")
        alert.setMessage("Do you want to delete this item?")
        alert.setPositiveButton("Delete") { _, _ ->
            productList.removeAt(posToDel)
            updateListView()
            saveProductList()
        }

            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }


        val alertDialog = alert.create()
        alertDialog.show()
    }


    private fun saveProductList() {
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
            val objectOutputStream = ObjectOutputStream(fileOutputStream)
            objectOutputStream.writeObject(productList)
            objectOutputStream.close()
            fileOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun loadProductList() {
        val fileInputStream: FileInputStream
        try {
            fileInputStream = openFileInput(FILE_NAME)
            val objectInputStream = ObjectInputStream(fileInputStream)
            val list = objectInputStream.readObject() as ArrayList<*>
            productList.addAll(list.filterIsInstance<String>())
            objectInputStream.close()
            fileInputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }
}