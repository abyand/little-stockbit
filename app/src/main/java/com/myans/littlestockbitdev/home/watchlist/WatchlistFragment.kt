package com.myans.littlestockbitdev.home.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.myans.database.Stock
import com.myans.littlestockbitdev.databinding.WatchlistFragmentBinding
import com.myans.littlestockbitdev.utils.autoCleared
import com.myans.littlestockbitdev.utils.setIconRight
import kotlin.random.Random

class WatchlistFragment: Fragment() {

    private var binding: WatchlistFragmentBinding by autoCleared()
    private lateinit var adapter: StockAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WatchlistFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
        binding.watchlistMenu.setIconRight()
    }

    private fun bindView() {
        adapter = StockAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        populateDummyEntries()
    }

    private fun populateDummyEntries() {
        val dummyList: MutableList<Stock> = mutableListOf()
        for (i in 0 .. 20) {
            val currentValue = Random.nextInt(5000, 70000)
            dummyList.add(
                Stock(
                    name = "ABC$i",
                    companyName = "Company $i Tbk.",
                    currentValue = currentValue,
                    startValue = Random.nextInt(currentValue - 2000, currentValue + 2000)
                )
            )
        }
        adapter.setList(dummyList)
    }

}