package com.example.superherodex.model

import android.os.Parcelable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize

@Parcelize
@Serializable
class SuperHero() : Parcelable {
    var name: String = ""

    //@SerialName("biography")
    var biography: Biography = Biography()
    var appearance: Appearance = Appearance()
    var work: Work = Work()
    var image: Image = Image()
    var connections: Connections = Connections()


    constructor(name: String) : this() {
        this.name = name
    }

    override fun toString(): String {
        return name;
    }







}