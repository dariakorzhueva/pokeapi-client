package com.korzhueva.pokeapiclient.ui

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.korzhueva.pokeapiclient.adapters.PhotoGridAdapter
import com.korzhueva.pokeapiclient.databinding.FragmentPokelistBinding
import com.korzhueva.pokeapiclient.viewmodels.PokeListViewModel


class PokeListFragment : Fragment(){
    private val viewModel : PokeListViewModel by lazy{
        ViewModelProviders.of(this).get(PokeListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPokelistBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        binding.photoGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener{
            viewModel.displayPokemonDetails(it)
        })

        viewModel.navigateToSelectedPokemon.observe(viewLifecycleOwner, Observer {
            if ( it != null ) {
                this.findNavController().navigate(PokeListFragmentDirections.actionShowDetail(it))
                viewModel.displayPokemonDetailsComplete()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

}