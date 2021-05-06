package com.sudhindra.composepokedex.viemodel.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    var allPokemonPager = repository.getAllPokemon(viewModelScope)

    fun getTypedPokemon(typeId: Int) = repository.getTypedPokemon(viewModelScope, typeId)

    fun getRegionalPokemon(regionId: Int) = repository.getRegionalPokemon(viewModelScope, regionId)
}
