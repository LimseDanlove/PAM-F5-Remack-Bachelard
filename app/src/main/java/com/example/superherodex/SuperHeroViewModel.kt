package com.example.superherodex
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.superherodex.model.SuperHero
import kotlinx.coroutines.launch
import io.ktor.client.engine.cio.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

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

    private fun replaceList(listSH: ArrayList<SuperHero>){
        listObj = listSH
        list.value = listObj
    }

    /*fun loadList(){
        // faire en sorte que le load ne soit pas réexécuté lors d'un changement d'orientation ?
        // cela éviterait de faire des appels réseau à chaque fois -> sort de cache
        Log.println(Log.INFO, "TEST", "LoadList")
        viewModelScope.launch {
            val client = APIClient(CIO)
            val listSH = client.getFirstSH(10)
            replaceList(listSH)
        }
    }*/

    fun addSHDB(context: Context, sh: SuperHero?){
        if (sh!=null) {
            val db = AppDatabase.getInstance(context);

            val shDao = db.shDao()

            runBlocking(Dispatchers.Default) {
                shDao.insert(
                    SuperHeroData(
                        sh!!.id,
                        sh!!.name,
                        sh!!.biography.full_name,
                        sh!!.biography.first_appearance,
                        sh!!.biography.publisher,
                        sh!!.biography.alignment,
                        sh!!.appearance.height[0]!!,
                        sh!!.appearance.height[1]!!,
                        sh!!.appearance.weight[0]!!,
                        sh!!.appearance.weight[1]!!,
                        sh!!.appearance.eye_color,
                        sh!!.appearance.hair_color,
                        sh!!.appearance.gender,
                        sh!!.appearance.race,
                        sh!!.work.occupation,
                        sh!!.image.url,
                        sh!!.connections.group_affiliation,
                        sh!!.connections.relatives
                    )
                )
            }
        }

    }

    fun loadList(context: Context){
        val db = AppDatabase.getInstance(context);

        val shDao = db.shDao()

        var listSHData : List<SuperHeroData>? = null
        runBlocking(Dispatchers.Default) {
            listSHData = shDao.getAll()
        }
        if (listSHData != null) {
           val listSH = ArrayList<SuperHero>()
           for (elt in listSHData!!) {
               listSH.add(SuperHero(elt))
           }
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