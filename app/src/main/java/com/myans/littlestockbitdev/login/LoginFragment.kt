package com.myans.littlestockbitdev.login

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.myans.littlestockbitdev.R
import com.myans.littlestockbitdev.databinding.LoginFragmentBinding
import com.myans.littlestockbitdev.utils.autoCleared

class LoginFragment: Fragment() {

    private var binding: LoginFragmentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.login_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun bindView() {
        binding.defaultLogin.setOnClickListener {
            findNavController().navigate(R.id.login_fragment_to_home_fragment)
        }
        binding.facebookLogin.setOnClickListener {
            findNavController().navigate(R.id.login_fragment_to_home_fragment)
        }
        binding.googleLogin.setOnClickListener {
            findNavController().navigate(R.id.login_fragment_to_home_fragment)
        }
        binding.fingerprintLogin.setOnClickListener {
            findNavController().navigate(R.id.login_fragment_to_home_fragment)
        }
    }

}