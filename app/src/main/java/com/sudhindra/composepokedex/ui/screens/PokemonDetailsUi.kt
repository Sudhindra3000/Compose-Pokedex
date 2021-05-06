package com.sudhindra.composepokedex.ui.screens

import android.graphics.drawable.Drawable
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import com.sudhindra.composepokedex.models.pokemon.Pokemon
import com.sudhindra.composepokedex.models.pokemon.PokemonSpecies
import com.sudhindra.composepokedex.ui.components.BackButton
import com.sudhindra.composepokedex.ui.components.PokemonImage
import com.sudhindra.composepokedex.ui.components.PokemonTypesChipGroup
import com.sudhindra.composepokedex.ui.components.ProgressDialog
import com.sudhindra.composepokedex.ui.components.pokemonDetails.PokemonAboutSection
import com.sudhindra.composepokedex.ui.components.pokemonDetails.PokemonEvolutionChain
import com.sudhindra.composepokedex.ui.components.pokemonDetails.PokemonStatsSection
import com.sudhindra.composepokedex.ui.components.toast
import com.sudhindra.composepokedex.utils.generatePalette
import com.sudhindra.composepokedex.utils.sharePokemon
import com.sudhindra.composepokedex.viemodel.details.DetailsViewModel
import com.sudhindra.composepokedex.viemodel.details.EvolutionChainState
import com.sudhindra.composepokedex.viemodel.details.NewPokemonState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DetailsAppBar(
    onShareClick: () -> Unit
) {
    TopAppBar(
        title = {},
        // TODO: 4/6/2021 : Add Transparency Effect
        backgroundColor = MaterialTheme.colors.primary,
        navigationIcon = {
            BackButton()
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Back Button"
                )
            }
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Back Button"
                )
            }
        },
        elevation = 0.dp
    )
}

@Composable
fun PokemonDetailsUi(
    viewModel: DetailsViewModel = hiltNavGraphViewModel(),
    pokemon: Pokemon,
    onNewPokemonSuccess: (Pokemon) -> Unit
) {
    var drawable: Drawable? by remember { mutableStateOf(null) }
    val defaultDominantColor = MaterialTheme.colors.surface
    var dominantColor by remember { mutableStateOf(defaultDominantColor) }
    val backgroundColor by animateColorAsState(dominantColor /*animationSpec = tween(500)*/)

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val isDark = isSystemInDarkTheme()

    LaunchedEffect(Unit) {
        viewModel.getEvolutionChain(pokemon.pokemonId)
    }

    Scaffold(
        topBar = {
            DetailsAppBar(onShareClick = {
                drawable?.let { context.sharePokemon(pokemon, it) }
            })
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.size(0.dp))
            PokemonMainInfo(
                pokemon,
                onDrawableReady = {
                    drawable = it
                    scope.launch(Dispatchers.IO) {
                        drawable?.generatePalette { palette ->
                            val swatch =
                                if (isDark) palette.darkMutedSwatch else palette.lightMutedSwatch
                            swatch?.rgb?.let { colorInt ->
                                dominantColor = Color(colorInt)
                            }
                        }
                    }
                }
            )
            PokemonSubInfo(
                pokemon = pokemon,
                evolutionChainState = viewModel.evolutionChainState.collectAsState().value,
                onPokemonClick = {
                    if (pokemon.pokemonId != it.pokemonId)
                        viewModel.getNewPokemonInfo(it)
                }
            )
        }

        when (val state = viewModel.newPokemonState.collectAsState().value) {
            NewPokemonState.Loading -> ProgressDialog(
                onDismissRequest = { },
                properties = DialogProperties(false, false)
            ) {
                Text(text = "Loading...")
            }
            is NewPokemonState.Error -> toast(state.msg)
            is NewPokemonState.Success -> onNewPokemonSuccess(state.pokemon)
        }
    }
}

@Composable
fun PokemonMainInfo(
    pokemon: Pokemon,
    onDrawableReady: (Drawable) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 45.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = pokemon.formattedName,
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.size(6.dp))
            PokemonTypesChipGroup(pokemon.types)
        }
        Text(text = "#${pokemon.pokemonId}", style = MaterialTheme.typography.h6)
    }
    PokemonImage(
        Modifier.size(280.dp),
        pokemon = pokemon,
        onDrawableReady = onDrawableReady,
        fadeIn = true,
        contentDescription = "${pokemon.formattedName} Sprite"
    )
}

val subInfoSections = listOf(
    "About",
    "Stats",
    "Evolution Chain"
)

@Composable
fun PokemonSubInfo(
    pokemon: Pokemon,
    evolutionChainState: EvolutionChainState,
    onPokemonClick: (PokemonSpecies) -> Unit
) {
    var selectedSection by rememberSaveable { mutableStateOf(0) }
    ScrollableTabRow(
        modifier = Modifier.clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
        selectedTabIndex = selectedSection,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        subInfoSections.forEachIndexed { index, s ->
            Tab(
                selected = index == selectedSection,
                onClick = { selectedSection = index }) {
                Text(text = s, modifier = Modifier.padding(10.dp))
            }
        }
    }
    Box(
        Modifier.padding(start = 18.dp, end = 18.dp, bottom = 18.dp)
    ) {
        when (selectedSection) {
            0 -> PokemonAboutSection(pokemon)
            1 -> PokemonStatsSection(pokemon)
            2 -> PokemonEvolutionChain(evolutionChainState, onPokemonClick)
        }
    }
}
