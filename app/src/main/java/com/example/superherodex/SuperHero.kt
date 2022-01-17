package com.example.superherodex

import kotlinx.serialization.Serializable

@Serializable
class SuperHero() {
    private var name: String = ""

    constructor(name: String) : this() {
        this.name = name
    }

    override fun toString(): String {
        return "SuperHero(name='$name')"
    }
}