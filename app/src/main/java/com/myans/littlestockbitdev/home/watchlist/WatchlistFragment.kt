package com.myans.littlestockbitdev.home.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.myans.database.entities.Resource
import com.myans.littlestockbitdev.R
import com.myans.littlestockbitdev.databinding.WatchlistFragmentBinding
import com.myans.littlestockbitdev.utils.autoCleared
import com.myans.littlestockbitdev.utils.setIconRight
import org.koin.androidx.viewmodel.ext.android.viewModel

class WatchlistFragment: Fragment() {

    private var binding: WatchlistFragmentBinding by autoCleared()
    private lateinit var adapter: StockAdapter
    private val viewModel: WatchViewModel by viewModel()

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
        getData()
        binding.watchlistMenu.setIconRight()
    }

    private fun getData() {
        viewModel.getTop24CrptoUpdate().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.errorEmptyLayout.visibility = View.GONE
                    if (!binding.recyclerView.isVisible) {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
                Resource.Status.ERROR -> {
                    binding.recyclerView.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    setErrorEmptyState(R.drawable.ic_error_data, it.message)
                    binding.swipeRefresh.isRefreshing = false
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorEmptyLayout.visibility = View.GONE
                    it.data?.let {
                        adapter.updateList(it)
                        binding.recyclerView.visibility = View.VISIBLE
                    }
                    if (adapter.isDataSetEmpty()) {
                        setErrorEmptyState(R.drawable.ic_empty_data, "currently there is no data :(")
                    }
                    binding.swipeRefresh.isRefreshing = false
                }
            }
        })
    }

    private fun setErrorEmptyState(statusRes: Int, message: String?) {
        binding.errorEmptyLayout.visibility = View.VISIBLE
        binding.errorEmptyText.text = message
        binding.errorEmptyImage.setImageResource(statusRes)
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
        binding.swipeRefresh.setOnRefreshListener {
            getData()
        }
        binding.errorEmptyButton.setOnClickListener {
            getData()
        }
    }


}