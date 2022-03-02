package com.example.superherodex.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Biography() {
    @SerialName("full-name")
    var full_name: String = ""

    @SerialName("first-appearance")
    var first_appearance: String = ""

    var publisher: String = ""
    var alignment: String = ""

    constructor(full_name: String, first_appearance: String, publisher: String, alignment: String) : this() {
        this.full_name = full_name
        this.first_appearance = first_appearance
        this.publisher = publisher
        this.alignment = alignment
    }

    override fun toString(): String {
        return "Biography(full_name='$full_name', first_appearance='$first_appearance', publisher='$publisher', alignment='$alignment')"
    }



}