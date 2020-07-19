package com.korzhueva.pokeapiclient.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.CheckBox
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
    private lateinit var gridAdapter: PhotoGridAdapter

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

        gridAdapter = binding.photoGrid.adapter as PhotoGridAdapter

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

        viewModel.photoList.observe(viewLifecycleOwner,Observer{
            if(it!= null){
                binding.statsCheckboxes.visibility = View.VISIBLE
            }
        })

        binding.attackCheck.setOnClickListener {
            isCheckedStat(binding.attackCheck)
            isCheckedStat(binding.defenseCheck)
            isCheckedStat(binding.hpCheck)

            binding.photoGrid.smoothScrollToPosition(0)
        }

        binding.defenseCheck.setOnClickListener {
            isCheckedStat(binding.attackCheck)
            isCheckedStat(binding.defenseCheck)
            isCheckedStat(binding.hpCheck)
        }

        binding.hpCheck.setOnClickListener {
            isCheckedStat(binding.attackCheck)
            isCheckedStat(binding.defenseCheck)
            isCheckedStat(binding.hpCheck)
        }

        viewModel.photoList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                val animationFadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
                binding.statsCheckboxes.startAnimation(animationFadeIn)
                binding.statsCheckboxes.visibility = View.VISIBLE
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun isCheckedStat(checkBox: CheckBox) {
        when (checkBox.text) {
            "Attack" -> findMaxStat(1)
            "Defense" -> findMaxStat(2)
            "HP" -> findMaxStat(3)
            else -> return
        }

    }

    private fun findMaxStat(flag: Int) {
        val maxStat = when (flag) {
            1 -> viewModel.photoList.value!!.maxBy { it.attack }
            2 -> viewModel.photoList.value!!.maxBy { it.defense }
            3 -> viewModel.photoList.value!!.maxBy { it.hp }
            else -> null
        }

        if (maxStat != null && maxStat.id != (viewModel.photoList.value)!![0].id) {
            (viewModel.photoList.value)!!.add(0, maxStat)

            gridAdapter.notifyItemInserted(0)
        }
    }
}