package com.example.todaynews

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todaynews.databinding.ItemCountBinding

class CountAdapter(private val menuList: ArrayList<DashboardData.Data.UserAttend>) : RecyclerView.Adapter<CountAdapter.RecyclerViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountAdapter.RecyclerViewHolder {
        val bind = ItemCountBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )

        return RecyclerViewHolder(bind)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: CountAdapter.RecyclerViewHolder, position: Int) {
        holder.bind(menuList[position])
    }

    inner class RecyclerViewHolder(private val bind: ItemCountBinding) :
        RecyclerView.ViewHolder(bind.root) {

        @SuppressLint("NotifyDataSetChanged")
        fun bind(data: DashboardData.Data.UserAttend) {
            bind.tvCategory.text = data.status
            bind.tvCategoryCount.text = "${data.count.toString()} Orang"
        }
    }
}
