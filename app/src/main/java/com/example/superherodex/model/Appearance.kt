package com.example.superherodex.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Arrays.copyOf
import java.util.Collections.copy

@Serializable
class Appearance() {
    @SerialName("eye-color")
    var eye_color: String = ""

    @SerialName("hair-color")
    var hair_color: String = ""

    var gender: String = ""
    var race: String = ""
    var height: Array<String?> = Array<String?>(2) {null}
    var weight: Array<String?> = Array<String?>(2) {null}

    constructor(eye_color: String, hair_color: String, gender: String, race: String, height: Array<String>, weight: Array<String>) : this() {
        this.eye_color = eye_color
        this.hair_color = hair_color
        this.gender = gender
        this.race = race
        this.height = height.copyOf(2)
        this.weight = weight.copyOf(2)
    }

}