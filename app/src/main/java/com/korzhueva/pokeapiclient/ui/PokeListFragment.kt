package com.korzhueva.pokeapiclient.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.korzhueva.pokeapiclient.databinding.FragmentPokelistBinding

class PokeListFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPokelistBinding.inflate(inflater)
        binding.setLifecycleOwner(this)



        return super.onCreateView(inflater, container, savedInstanceState)
    }
}