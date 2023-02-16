package com.example.todaynews

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.todaynews.databinding.ItemHeadlinesBinding
import com.example.todaynews.databinding.ItemNewsBinding

class NewsHeadlinesDataAdapter(
    private val newsList: List<HeadlinesData.Articles?>
    ) : RecyclerView.Adapter<NewsHeadlinesDataAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, i: Int) {

        holder.binding.apply {
            tvTitle.text = newsList[i]?.title
            tvSubtitle.text = newsList[i]?.description
            ivHeadlines.let {
                Glide.with(it)
                    .load(newsList[i]?.urlToImage)
                    .into(it)
            }
            tvAuthor.text = "${newsList[i]?.author} dari ${newsList[i]?.source?.name}"
            rlNewsCard.setOnClickListener {
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

    override fun getItemCount(): Int = newsList.size

    class ListViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {}
}