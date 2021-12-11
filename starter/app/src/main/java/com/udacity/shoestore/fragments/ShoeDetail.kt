package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewModels.ShoeDetailViewModel
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

        act.toolbar.title = "Shoe Details"

        val viewModel = ShoeDetailViewModel()

        binding.shoeDetailsViewModel = viewModel

        viewModel.cancelButtonClicked.observe(viewLifecycleOwner, Observer {
            if(it == true){
                findNavController().navigate(ShoeDetailDirections.actionShoeDetailToShoeList())
                viewModel.finishCanceling()
            }
        })

        viewModel.saveButtonClicked.observe(viewLifecycleOwner, Observer {
            if(it == true){
                val name = binding.etShoeDetailsName.text
                val size = binding.etShoeDetailsSize.text
                val company = binding.etShoeDetailsCompany.text
                val desc = binding.etShoeDetailsDescription.text
                if(name.isNotEmpty() && size.isNotEmpty() && company.isNotEmpty() && desc.isNotEmpty()){
                    shoeListViewModel.addNewShoe(Shoe(name.toString(), size.toString().toDouble(), company.toString(), desc.toString()))
                    viewModel.finishCanceling()
                    Toast.makeText(requireContext(), "Added successfully", Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                }
                else{
                    Toast.makeText(requireContext(), "All fields are requirements", Toast.LENGTH_LONG).show()
                }
            }
        })

        return binding.root
    }
}