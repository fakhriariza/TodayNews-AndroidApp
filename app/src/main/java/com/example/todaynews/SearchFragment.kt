package com.example.todaynews

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todaynews.databinding.FragmentSearchBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val SEARCH_DATA = "searchData"

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val articleType = object : TypeToken<ArrayList<HeadlinesData.Articles?>>() {}.type
    private lateinit var viewModel: MainActivityVM

    private var searchData: String? = null
    private var searchDataList: ArrayList<HeadlinesData.Articles?> = ArrayList()
    private lateinit var headlinesNewsAdapter: NewsHeadlinesDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            searchData = it.getString(SEARCH_DATA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this)[MainActivityVM::class.java]
        initView()
        return view
    }

    @SuppressLint("SetTextI18n")
    private fun initView() = with(binding){
        if (!searchData.isNullOrEmpty()) {
            searchDataList = Gson().fromJson(searchData, articleType)
            if (!searchDataList.isNullOrEmpty()) {
                nsvNews.visibility = View.VISIBLE
                tvInfo.visibility = View.GONE
                headlinesNewsAdapter = NewsHeadlinesDataAdapter(searchDataList)
                val layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                rvNews.layoutManager = layoutManager
                rvNews.adapter = headlinesNewsAdapter
            } else {
                nsvNews.visibility = View.GONE
                tvInfo.visibility = View.VISIBLE
                tvInfo.text = "Maaf, kata yang kamu cari tidak ada"
            }
        } else {
            nsvNews.visibility = View.GONE
            tvInfo.visibility = View.VISIBLE
            tvInfo.text = getString(R.string.search_noticetext)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(searchData: String?) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(SEARCH_DATA, searchData)
                }
            }
    }
}