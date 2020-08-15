package com.myans.littlestockbitdev.login

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.myans.littlestockbitdev.R
import com.myans.littlestockbitdev.databinding.LoginFragmentBinding
import com.myans.littlestockbitdev.utils.autoCleared
import java.lang.reflect.Type

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
        (activity as AppCompatActivity).getSupportActionBar()?.elevation = 0f
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.default_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun bindView() {

    }

}