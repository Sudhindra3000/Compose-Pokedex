package com.sudhindra.composepokedex.viemodel.details

import androidx.lifecycle.ViewModel
import com.sudhindra.composepokedex.api.PokeApi
import com.sudhindra.composepokedex.models.pokemon.EvolutionChain
import com.sudhindra.composepokedex.models.pokemon.Pokemon
import com.sudhindra.composepokedex.models.pokemon.PokemonSpecies
import com.sudhindra.composepokedex.utils.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val api: PokeApi
) : ViewModel() {

    private val _evolutionChainState: MutableStateFlow<EvolutionChainState> =
        MutableStateFlow(EvolutionChainState.Loading)
    val evolutionChainState: StateFlow<EvolutionChainState> = _evolutionChainState

    fun getEvolutionChain(id: Int) = launch {
        try {
            val species = api.getSpecies(id).body()!!
            val evolutionChain = api.getEvolutionChain(species.evolution_chain.url).body()!!
            _evolutionChainState.value = EvolutionChainState.Success(evolutionChain)
        } catch (e: Exception) {
            e.printStackTrace()
            _evolutionChainState.value = EvolutionChainState.Error("Failed to get Evolution Chain")
        }
    }

    private val _newPokemonState: MutableStateFlow<NewPokemonState> =
        MutableStateFlow(NewPokemonState.Nothing)
    val newPokemonState: StateFlow<NewPokemonState> = _newPokemonState

    fun getNewPokemonInfo(species: PokemonSpecies) = launch {
        try {
            _newPokemonState.value = NewPokemonState.Loading
            val info = api.getPokemonDetails(species.name).body()!!
            _newPokemonState.value = NewPokemonState.Success(info)
        } catch (e: Exception) {
            e.printStackTrace()
            _newPokemonState.value =
                NewPokemonState.Error("Failed to get Info for ${species.formattedName}")
        }
    }
}

sealed class EvolutionChainState {
    object Loading : EvolutionChainState()
    data class Success(val evolutionChain: EvolutionChain) : EvolutionChainState()
    data class Error(val msg: String) : EvolutionChainState()
}

sealed class NewPokemonState {
    object Nothing : NewPokemonState()
    object Loading : NewPokemonState()
    data class Success(val pokemon: Pokemon) : NewPokemonState()
    data class Error(val msg: String) : NewPokemonState()
}
