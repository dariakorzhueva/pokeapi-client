package com.korzhueva.pokeapiclient.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.korzhueva.pokeapiclient.databinding.FragmentDetailBinding
import com.korzhueva.pokeapiclient.viewmodels.PokemonDetailViewModel

class PokemonDetailFragment: Fragment() {
    private val viewModel : PokemonDetailViewModel by lazy{
        ViewModelProviders.of(this).get(PokemonDetailViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val pokemon = PokemonDetailFragmentArgs.fromBundle(requireArguments()).selectedProperty
        val viewModelFactory = PokemonDetailViewModelFactory(pokemon, application)
        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(PokemonDetailViewModel::class.java)

        return binding.root
    }
}