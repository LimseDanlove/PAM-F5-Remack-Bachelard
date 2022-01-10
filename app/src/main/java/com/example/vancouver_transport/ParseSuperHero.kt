package com.example.vancouver_transport

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ParseSuperHero {
    companion object {
        fun fromJson(response: String): SuperHero {
            return Json { ignoreUnknownKeys = true }.decodeFromString(response)
        }
    }
}