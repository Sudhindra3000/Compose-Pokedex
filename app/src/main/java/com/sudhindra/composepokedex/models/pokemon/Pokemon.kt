package com.sudhindra.composepokedex.models.pokemon

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.sudhindra.composepokedex.room.typeconverters.AbilityObjectTypeConverter
import com.sudhindra.composepokedex.room.typeconverters.PokemonStatTypeConverter
import com.sudhindra.composepokedex.room.typeconverters.PokemonTypeTypeConverter
import com.sudhindra.composepokedex.utils.capitalize
import com.sudhindra.composepokedex.utils.splitAndCapitalise
import kotlinx.parcelize.Parcelize

@Entity(tableName = "pokemon_table")
@Parcelize
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val pokemonId: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    @TypeConverters(AbilityObjectTypeConverter::class)
    val abilities: List<AbilityObject>,
    @TypeConverters(PokemonStatTypeConverter::class)
    val stats: List<PokemonStat>,
    @TypeConverters(PokemonTypeTypeConverter::class)
    val types: List<PokemonType>,
) : Parcelable {

    val formattedName: String
        get() = name.splitAndCapitalise()

    fun getPokemonSprite(): String =
        if (pokemonId < 10001) "https://pokeres.bastionbot.org/images/pokemon/$pokemonId.png" else "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png"

    fun abilitiesAsString() = abilities.joinToString { it.ability.name.splitAndCapitalise() }

    val pokemonShareString: String
        get() = """
            ID : $pokemonId
            Pokémon : $formattedName
            
            ${if (types.size == 1) "Type" else "Types"} : ${types.joinToString { it.type.name.capitalize() }}

            Hp : ${stats[0].base_stat}
            Attack : ${stats[1].base_stat}
            Defense : ${stats[2].base_stat}
            Sp. Attack : ${stats[3].base_stat}
            Sp. Defense : ${stats[4].base_stat}
            Speed : ${stats[5].base_stat}
            """.trimIndent()
}
