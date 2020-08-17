package com.myans.littlestockbitdev.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.myans.littlestockbitdev.R
import com.myans.littlestockbitdev.databinding.HomeFragmentBinding
import com.myans.littlestockbitdev.home.watchlist.WatchlistFragment
import com.myans.littlestockbitdev.utils.autoCleared

class HomeFragment: Fragment() {
    private var binding: HomeFragmentBinding by autoCleared()
    private lateinit var tabAdapter: HomeTabAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationIcon(R.drawable.ic_toolbar_menu)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        setHasOptionsMenu(true)

        bindViewPager()
    }

    private fun bindViewPager() {
        binding.viewPager.offscreenPageLimit = 5
        tabAdapter = HomeTabAdapter(parentFragmentManager)
        tabAdapter.pages = populateTabItems()
        binding.viewPager.adapter = tabAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        tabAdapter.pages.forEachIndexed { index, homeTabItem ->
            binding.tabLayout.getTabAt(index)?.icon = homeTabItem.iconResource
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun populateTabItems(): List<HomeTabAdapter.HomeTabItem> {
        return listOf(
            HomeTabAdapter.HomeTabItem(
                fragment = WatchlistFragment(),
                title = requireContext().resources.getString(R.string.tab_watchlist),
                iconResource = ContextCompat.getDrawable(requireContext(), R.drawable.ic_tab_watchlist)
            ),
            HomeTabAdapter.HomeTabItem(
                fragment = EmptyFragment(),
                title = requireContext().resources.getString(R.string.tab_stream),
                iconResource = ContextCompat.getDrawable(requireContext(), R.drawable.ic_tab_stream)
            ),
            HomeTabAdapter.HomeTabItem(
                fragment = EmptyFragment(),
                title = requireContext().resources.getString(R.string.tab_search),
                iconResource = ContextCompat.getDrawable(requireContext(), R.drawable.ic_tab_search)
            ),
            HomeTabAdapter.HomeTabItem(
                fragment = EmptyFragment(),
                title = requireContext().resources.getString(R.string.tab_chat),
                iconResource = ContextCompat.getDrawable(requireContext(), R.drawable.ic_tab_chat)
            ),
            HomeTabAdapter.HomeTabItem(
                fragment = EmptyFragment(),
                title = requireContext().resources.getString(R.string.tab_portfolio),
                iconResource = ContextCompat.getDrawable(requireContext(), R.drawable.ic_tab_portfolio)
            )
        )
    }
}