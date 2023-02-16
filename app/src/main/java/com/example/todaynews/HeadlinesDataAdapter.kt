package com.example.todaynews

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.todaynews.databinding.ItemHeadlinesBinding

class HeadlinesDataAdapter(
    private val newsList: ArrayList<HeadlinesData.Articles?>
    ) : RecyclerView.Adapter<HeadlinesDataAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemHeadlinesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, i: Int) {

        holder.binding.apply {
            tvTitle.text = newsList[i]?.title
            tvSources.text = newsList[i]?.source?.name
            ivHeadlines.let {
                Glide.with(it)
                    .load(newsList[i]?.urlToImage)
                    .into(it)
            }
            rlHeadlinesCard.setOnClickListener {
                val intent = Intent(holder.itemView.context, NewsDetailActivity::class.java)
                intent.putExtra(NewsDetailActivity.TITLE, newsList[i]?.title)
                intent.putExtra(NewsDetailActivity.NEWS_URL, newsList[i]?.url)
                intent.putExtra(NewsDetailActivity.IMAGE_URL, newsList[i]?.urlToImage)
                intent.putExtra(NewsDetailActivity.AUTHOR, newsList[i]?.author)
                intent.putExtra(NewsDetailActivity.SOURCES, newsList[i]?.source?.name)
                intent.putExtra(NewsDetailActivity.DATE, newsList[i]?.publishedAt)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = 5

    class ListViewHolder(val binding: ItemHeadlinesBinding) : RecyclerView.ViewHolder(binding.root) {}
}