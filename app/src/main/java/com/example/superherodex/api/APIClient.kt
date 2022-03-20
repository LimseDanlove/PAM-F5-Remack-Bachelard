package com.example.superherodex.api

import com.example.superherodex.utils.ParseSuperHero
import com.example.superherodex.model.SuperHero
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

class APIClient(cio : CIO) {
    private val client : HttpClient = HttpClient(cio)

    // Finding a superhero by id
    suspend fun getSH(id : Int) : SuperHero {
        val httpResponse: HttpResponse = client.get("https://www.superheroapi.com/api.php/1106077463536216/$id")
        val body: String = httpResponse.receive()
        // is it the right place to parse ? (separation of concerns)
        return ParseSuperHero.fromJson(body)
    }

    // Finding superhero by name (search bar)
    suspend fun getSH(name: String): ArrayList<SuperHero>{
        val httpResponse: HttpResponse = client.get("https://www.superheroapi.com/api.php/1106077463536216/search/$name")
        val listSH = ArrayList<SuperHero>()

        if(httpResponse.status == HttpStatusCode.OK) {
            // Get body as JsonElement object
            val jsonBody : JsonElement = Json.decodeFromString(JsonElement.serializer(),httpResponse.receive())

            // Parse each result from array results
            if(jsonBody.jsonObject["results"] != null) {
                // Get "results" JsonElement
                val results = Json.decodeFromString(JsonElement.serializer(),jsonBody.jsonObject["results"].toString())

                for (i in 0 until results.jsonArray.size) {
                    // Parsing elements
                    listSH.add(ParseSuperHero.fromJson(results.jsonArray[i].toString()))
                }
            }
        }
        return listSH
    }
}