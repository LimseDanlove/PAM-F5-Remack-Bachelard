package com.example.vancouver_transport

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class APIClient(cio : CIO) {
    private val client : HttpClient = HttpClient(cio)

    suspend fun getSH(){
        Log.println(Log.INFO, "TEST", "getSH")

        val httpResponse: HttpResponse = client.get("https://www.superheroapi.com/api.php/1106077463536216/1")
        val body: String = httpResponse.receive() // to parse
        Log.println(Log.INFO, "TEST", body)

        val sh = ParseSuperHero.fromJson(body)
        Log.println(Log.INFO,"TEST",sh.toString())

    }
}