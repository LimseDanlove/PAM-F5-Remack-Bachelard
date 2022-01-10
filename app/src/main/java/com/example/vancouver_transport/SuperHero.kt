package com.example.vancouver_transport

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*

@Serializable
class SuperHero() {
    private var name: String = ""

    constructor(name: String) : this() {
        this.name = name
    }

    override fun toString(): String {
        return "SuperHero(name='$name')"
    }

    companion object {
        fun fromJson(response: String): SuperHero {
            return Json.decodeFromString<SuperHero>(response)
        }
    }
}