package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.viewModels.ShoeListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class ShoeList : Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_shoe_list,
                container,
                false
        )
        val act = activity as MainActivity

        act.toolbar.title = "Shoe List"
        setHasOptionsMenu(true)
        act.toolbar.menu.clear()
        act.toolbar.inflateMenu(R.menu.menu)
        act.toolbar.setOnMenuItemClickListener {
            viewModel.clear()
            findNavController().navigate(ShoeListDirections.actionShoeListToLogin())
            true
        }

        binding.shoeListViewModel  = viewModel

        viewModel.getShoeList().observe(viewLifecycleOwner, Observer { items ->
            for (item in items){
                val ll = LayoutInflater.from(requireContext()).inflate(R.layout.custom_shoe, binding.llShoeListDisplay, false)

                val name = ll.findViewById<TextView>(R.id.tv_custom_name)
                val size = ll.findViewById<TextView>(R.id.tv_custom_size)
                val company = ll.findViewById<TextView>(R.id.tv_custom_company)
                val desc = ll.findViewById<TextView>(R.id.tv_custom_description)

                name.text = item.name
                size.text = "Size: ${item.size.toString()}"
                company.text = "Company ${item.company}"
                desc.text = "Description: ${item.description}"

                binding.llShoeListDisplay.addView(ll)

            }
        })

        viewModel.navigateToDetails.observe(viewLifecycleOwner, Observer {
            if(it == true){
                findNavController().navigate(ShoeListDirections.actionShoeListToShoeDetail())
                viewModel.finishNavigate()
            }
        })


        return binding.root
    }
}