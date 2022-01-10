package com.example.vancouver_transport
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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
}