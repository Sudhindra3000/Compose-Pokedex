package com.sudhindra.composepokedex.room

import com.sudhindra.composepokedex.models.pokemon.Pokemon
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val dao: PokemonDao
) {

    suspend fun insert(pokemon: Pokemon) = dao.insert(pokemon)

    suspend fun update(pokemon: Pokemon) = dao.update(pokemon)

    suspend fun delete(pokemon: Pokemon) = dao.delete(pokemon)

    fun getFavouritePokemon() = dao.getFavouritePokemon()

    fun getFavouritePokemonIds() =
        dao.getFavouritePokemon().map { it.map { pokemon -> pokemon.pokemonId } }
}
