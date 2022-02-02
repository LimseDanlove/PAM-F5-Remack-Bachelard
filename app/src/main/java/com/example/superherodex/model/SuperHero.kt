package com.example.superherodex.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SuperHero() {
    private var name: String = ""

    //@SerialName("biography")
    private var biography: Biography = Biography()
    private var appearance: Appearance = Appearance()
    private var work: Work = Work()
    private var image: Image = Image()
    private var connections: Connections = Connections()


    constructor(name: String) : this() {
        this.name = name
    }

    override fun toString(): String {
        return name;
    }
}