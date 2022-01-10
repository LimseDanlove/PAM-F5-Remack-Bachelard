package com.example.vancouver_transport

class SuperHero() {
    private var name: String = ""

    constructor(name: String) : this() {
        this.name = name
    }

    override fun toString(): String {
        return "SuperHero(name='$name')"
    }
}