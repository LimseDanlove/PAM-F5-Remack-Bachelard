package com.example.superherodex
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.superherodex.model.SuperHero

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

    fun replaceList(listSH: ArrayList<SuperHero>){
        listObj = listSH
        list.value = listObj
    }
}