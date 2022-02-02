package com.example.superherodex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.Navigation.findNavController

import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        // Define the listener
        val expandListener = object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                val navController = findNavController(findViewById(R.id.fragmentContainerView))
                navController?.navigate(R.id.action_searchListFragment_to_shListFragment)
                return true // Return true to collapse action view
            }

            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                val navController = findNavController(findViewById(R.id.fragmentContainerView))
                navController?.navigate(R.id.action_shListFragment_to_searchListFragment)
                return true
            }
        }

        // Get the MenuItem for the action item
        val actionMenuItem = menu?.findItem(R.id.action_search)

        // Assign the listener to that action item
        actionMenuItem?.setOnActionExpandListener(expandListener)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewSearch)

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                /*val adapter = recyclerView?.adapter as SuperHeroAdapter
                if (p0 != null) {
                    adapter?.filter(p0)
                }*/
                Log.println(Log.INFO,"TEST","onQueryTextSubmit")
                // recyclerView is null
                recyclerView?.adapter = SuperHeroAdapter(SuperHeroViewModel()).apply {


                    if (p0 != null) {
                        filter(p0)
                    }
                }

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                //Log.println(Log.INFO,"TEST","onQueryTextChange")
                return false
            }
        })




        return super.onCreateOptionsMenu(menu)
    }

    /*override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_search -> {

            true
        }


        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }*/

}