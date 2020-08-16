package com.myans.littlestockbitdev.utils

import androidx.recyclerview.widget.DiffUtil

abstract class DiffCallBackUtil<T> constructor(oldList: List<T>, newList: List<T>) : DiffUtil.Callback() {

    lateinit var oldList : List<T>
    lateinit var newList : List<T>

    abstract fun checkEquality(oldItem: T, newItem: T) : Boolean
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = checkEquality(oldList[oldItemPosition], newList[newItemPosition])
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) : Boolean{
        oldList[oldItemPosition]?.let {
            return it?.equals(newList[newItemPosition]) ?: false
        }
        return false
    }

}