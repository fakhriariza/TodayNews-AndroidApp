package com.example.todaynews

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todaynews.databinding.FragmentHomeBinding
import com.google.android.material.chip.Chip
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val HEADLINES_DATA = "headlinesData"

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val articleType = object : TypeToken<ArrayList<HeadlinesData.Articles?>>() {}.type
    private lateinit var viewModel: MainActivityVM

    private var headlinesData: String? = null
    private var dataHeadlines: ArrayList<HeadlinesData.Articles?> = ArrayList()
    private lateinit var headlinesDataAdapter: HeadlinesDataAdapter
    private lateinit var headlinesNewsAdapter: NewsHeadlinesDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            headlinesData = it.getString(HEADLINES_DATA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this)[MainActivityVM::class.java]
        initView()
        return view
    }

    private fun initView() = with(binding){
        if (headlinesData != null) {
            dataHeadlines = Gson().fromJson(headlinesData, articleType)
            headlinesDataAdapter = HeadlinesDataAdapter(dataHeadlines)
            val layoutManagerHeadlines =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rvHeadlines.layoutManager = layoutManagerHeadlines
            binding.rvHeadlines.adapter = headlinesDataAdapter

            initHeadline()
        }

        menuChip.setOnCheckedStateChangeListener{ group, checkId ->
            val layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            for (i in checkId.indices) {
                val chip: Chip? = group.findViewById(checkId[i])
                chip?.let { chipView ->
                    when (chip.text) {
                        getString(R.string.chip_headlines) -> {
                            initHeadline()
                        }
                        getString(R.string.chip_detik) -> {
                            initVM("detik.com")
                        }
                        getString(R.string.chip_cnbc) -> {
                            initVM("cnbcindonesia.com")
                        }
                        getString(R.string.chip_cnn) -> {
                            initVM("cnnindonesia.com")
                        }
                        getString(R.string.chip_tempo) -> {
                            initVM("tempo.co")
                        }
                        getString(R.string.chip_viva) -> {
                            initVM("viva.co.id")
                        }
                        getString(R.string.chip_republika) -> {
                            initVM("republika.co.id")
                        }

                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initVM(domain: String) {
        initLoading()
        viewModel.fetchEverythingData(domain)
        viewModel.newsData.observe(requireActivity()) {response: HeadlinesData ->
            val data = response.data
            if (!data.isNullOrEmpty()) {
                binding.rvNews.visibility = View.VISIBLE
                binding.tvNotice.visibility = View.GONE
                headlinesNewsAdapter = NewsHeadlinesDataAdapter(data)
                val layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.rvNews.layoutManager = layoutManager
                binding.rvNews.adapter = headlinesNewsAdapter
            } else {
                binding.rvNews.visibility = View.GONE
                binding.tvNotice.text = "Maaf, belum ada berita terbaru dari $domain"
                binding.tvNotice.visibility = View.VISIBLE
            }
        }
    }

    private fun initHeadline() {
        initLoading()
        if (!dataHeadlines.isNullOrEmpty()) {
            binding.rvNews.visibility = View.VISIBLE
            binding.tvNotice.visibility = View.GONE
            dataHeadlines = Gson().fromJson(headlinesData, articleType)
            headlinesNewsAdapter = NewsHeadlinesDataAdapter(dataHeadlines)
            val layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.rvNews.layoutManager = layoutManager
            binding.rvNews.adapter = headlinesNewsAdapter
        }
        else {
            binding.rvNews.visibility = View.GONE
            binding.tvNotice.text = "Maaf, terdapat kesalahan dalam mengambil data"
            binding.tvNotice.visibility = View.VISIBLE
        }
    }

    private fun initLoading() {
        val splashTime: Long = 2000
        binding.progressBar.visibility = View.VISIBLE
        Handler().postDelayed({
            binding.progressBar.visibility = View.GONE
        }, splashTime)
    }

    companion object {
        @JvmStatic
        fun newInstance(headlinesData: String?) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(HEADLINES_DATA, headlinesData)
                }
            }
    }
}