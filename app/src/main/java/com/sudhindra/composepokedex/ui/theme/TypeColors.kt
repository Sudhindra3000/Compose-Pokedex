package com.sudhindra.composepokedex.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sudhindra.composepokedex.models.pokemon.PokemonType

@Composable
fun PokemonType.colorForType() = when (this.type.name) {
    "normal" -> NormalColor
    "fighting" -> FightingColor
    "flying" -> FlyingColor
    "poison" -> PoisonColor
    "ground" -> GroundColor
    "rock" -> RockColor
    "bug" -> BugColor
    "ghost" -> GhostColor
    "steel" -> SteelColor
    "fire" -> FireColor
    "water" -> WaterColor
    "grass" -> GrassColor
    "electric" -> ElectricColor
    "psychic" -> PsychicColor
    "ice" -> IceColor
    "dragon" -> DragonColor
    "dark" -> DarkColor
    "fairy" -> FairyColor
    else -> MaterialTheme.colors.primary
}

val NormalColor = Color(0xFFBFB97F)
val FightingColor = Color(0xFFD52F31)
val FlyingColor = Color(0xFF9E87E1)
val PoisonColor = Color(0xFFAB48BD)
val GroundColor = Color(0xFFDFB352)
val RockColor = Color(0xFFBCA436)
val BugColor = Color(0xFF99BA2F)
val GhostColor = Color(0xFF7556A4)
val SteelColor = Color(0xFFB4B4CE)
val FireColor = Color(0xFFE96415)
val WaterColor = Color(0xFF2196F3)
val GrassColor = Color(0xFF4CB050)
val ElectricColor = Color(0xFFFECD07)
val PsychicColor = Color(0xFFEC407A)
val IceColor = Color(0xFF81DDEC)
val DragonColor = Color(0xFF673BB7)
val DarkColor = Color(0xFF5D4038)
val FairyColor = Color(0xFFF582BB)
