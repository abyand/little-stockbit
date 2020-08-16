package com.myans.littlestockbitdev.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myans.littlestockbitdev.databinding.EmptyFragmentBinding
import com.myans.littlestockbitdev.utils.autoCleared

class EmptyFragment: Fragment() {
    private var binding: EmptyFragmentBinding by autoCleared()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EmptyFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}