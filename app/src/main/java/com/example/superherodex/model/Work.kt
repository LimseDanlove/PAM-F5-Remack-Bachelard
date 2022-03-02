package com.example.superherodex.model

import kotlinx.serialization.Serializable

@Serializable
class Work() {
    var occupation: String = ""

    constructor(occupation: String) : this() {
        this.occupation = occupation
    }
}