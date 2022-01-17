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

    private var listObj = ArrayList<SuperHero>(12)

    private val list = MutableLiveData<ArrayList<SuperHero>>()

    fun getList(): LiveData<ArrayList<SuperHero>> {
        return list
    }

    fun addSH(sh: SuperHero) {
        listObj.add(sh)
        list.value = listObj
    }

    private fun replaceList(listSH : ArrayList<SuperHero>){
        listObj = listSH
        list.value = listObj
    }

    fun loadList(){
        // faire en sorte que le load ne soit pas réexécuté lors d'un changement d'orientation ?
        // cela éviterait de faire des appels réseau à chaque fois -> sort de cache
        Log.println(Log.INFO, "TEST", "LoadList")
        viewModelScope.launch {
            val client = APIClient(CIO)
            val listSH = client.getFirstSH(10)
            replaceList(listSH)
        }
    }

    fun getListName(query : String){
        viewModelScope.launch {
            val client = APIClient(CIO)
            val listSH = client.getSH(query)
            Log.println(Log.INFO,"TEST",listSH.toString())
            replaceList(listSH)
        }
    }
}