package com.example.superherodex

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

    suspend fun getSH(id : Int) : SuperHero {
        val httpResponse: HttpResponse = client.get("https://www.superheroapi.com/api.php/1106077463536216/$id")
        val body: String = httpResponse.receive()
        // is it the right place to parse ? (separation of concerns)
        return ParseSuperHero.fromJson(body)
    }

    suspend fun getFirstSH(firsts : Int): ArrayList<SuperHero>{
        val list = ArrayList<SuperHero>()
        var max = firsts

        if(max > 731){
            max = 731
        }

        for (i in 1..firsts) {
            list.add(getSH(i))
        }

        return list
    }

    suspend fun getSH(name: String): ArrayList<SuperHero>{
        // maybe put API token as attribute ?
        val httpResponse: HttpResponse = client.get("https://www.superheroapi.com/api.php/1106077463536216/search/$name")
        val listSH = ArrayList<SuperHero>()

        if(httpResponse.status == HttpStatusCode.OK) {
            // get body as JsonElement object
            val jsonBody : JsonElement = Json.decodeFromString(JsonElement.serializer(),httpResponse.receive())

            // get "results" JsonElement
            val results = Json.decodeFromString(JsonElement.serializer(),jsonBody.jsonObject["results"].toString())

            // parse each result from array results
            for (i in 0 until results.jsonArray.size) {
                // right place to parse ?
                listSH.add(ParseSuperHero.fromJson(results.jsonArray[i].toString()))
            }
        }
        return listSH
    }
}