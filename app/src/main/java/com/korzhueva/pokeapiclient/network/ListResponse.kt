package com.korzhueva.pokeapiclient.network

class ListResponse (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonLink>
)
