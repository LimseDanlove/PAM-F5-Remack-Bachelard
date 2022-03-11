package com.example.superherodex.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SuperHeroDAO {
    @Query("SELECT * FROM superhero")
    fun getAll(): List<SuperHeroData>

    @Insert(entity = SuperHeroData::class)
    fun insert(vararg sh: SuperHeroData)

    @Delete
    fun delete(sh: SuperHeroData)
}
