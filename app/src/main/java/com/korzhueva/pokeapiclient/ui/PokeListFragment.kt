package com.korzhueva.pokeapiclient.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.korzhueva.pokeapiclient.adapters.PhotoGridAdapter
import com.korzhueva.pokeapiclient.databinding.FragmentPokelistBinding
import com.korzhueva.pokeapiclient.viewmodels.PokeListViewModel


class PokeListFragment : Fragment() {
    private val viewModel: PokeListViewModel by lazy {
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

        binding.photoGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            viewModel.displayPokemonDetails(it)
        })

        binding.photoGrid.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val gridLayoutManager = recyclerView.layoutManager as GridLayoutManager?

                if (gridLayoutManager != null) {
                    val visibleItemCount = gridLayoutManager.childCount
                    val totalItemCount = gridLayoutManager.itemCount
                    val pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition()

                    Log.d("pokemooooon","$visibleItemCount $totalItemCount $pastVisiblesItems")

                    var countItems = 0

                    //TODO: fix scroll condition

                    if (pastVisiblesItems > countItems) {
                        viewModel.loadMore()

                        countItems += 30

                        recyclerView.adapter!!.notifyItemChanged(
                            countItems,
                            (viewModel.photoList.value!!.size)!!.minus(1)
                        )

                        countItems += 9
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

        setHasOptionsMenu(true)

        return binding.root
    }

}