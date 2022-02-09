package com.example.superherodex

import android.app.Notification
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

import androidx.recyclerview.widget.RecyclerView
import io.ktor.util.reflect.*
import java.lang.Exception
import com.example.superherodex.SearchListFragment as SearchListFragment


class MainActivity : AppCompatActivity() {

    private var menuId : Int = R.menu.menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(menuId, menu)

        // Define the listener for search view
        val expandListener = object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                //val navController = findNavController(findViewById(R.id.fragmentContainerView))
                //navController?.navigate(R.id.action_searchListFragment_to_shListFragment)
                return true // Return true to collapse action view
            }

            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                when(item.itemId) {
                    R.id.action_search -> {
                        // Maybe move this to onOptionsItemSelected
                        val navController = findNavController(findViewById(R.id.fragmentContainerView))
                        var action = navController.currentDestination?.getAction(R.id.action_shListFragment_to_searchListFragment)
                        if(action != null){
                            navController?.navigate(R.id.action_shListFragment_to_searchListFragment)
                        }

                        /*try {
                            navController?.navigate(R.id.action_shListFragment_to_searchListFragment)
                        } catch (e : Exception){
                            // C'est kk : on peut accéder au search depuis deux fragment différent,
                            // l'action ne correspond donc pas tout le temps
                            // il vaudrait mieux faire un if en fonciton de la situation ?
                            // Après ça reste assez logique qu'on ne puisse rien faire si on n'est pas au bon endroit ?
                        }*/
                    }
                }
                return true
            }
        }

        // Get the MenuItem for the action item
        val actionMenuItem = menu?.findItem(R.id.action_search)

        if(actionMenuItem != null) {
            val searchView = actionMenuItem?.actionView as SearchView
            // Assign the listener to that action item
            actionMenuItem?.setOnActionExpandListener(expandListener)

            searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.println(Log.INFO, "TEST", "onQueryTextSubmit")
                    val navFragment =
                        supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
                    val searchFragment =
                        navFragment.childFragmentManager.fragments[0] as SearchListFragment

                    searchFragment.querySubmit(query);

                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    //Log.println(Log.INFO,"TEST","onQueryTextChange")
                    return false
                }
            })
        }



        return super.onCreateOptionsMenu(menu)
    }

    fun changeMenu(menuId: Int){
        //Check if it's already set to not reset it (and reset the layout of the action bar)
        if(menuId != this.menuId) {
            this.menuId = menuId
            invalidateOptionsMenu()
        }
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