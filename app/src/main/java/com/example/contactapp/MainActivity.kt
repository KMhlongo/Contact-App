package com.example.contactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.model.User
import com.example.contactapp.viewmodel.UserViewModel
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MyRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        adapter = MyRecyclerAdapter(this, ArrayList<User>())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val userViewModel: UserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        userViewModel.getUsers().observe(this) {
            adapter.mList = it
            adapter.mFilterList = it
            adapter.notifyDataSetChanged()
        }

        val userSearch = findViewById<SearchView>(R.id.search_view)
        userSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

    }
}