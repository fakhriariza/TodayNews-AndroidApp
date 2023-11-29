package com.example.todaynews

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todaynews.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.google.zxing.client.android.Intents
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityVM
//    private val mahasiswa: Mahasiswa? = null

    var mDatabase: DatabaseReference? = null
    private var mFirebaseDatabase: FirebaseDatabase? = null
    private var mFirebaseReference: DatabaseReference? = null

    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            val originalIntent = result.originalIntent
            if (originalIntent == null) {
                Log.d("MainActivity", "Cancelled scan")
                Toast.makeText(this@MainActivity, "Cancelled", Toast.LENGTH_LONG).show()
            } else if (originalIntent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION)) {
                Log.d(
                    "MainActivity",
                    "Cancelled scan due to missing camera permission"
                )
                Toast.makeText(
                    this@MainActivity,
                    "Cancelled due to missing camera permission",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            scanBarcode(result.contents)
            Toast.makeText(
                this@MainActivity,
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this)[MainActivityVM::class.java]
        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mFirebaseReference = mFirebaseDatabase?.reference
        initData()
        initView()
    }
    private fun initData() {
        viewModel.fetchCountData()
        viewModel.countData.observe(this) {response: DashboardData ->
            val data = response.data?.totalCount
            binding.tvCountValue.text = "$data Orang"
            val listData = response.data?.userAttend
            binding.rvCount.adapter = listData?.let { CountAdapter(it) }
        }
    }
    @SuppressLint("StringFormatInvalid")
    private fun initView() {
        binding.ivRefresh.setOnClickListener {
//            val i = Intent(applicationContext, ScanResultFirebaseActivity::class.java)
//            startActivity(i)
            finish()
            initLoading()
            initData()
        }
        binding.bnvMainActivity.setOnClickListener {
            scanToolbar()
        }
    }

    private fun scanBarcode(barcodeNo: String) {
        viewModel.fetchGuestData(barcodeNo)
        viewModel.guestData.observe(this) {
            if (it != null) {
                val id = mFirebaseReference?.push()?.key
                val name = it.data?.namaUndangan
                val status = it.data?.status
                val firebaseData = FirebaseData(name, status)
                if (id != null) {
                    mFirebaseReference?.child("users")?.setValue(firebaseData)
                }
                val i = Intent(applicationContext, ScanResultActivity::class.java)
                i.putExtra("data" , it as Parcelable)
                i.putExtra("barcode" , barcodeNo)
                startActivity(i)
            } else {
                Toast.makeText(this, "Data Tamu Tidak Ditemukan", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun scanToolbar() {
        val options = ScanOptions().setCaptureActivity(ToolbarCaptureActivity::class.java)
        barcodeLauncher.launch(options)
    }

    private fun initLoading() {
        val splashTime: Long = 2000
        binding.progressBar.visibility = View.VISIBLE
        Handler().postDelayed({
            binding.progressBar.visibility = View.GONE
        }, splashTime)
    }

}