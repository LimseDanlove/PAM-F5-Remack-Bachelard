package com.example.superherodex.model

import kotlinx.serialization.Serializable

@Serializable
class Image() {
    private var url: String = ""

    constructor(url: String) : this() {
        this.url = url
    }

}