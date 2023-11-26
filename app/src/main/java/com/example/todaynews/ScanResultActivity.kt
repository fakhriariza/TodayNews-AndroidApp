package com.example.todaynews

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todaynews.databinding.ActivityScanResultBinding

class ScanResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanResultBinding
    private lateinit var viewModel: MainActivityVM
    private var guestData: CountData? = null
    private var barcode: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityScanResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this)[MainActivityVM::class.java]
        initData()
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initData() {
        if (intent.hasExtra("data")) {
            guestData = intent.getParcelableExtra("data")
            binding.apply {
                tvName.text = guestData?.data?.namaUndangan
                tvStatus.text = guestData?.data?.status
                if (guestData?.data?.status?.contains("VIP") == true) {
                    rootContainer.background = resources.getDrawable(R.color.blue)
//                    tvName.setTextColor(resources.getColor(R.color.white))
//                    tvStatus.setTextColor(resources.getColor(R.color.white))
//                    tvTitle.setTextColor(resources.getColor(R.color.white))
                }
                btnBack.setOnClickListener {
                    val i = Intent(applicationContext, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }
        }
        if (intent.hasExtra("barcode")) {
            barcode = intent.getStringExtra("barcode")
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