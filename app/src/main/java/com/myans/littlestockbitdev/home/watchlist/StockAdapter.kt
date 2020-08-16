package com.myans.littlestockbitdev.home.watchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.myans.database.Stock
import com.myans.littlestockbitdev.R
import com.myans.littlestockbitdev.databinding.StockItemBinding
import com.myans.littlestockbitdev.utils.DiffCallBackUtil
import java.text.NumberFormat
import java.util.*

class StockAdapter : RecyclerView.Adapter<StockAdapter.StocksViewHolder>()  {

    private var items: List<Stock> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StocksViewHolder {
        val binding: StockItemBinding = StockItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StocksViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newList: List<Stock>){
        val diffResult = DiffUtil.calculateDiff(ListDiffCallback(items, newList))
        items = newList
        diffResult.dispatchUpdatesTo(this)
    }

    fun setList(list: List<Stock>){
        items = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: StocksViewHolder, position: Int) = holder.bind(items[position])

    class StocksViewHolder(private val itemBinding: StockItemBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Stock) {
            itemBinding.companyName.text = item.companyName
            itemBinding.name.text = item.name
            itemBinding.currentValue.text = formatNumber(item.currentValue)
            itemBinding.differentValue.text = formatDifferentValue(item.startValue, item.currentValue)

            if (item.currentValue > item.startValue) {
                itemBinding.differentValue.setTextColor(ContextCompat.getColor(itemView.context, R.color.greenPrimary))
            } else {
                itemBinding.differentValue.setTextColor(ContextCompat.getColor(itemView.context, R.color.redPrimary))
            }
        }

        private fun formatNumber(value: Int): String {
            val format: NumberFormat = NumberFormat.getNumberInstance(Locale.US)
            format.setMaximumFractionDigits(0)
            return format.format(value)
        }

        private fun formatDifferentValue(start: Int, current: Int): String {
            val builder = StringBuilder()
            val different = current - start
            if (different > 0) {
                builder.append("+")
            }
            builder.append(start - current)
            builder.append(" (")
            if (different > 0) {
                builder.append("+")
            }
            val percentage = String.format("%.2f", (different * 100).toFloat() / start.toFloat())
            builder.append("$percentage%)")
            return builder.toString()
        }

    }

    class ListDiffCallback constructor(oldList: List<Stock>, newList: List<Stock>): DiffCallBackUtil<Stock>(oldList, newList){
        override fun checkEquality(oldItem: Stock, newItem: Stock): Boolean {
            return true
        }
    }
}