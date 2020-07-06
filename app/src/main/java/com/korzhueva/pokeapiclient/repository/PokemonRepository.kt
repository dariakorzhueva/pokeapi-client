package com.korzhueva.pokeapiclient.repository

import android.util.Log
import com.korzhueva.pokeapiclient.models.PokemonItem
import com.korzhueva.pokeapiclient.network.PokeApi
import com.korzhueva.pokeapiclient.utils.asDomainModel

class PokemonRepository {
    lateinit var itemsConverted: List<PokemonItem>

    suspend fun refreshData(newOffset: Int) {
        try{
        val responseList = PokeApi.retrofitService.getPokemonList(offset=newOffset).await()

        itemsConverted = responseList.results.map {
            it.asDomainModel()
        }
        itemsConverted.forEach {
            val responsePokemon = PokeApi.retrofitService.getPokemonInfo(it.id).await()
            it.type = responsePokemon.types[0].type.name
            it.height = responsePokemon.height
            it.weight = responsePokemon.weight
            it.attack = responsePokemon.stats[1].baseStat
            it.defense = responsePokemon.stats[2].baseStat
            it.hp = responsePokemon.stats[0].baseStat
            it.sprite = responsePokemon.sprites.frontDefault
        }
        }
        catch(e: Exception){
            Log.d("PokeListFragmentEx","$e")
        }
    }
}