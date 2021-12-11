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
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.viewModels.WelcomeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class Welcome : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_welcome,
            container,
            false
        )

        val act = activity as MainActivity
        setHasOptionsMenu(false)
        act.toolbar.menu.clear()

        act.toolbar.title = "Welcome"

        val viewModel = WelcomeViewModel()

        binding.welcomeViewModel = viewModel

        viewModel.canNavigateToInstructionFragment.observe(viewLifecycleOwner, Observer {
            if(it == true){
                findNavController().navigate(R.id.action_welcome_to_instruction)
                viewModel.finishNavigate()
            }
        })

        return binding.root
    }
}