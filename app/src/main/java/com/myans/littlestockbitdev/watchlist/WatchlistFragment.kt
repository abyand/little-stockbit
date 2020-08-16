package com.myans.littlestockbitdev.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.myans.littlestockbitdev.R
import com.myans.littlestockbitdev.databinding.LoginFragmentBinding
import com.myans.littlestockbitdev.databinding.WatchlistFragmentBinding
import com.myans.littlestockbitdev.utils.autoCleared

class WatchlistFragment: Fragment() {

    private var binding: WatchlistFragmentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WatchlistFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        bindView()
        binding.toolbar.setNavigationIcon(R.drawable.ic_toolbar_menu)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        setHasOptionsMenu(true)
    }

}