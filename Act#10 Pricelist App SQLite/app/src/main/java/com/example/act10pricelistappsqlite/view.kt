package com.example.act10pricelistappsqlite

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class view : AppCompatActivity() {
    private lateinit var viewList: ListView
    private val productlist = ArrayList<String>()
    private lateinit var prodListAdapter: ArrayAdapter<String>

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        val db: SQLiteDatabase = openOrCreateDatabase("dbaseprod", Context.MODE_PRIVATE, null)

        viewList = findViewById(R.id.list_id)

        val tmptable: Cursor = db.rawQuery("select * from tblproduct order by f_prodname", null)
        val id: Int = tmptable.getColumnIndex("id")
        val prodname: Int = tmptable.getColumnIndex("f_prodname")
        val prodprice: Int = tmptable.getColumnIndex("f_prodprice")
        productlist.clear()

        prodListAdapter = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, productlist)
        viewList.adapter = prodListAdapter

        val productdetails = ArrayList<product>()

        if (tmptable.moveToFirst()) {
            do {
                val prod = product()
                prod.refid = tmptable.getString(id)
                prod.productname = tmptable.getString(prodname)
                prod.productprice = tmptable.getString(prodprice)
                productdetails.add(prod)

                productlist.add("(${tmptable.getString(id)})\t\t\t${tmptable.getString(prodname)}\t\t\t\t\t₱${tmptable.getString(prodprice)}")
            }
            while (tmptable.moveToNext())
            prodListAdapter.notifyDataSetChanged()
            viewList.invalidateViews()
        }

        viewList.setOnItemClickListener { adapterView, view, i, l ->
            val prod = productdetails[i]
            val i_edel = Intent(applicationContext, edit::class.java)

            i_edel.putExtra("id", prod.refid)
            i_edel.putExtra("prod", prod.productname)
            i_edel.putExtra("price", prod.productprice)
            finish()
            startActivity(i_edel)
        }

    }
}
