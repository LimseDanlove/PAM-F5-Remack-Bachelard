package com.example.superherodex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView

import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val searchView = findViewById<SearchView>(R.id.searchView)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewSearch)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                /*val adapter = recyclerView?.adapter as SuperHeroAdapter
                if (p0 != null) {
                    adapter?.filter(p0)
                }*/
                // recyclerView is null
                recyclerView?.adapter = SuperHeroAdapter(SuperHeroViewModel()).apply {
                    if (p0 != null) {
                        filter(p0)
                    }
                }

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })*/
    }
}