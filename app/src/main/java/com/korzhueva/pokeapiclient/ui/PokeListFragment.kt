package com.korzhueva.pokeapiclient.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.korzhueva.pokeapiclient.R
import com.korzhueva.pokeapiclient.adapters.PhotoGridAdapter
import com.korzhueva.pokeapiclient.databinding.FragmentPokelistBinding
import com.korzhueva.pokeapiclient.viewmodels.PokeListViewModel


class PokeListFragment : Fragment() {
    private val viewModel: PokeListViewModel by lazy {
        ViewModelProviders.of(this).get(PokeListViewModel::class.java)
    }

    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPokelistBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        binding.photoGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            viewModel.displayPokemonDetails(it)
        })

        binding.fabRandom.setOnClickListener {
            val count = viewModel.totalPokemonCount - 12
            val randomOffset = (0..count).random()

            viewModel.randomInitialization(randomOffset)
        }

        binding.photoGrid.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                var layoutManager = recyclerView.layoutManager as GridLayoutManager

                var visibleItemCount = layoutManager.childCount
                var totalItemCount = layoutManager.itemCount
                var firstVisibleItems = layoutManager.findFirstVisibleItemPosition()

                isLoading = viewModel.isLoading

                if (!isLoading) {
                    if ((visibleItemCount + firstVisibleItems) >= totalItemCount - 18) {
                        isLoading = true

                        val adapter = recyclerView.adapter as PhotoGridAdapter

                        viewModel.loadMore(adapter, totalItemCount)

                        isLoading = viewModel.isLoading
                    }
                }
            }
        })

        viewModel.navigateToSelectedPokemon.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController().navigate(PokeListFragmentDirections.actionShowDetail(it))
                viewModel.displayPokemonDetailsComplete()
            }
        })

        val animationFadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        binding.statsCheckboxes.startAnimation(animationFadeIn)
        binding.statsCheckboxes.visibility = View.VISIBLE

        setHasOptionsMenu(true)

        return binding.root
    }

}