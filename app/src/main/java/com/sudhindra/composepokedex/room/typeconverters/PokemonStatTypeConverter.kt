package com.sudhindra.composepokedex.room.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.sudhindra.composepokedex.models.pokemon.PokemonStat

object PokemonStatTypeConverter {
    @TypeConverter
    fun stringToPokemonStat(value: String?): PokemonStat? =
        value?.let { Gson().fromJson(it, PokemonStat::class.java) }

    @TypeConverter
    fun pokemonStatToString(pokemonStat: PokemonStat?): String? =
        pokemonStat?.let { Gson().toJson(it) }
}
