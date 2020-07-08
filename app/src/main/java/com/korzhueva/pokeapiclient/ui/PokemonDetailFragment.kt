package com.korzhueva.pokeapiclient.ui

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.korzhueva.pokeapiclient.databinding.FragmentDetailBinding
import com.korzhueva.pokeapiclient.viewmodels.PokemonDetailViewModel


class PokemonDetailFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val pokemon = PokemonDetailFragmentArgs.fromBundle(requireArguments()).selectedPokemon
        val viewModelFactory = PokemonDetailViewModelFactory(pokemon, application)
        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(PokemonDetailViewModel::class.java)

        return binding.root
    }
}