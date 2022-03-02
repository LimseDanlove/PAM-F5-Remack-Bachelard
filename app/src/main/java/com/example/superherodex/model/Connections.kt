package com.example.superherodex.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Connections() {
    @SerialName("group-affiliation")
    var group_affiliation: String = ""
    var relatives: String = ""

    constructor(group_affiliation: String, relatives: String) : this() {
        this.group_affiliation = group_affiliation
        this.relatives = relatives
    }

}