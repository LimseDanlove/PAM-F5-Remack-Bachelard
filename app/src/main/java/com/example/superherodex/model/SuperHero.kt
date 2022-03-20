package com.example.superherodex.model

import android.os.Parcelable
import com.example.superherodex.repository.SuperHeroData
import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize

@Parcelize
@Serializable
class SuperHero() : Parcelable {
    var id: Int = 0
    var name: String = ""

    var biography: Biography = Biography()
    var appearance: Appearance = Appearance()
    var work: Work = Work()
    var image: Image = Image()
    var connections: Connections = Connections()

    // Specific constructor needed for parsing
    constructor(shd: SuperHeroData) : this() {
        this.id = shd.id
        this.name = shd.name
        this.biography = Biography(shd.fullname, shd.first_appearance, shd.publisher, shd.alignment)
        this.appearance = Appearance(shd.eye_color, shd.hair_color, shd.gender, shd.race, arrayOf(shd.height_en, shd.height_fr), arrayOf(shd.weight_en, shd.weight_fr))
        this.work = Work(shd.occupation)
        this.image = Image(shd.url)
        this.connections = Connections(shd.group_affiliation, shd.relatives)
    }

    override fun toString(): String {
        return name;
    }







}