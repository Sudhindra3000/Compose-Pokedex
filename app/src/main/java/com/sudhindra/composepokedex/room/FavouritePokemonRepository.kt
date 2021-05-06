package com.sudhindra.composepokedex.room

import com.sudhindra.composepokedex.models.pokemon.Pokemon
import com.sudhindra.composepokedex.room.models.FavouritePokemon
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouritePokemonRepository @Inject constructor(
    private val dao: FavouritePokemonDao
) {

    suspend fun insert(pokemon: Pokemon) = dao.insert(FavouritePokemon(pokemon = pokemon))

    suspend fun update(pokemon: Pokemon) = dao.update(FavouritePokemon(pokemon = pokemon))

    suspend fun delete(pokemon: Pokemon) = dao.delete(FavouritePokemon(pokemon = pokemon))

    fun getFavouritePokemon() = dao.getFavouritePokemon()

    fun getFavouritePokemonIds() =
        dao.getFavouritePokemon().map { it.map { favourite -> favourite.pokemon.pokemonId } }
}
