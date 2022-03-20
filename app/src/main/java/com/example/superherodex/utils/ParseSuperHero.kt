package com.example.superherodex.utils

import com.example.superherodex.model.SuperHero
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ParseSuperHero {
    companion object {
        fun fromJson(response: String): SuperHero {
            // Parsing Json and ignoring unknown keys if they exists
            return Json { ignoreUnknownKeys = true }.decodeFromString(response)
        }
    }
}