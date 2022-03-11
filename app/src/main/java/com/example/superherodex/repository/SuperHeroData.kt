package com.example.superherodex.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="superhero")
data class SuperHeroData (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "biography_fullname") val fullname: String,
    @ColumnInfo(name = "biography_first_appearance") val first_appearance: String,
    @ColumnInfo(name = "biography_publisher") val publisher: String,
    @ColumnInfo(name = "biography_alignment") val alignment: String,

    @ColumnInfo(name = "appearance_height_en") val height_en: String,
    @ColumnInfo(name = "appearance_height_fr") val height_fr: String,
    @ColumnInfo(name = "appearance_weight_en") val weight_en: String,
    @ColumnInfo(name = "appearance_weight_fr") val weight_fr: String,
    @ColumnInfo(name = "appearance_eye_color") val eye_color: String,
    @ColumnInfo(name = "appearance_hair_color") val hair_color: String,
    @ColumnInfo(name = "appearance_gender") val gender: String,
    @ColumnInfo(name = "appearance_race") val race: String,

    @ColumnInfo(name = "work_occupation") val occupation: String,

    @ColumnInfo(name = "image_url") val url: String,

    @ColumnInfo(name = "connections_group_affiliation") val group_affiliation: String,
    @ColumnInfo(name = "connections_relatives") val relatives: String
)