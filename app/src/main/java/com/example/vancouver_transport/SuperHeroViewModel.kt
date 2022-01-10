package com.example.vancouver_transport
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.launch
import io.ktor.client.engine.cio.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class SuperHeroViewModel : ViewModel() {

    private val listObj = ArrayList<SuperHero>(12)

    private val list = MutableLiveData<ArrayList<SuperHero>>()

    fun getList(): LiveData<ArrayList<SuperHero>> {
        return list
    }

    fun addSH(sh: SuperHero) {
        listObj.add(sh)
        list.value = listObj
    }

    fun loadList(){
        Log.println(Log.INFO, "TEST", "LoadList")
        viewModelScope.launch {
            val client = APIClient(CIO)
            client.getSH()
        }
    }

    private suspend fun getSH(){
        Log.println(Log.INFO, "TEST", "getSH")
        val client = HttpClient(CIO)
        val httpResponse: HttpResponse = client.get("https://www.superheroapi.com/api.php/1106077463536216/1")
        val body: String = httpResponse.receive() // to parse
        Log.println(Log.INFO, "TEST", body)

        val sh = ParseSuperHero.fromJson(body)
        Log.println(Log.INFO,"TEST",sh.toString())

    }
}