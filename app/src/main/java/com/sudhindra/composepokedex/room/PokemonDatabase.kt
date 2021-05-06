package com.sudhindra.composepokedex.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sudhindra.composepokedex.models.pokemon.Pokemon
import com.sudhindra.composepokedex.room.typeconverters.AbilityObjectTypeConverter
import com.sudhindra.composepokedex.room.typeconverters.PokemonStatTypeConverter
import com.sudhindra.composepokedex.room.typeconverters.PokemonTypeTypeConverter

@Database(entities = [Pokemon::class], version = 1)
@TypeConverters(
    AbilityObjectTypeConverter::class,
    PokemonStatTypeConverter::class,
    PokemonTypeTypeConverter::class
)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
}
