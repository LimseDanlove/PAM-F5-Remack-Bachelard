package com.example.superherodex

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.superherodex.model.SuperHero

@Dao
interface SuperHeroDAO {
    @Query("SELECT * FROM superhero")
    fun getAll(): List<SuperHeroData>

    @Insert
    fun insertAll(vararg users: SuperHeroData)

    @Delete
    fun delete(user: SuperHeroData)
}