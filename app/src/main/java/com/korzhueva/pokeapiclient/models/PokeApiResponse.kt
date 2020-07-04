package com.korzhueva.pokeapiclient.models

data class PokeApiResponse (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonLink>
)
