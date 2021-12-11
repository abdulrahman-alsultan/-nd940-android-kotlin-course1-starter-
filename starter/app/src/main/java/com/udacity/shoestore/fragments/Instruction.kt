package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionBinding
import com.udacity.shoestore.viewModels.InstructionViewModel
import kotlinx.android.synthetic.main.activity_main.*

class Instruction : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentInstructionBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_instruction,
            container,
            false
        )
        val act = activity as MainActivity

        act.toolbar.title = "Instruction"

        val viewModel = InstructionViewModel()

        binding.instructionViewModel = viewModel

        viewModel.canNavigateToShoeList.observe(viewLifecycleOwner, Observer {
            if(it == true){
                findNavController().navigate(R.id.action_instruction_to_shoeList)
                viewModel.finishNavigate()
            }
        })

        return binding.root
    }

}