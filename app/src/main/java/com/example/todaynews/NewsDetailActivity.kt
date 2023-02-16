package com.example.todaynews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.bumptech.glide.Glide
import com.example.todaynews.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding) {
        if (intent.hasExtra(TITLE))
            tvTitle.text = intent.getStringExtra(TITLE)
        if (intent.hasExtra(IMAGE_URL))
            ivDetails.let {
                Glide.with(it)
                    .load(intent.getStringExtra(IMAGE_URL))
                    .into(it)
            }
        if (intent.hasExtra(SOURCES))
            tvSources.text = intent.getStringExtra(SOURCES)
        if (intent.hasExtra(AUTHOR))
            tvAuthor.text = intent.getStringExtra(AUTHOR)
        if (intent.hasExtra(DATE))
            tvDate.text = intent.getStringExtra(DATE)
        if (intent.hasExtra(NEWS_URL)) {
            wvDetail.webViewClient = WebViewClient()
            wvDetail.settings.javaScriptEnabled = true
            intent.getStringExtra(NEWS_URL)?.let { wvDetail.loadUrl(it) }
        }

        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        const val IMAGE_URL = "imageUrl"
        const val TITLE = "title"
        const val SOURCES = "sources"
        const val NEWS_URL = "urlNews"
        const val AUTHOR = "author"
        const val DATE = "date"
    }
}