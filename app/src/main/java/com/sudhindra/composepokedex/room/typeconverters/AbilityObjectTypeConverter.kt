package com.sudhindra.composepokedex.room.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.sudhindra.composepokedex.models.pokemon.AbilityObject

object AbilityObjectTypeConverter {
    @TypeConverter
    fun stringToAbilityObject(value: String?): AbilityObject? =
        value?.let { Gson().fromJson(it, AbilityObject::class.java) }

    @TypeConverter
    fun abilityObjectToString(abilityObject: AbilityObject?): String? =
        abilityObject?.let { Gson().toJson(it) }
}
