package com.example.superherodex.model

import kotlinx.serialization.Serializable

@Serializable
class Image() {
    var url: String = ""

    constructor(url: String) : this() {
        this.url = url
    }

}