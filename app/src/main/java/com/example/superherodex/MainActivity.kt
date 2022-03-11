package com.example.superherodex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

import com.example.superherodex.SearchListFragment as SearchListFragment
import android.R.color

import androidx.core.graphics.drawable.DrawableCompat

import android.graphics.drawable.Drawable

import android.R.string.no
import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    private var menuId : Int = R.menu.menu
    private var favorite : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    @SuppressLint("ResourceAsColor", "UseCompatLoadingForDrawables")
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
                        val action = navController.currentDestination?.getAction(R.id.action_shListFragment_to_searchListFragment)
                        if(action != null){
                            navController.navigate(R.id.action_shListFragment_to_searchListFragment)
                        }
                    }
                }
                return true
            }
        }

        // Get the MenuItem for the action item
        val actionMenuItem = menu.findItem(R.id.action_search)

        if(actionMenuItem != null) {
            val searchView = actionMenuItem.actionView as SearchView
            // Assign the listener to that action item
            actionMenuItem.setOnActionExpandListener(expandListener)

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    val navFragment =
                        supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
                    val searchFragment =
                        navFragment.childFragmentManager.fragments[0] as SearchListFragment

                    searchFragment.querySubmit(query);

                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return false
                }
            })
        }

        val favoriteMenuItem = menu.findItem(R.id.action_favorite)

        if(favoriteMenuItem != null) {
            val onClickListener = object: MenuItem.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem?): Boolean {
                    when(item?.itemId) {
                        R.id.action_favorite -> {
                            val navFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
                            val detailFragment = navFragment.childFragmentManager.fragments[0] as DetailsFragment

                            if (favorite) {
                                changeItemIcon(favoriteMenuItem, R.drawable.ic_favorite)
                                favorite = false
                                detailFragment.deleteFavorite()
                            } else {
                                changeItemIcon(favoriteMenuItem, R.drawable.ic_favorite_full)
                                favorite = true
                                detailFragment.addFavorite()
                            }
                        }
                    }

                    return false
                }
            }
            favoriteMenuItem.setOnMenuItemClickListener(onClickListener)

            if (favorite){
                changeItemIcon(favoriteMenuItem,R.drawable.ic_favorite_full)
            }
        }


        return super.onCreateOptionsMenu(menu)
    }

    private fun changeItemIcon(item : MenuItem, iconRes : Int){
        val normalDrawable: Drawable? = AppCompatResources.getDrawable(applicationContext, iconRes)
        val wrapDrawable = normalDrawable?.let { DrawableCompat.wrap(it) }
        if (wrapDrawable != null) {
            DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(applicationContext,R.color.white))
        }
        item.icon = wrapDrawable
    }


    fun changeMenu(menuId: Int,favorite: Boolean){
        this.favorite = favorite

        //Check if it's already set to not reset it (and reset the layout of the action bar)
        if(menuId != this.menuId) {
            this.menuId = menuId
            invalidateOptionsMenu()
        }
    }
}