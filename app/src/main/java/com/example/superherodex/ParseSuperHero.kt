package com.example.superherodex

import com.example.superherodex.model.SuperHero
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ParseSuperHero {
    companion object {
        fun fromJson(response: String): SuperHero {
            return Json { ignoreUnknownKeys = true }.decodeFromString(response)
        }
    }
}