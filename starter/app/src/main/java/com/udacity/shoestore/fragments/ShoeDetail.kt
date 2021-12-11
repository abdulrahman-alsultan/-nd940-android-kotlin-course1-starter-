package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viewModels.ShoeListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class ShoeDetail : Fragment() {

    private val shoeListViewModel by lazy { ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )
        val act = activity as MainActivity
        setHasOptionsMenu(false)
        act.toolbar.menu.clear()

        act.toolbar.title = "Shoe Details"

        binding.shoeDetailsViewModel = shoeListViewModel

        shoeListViewModel.cancelButtonClicked.observe(viewLifecycleOwner, Observer {
            if(it == true){
                findNavController().navigate(ShoeDetailDirections.actionShoeDetailToShoeList())
                shoeListViewModel.finishCanceling()
            }
        })

        shoeListViewModel.saveButtonClicked.observe(viewLifecycleOwner, Observer {
            if(it == true){
                findNavController().navigate(ShoeDetailDirections.actionShoeDetailToShoeList())
                shoeListViewModel.finishSaving()
            }
        })

        return binding.root
    }
}