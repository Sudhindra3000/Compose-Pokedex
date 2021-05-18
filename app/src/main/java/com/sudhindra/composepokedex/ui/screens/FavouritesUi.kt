package com.sudhindra.composepokedex.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import com.sudhindra.composepokedex.ui.components.PokemonCard
import com.sudhindra.composepokedex.viemodel.FavouritesViewModel

@Composable
fun FavouritesUi(
    viewModel: FavouritesViewModel = hiltNavGraphViewModel()
) {
    val favourites by viewModel.favouritePokemon.collectAsState(listOf())
    LazyColumn {
        items(favourites) { favourite ->
            PokemonCard(
                pokemon = favourite.pokemon,
                onClick = { },
                inFavourites = true,
                onFavouriteClick = {},
                onUnFavouriteClick = {}
            )
        }
    }
}
