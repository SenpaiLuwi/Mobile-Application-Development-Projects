package com.example.act11firebaserealtimedatabase

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var v_FloatingActionButton: FloatingActionButton
    private lateinit var v_recyclerView: RecyclerView
    private lateinit var v_mainAdapter: MainAdapter

    // Starting of On Create
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        v_FloatingActionButton = findViewById(R.id.floatAbtn_id)
        v_recyclerView = findViewById(R.id.rv_id)

        v_recyclerView.layoutManager = LinearLayoutManager(this)

        val options: FirebaseRecyclerOptions<MainModel> = FirebaseRecyclerOptions.Builder<MainModel>()
            .setQuery(FirebaseDatabase.getInstance().reference.child("values"), MainModel::class.java)
            .build()

        v_mainAdapter = MainAdapter(options)
        v_recyclerView.adapter = v_mainAdapter

        // Add A Character
        v_FloatingActionButton.setOnClickListener {
            Toast.makeText(this@MainActivity, "ADD A CHARACTER!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, AddNewRecord::class.java))
        }
    }

    // On Start
    override fun onStart() {
        super.onStart()
        v_mainAdapter.startListening()
    }

    // On Stop
    override fun onStop() {
        super.onStop()
        v_mainAdapter.stopListening()
    }

    // onCreateOptionsMenu for the Search Item
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        val item: MenuItem = menu!!.findItem(R.id.search)
        val searchView = item.actionView as SearchView

        // onQueryTextSubmit
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                txtSearch(query)
                return false
            }

            // onQueryTextChange
            override fun onQueryTextChange(query: String?): Boolean {
                txtSearch(query)
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    // TxtSearch will be the Validation for the Search View
    private fun txtSearch(str:String?){
        val options: FirebaseRecyclerOptions<MainModel> = FirebaseRecyclerOptions.Builder<MainModel>()
            .setQuery(FirebaseDatabase.getInstance().reference.child("values").orderByChild("B_name").startAt(str).endAt("$str~"), MainModel::class.java)
            .build()

        v_mainAdapter = MainAdapter(options)
        v_mainAdapter.startListening()
        v_recyclerView.adapter = v_mainAdapter
    }
}
