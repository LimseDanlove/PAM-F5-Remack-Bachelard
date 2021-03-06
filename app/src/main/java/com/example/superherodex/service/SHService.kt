package com.example.superherodex.service

import android.content.Context
import com.example.superherodex.api.APIClient
import com.example.superherodex.repository.AppDatabase
import com.example.superherodex.repository.SuperHeroData
import com.example.superherodex.model.SuperHero
import io.ktor.client.engine.cio.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class SHService(context: Context) {
    private var context: Context = context

    //-------------------------------------------------------
    // SERVICES FOR SEARCH FEATURE
    //-------------------------------------------------------

    // Retrieving list of superhero by name from API
    fun getName(query : String): ArrayList<SuperHero>{
        var listSH = ArrayList<SuperHero>()

        runBlocking(Dispatchers.Default) {
            val client = APIClient(CIO)
            listSH = client.getSH(query)
        }
        return listSH
    }


    //-------------------------------------------------------
    // SERVICES FOR FAVORITE FEATURE
    //-------------------------------------------------------

    // Adding superhero to database
    fun add(sh: SuperHero){
        val db = AppDatabase.getInstance(context)
        val shDao = db.shDao()

        runBlocking(Dispatchers.Default) {
            shDao.insert(
                SuperHeroData(
                    sh.id,
                    sh.name,
                    sh.biography.full_name,
                    sh.biography.first_appearance,
                    sh.biography.publisher,
                    sh.biography.alignment,
                    sh.appearance.height[0]!!,
                    sh.appearance.height[1]!!,
                    sh.appearance.weight[0]!!,
                    sh.appearance.weight[1]!!,
                    sh.appearance.eye_color,
                    sh.appearance.hair_color,
                    sh.appearance.gender,
                    sh.appearance.race,
                    sh.work.occupation,
                    sh.image.url,
                    sh.connections.group_affiliation,
                    sh.connections.relatives
                )
            )
        }
    }

    // Finding out if a superhero is already present in database (by id)
    fun get(sh: SuperHero) : SuperHero? {
        val db = AppDatabase.getInstance(context)
        val shDao = db.shDao()
        var foundSHData : SuperHeroData? = null

        runBlocking(Dispatchers.Default) {
            foundSHData = shDao.get(sh.id)
        }

        if (foundSHData != null) {
            return SuperHero(foundSHData!!)
        }

        return null
    }


    // Getting all superheros from database
    fun getAll() : ArrayList<SuperHero>{
        val db = AppDatabase.getInstance(context)
        val shDao = db.shDao()
        var listSHData : List<SuperHeroData>? = null
        val listSH = ArrayList<SuperHero>()

        runBlocking(Dispatchers.Default) {
            listSHData = shDao.getAll()
        }

        if (listSHData != null) {
            for (elt in listSHData!!) {
                listSH.add(SuperHero(elt))
            }
        }

        return listSH
    }

    // Delete superhero from database (by id)
    fun delete(sh: SuperHero) : Int {
        val db = AppDatabase.getInstance(context)
        val shDao = db.shDao()
        var res = 0

        runBlocking(Dispatchers.Default) {
            res = shDao.delete(sh.id)
        }

        return res
    }
}