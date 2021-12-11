package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.viewModels.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*

class Login : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        val act = activity as MainActivity
        setHasOptionsMenu(false)
        act.toolbar.menu.clear()

        val backBtn = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backBtn)

        act.toolbar.title = "Login/Create account"

        val viewModel = LoginViewModel()

        binding.loginViewModel = viewModel

        viewModel.canNavigateToWelcomeFragment.observe(viewLifecycleOwner, Observer {
            if(it == true){
                findNavController().navigate(R.id.action_login_to_welcome)
                viewModel.finishNavigate()
            }
        })



        return binding.root
    }

}