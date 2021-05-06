package com.sudhindra.composepokedex.room.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.sudhindra.composepokedex.models.pokemon.PokemonType

object PokemonTypeTypeConverter {
    @TypeConverter
    fun stringToPokemonType(value: String?): PokemonType? =
        value?.let { Gson().fromJson(it, PokemonType::class.java) }

    @TypeConverter
    fun pokemonTypeToString(pokemonType: PokemonType?): String? =
        pokemonType?.let { Gson().toJson(it) }
}
