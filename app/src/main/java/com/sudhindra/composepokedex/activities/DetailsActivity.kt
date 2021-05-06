package com.sudhindra.composepokedex.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sudhindra.composepokedex.constants.BundleKeys
import com.sudhindra.composepokedex.models.pokemon.Pokemon
import com.sudhindra.composepokedex.ui.screens.PokemonDetailsUi
import com.sudhindra.composepokedex.ui.utils.WithTheme
import com.sudhindra.composepokedex.utils.createIntent
import com.sudhindra.composepokedex.viemodel.details.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val viewModel by viewModels<DetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pokemon = intent.getParcelableExtra<Pokemon>(BundleKeys.POKEMON)!!
        setContent {
            WithTheme {
                PokemonDetailsUi(
                    viewModel,
                    pokemon,
                    onNewPokemonSuccess = {
                        val intent = createIntent<DetailsActivity>()
                        intent.putExtra(BundleKeys.POKEMON, it)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}
