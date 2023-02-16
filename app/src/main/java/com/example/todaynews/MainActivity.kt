package com.example.todaynews

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.todaynews.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityVM
    private var homeFragment: HomeFragment = HomeFragment()
    private var searchFragment: SearchFragment = SearchFragment()
    private var headlinesData: String? = null
    private var searchData: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this)[MainActivityVM::class.java]
        initData()
        initView()
        fragmentManager()
    }

    private fun changeFragment(id: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim)
            .replace(R.id.fcvMainContainer, id, null)
            .setReorderingAllowed(true).commit()
    }

    private fun fragmentManager() {
        binding.bnvMainActivity.setOnItemSelectedListener { item: MenuItem ->
            val itemId = item.itemId
            if (itemId == R.id.navigation_home) {
                changeFragment(homeFragment)
                return@setOnItemSelectedListener true
            } else if (itemId == R.id.navigation_add) {
                changeFragment(searchFragment)
                return@setOnItemSelectedListener true
            }
            false
        }
    }
    private fun initData() {
        initLoading()
        viewModel.fetchHeadlinesData()
        viewModel.headlinesData.observe(this) {response: HeadlinesData ->
            val data = response.data
            if (data != null) {
                headlinesData = Gson().toJson(data)
                homeFragment = HomeFragment.newInstance(headlinesData)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fcvMainContainer, homeFragment, null).commit()
            }
        }
    }
    private fun initView() {
        binding.ivRefresh.setOnClickListener {
            initLoading()
            initData()
        }
        binding.ivSearch.setOnClickListener {
            initLoading()
            if (binding.bnvMainActivity.selectedItemId == R.id.navigation_home) {
                binding.bnvMainActivity.selectedItemId = R.id.navigation_add
            }
            val searchText = binding.etSearch.text.toString()
            viewModel.fetchSearchData(searchText)
            viewModel.searchData.observe(this) {response: HeadlinesData ->
                val data = response.data
                if (data != null) {
                    searchData = Gson().toJson(data)
                    searchFragment = SearchFragment.newInstance(searchData)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcvMainContainer, searchFragment, null).commit()
                }
            }
        }
    }

    private fun initLoading() {
        val splashTime: Long = 2000
        binding.progressBar.visibility = View.VISIBLE
        Handler().postDelayed({
            binding.progressBar.visibility = View.GONE
        }, splashTime)
    }

}