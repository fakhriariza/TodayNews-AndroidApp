package com.example.todaynews

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todaynews.databinding.ActivityScanResultBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ScanResultFirebaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanResultBinding
    private lateinit var viewModel: MainActivityVM
    private var mFirebaseDatabase: FirebaseDatabase? = null
    private var mFirebaseReference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityScanResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this)[MainActivityVM::class.java]
        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mFirebaseReference = mFirebaseDatabase?.reference
    }

    override fun onStart() {
        super.onStart()
        mFirebaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    val firebaseData: FirebaseData? = postSnapshot.getValue(FirebaseData::class.java)
                    binding.tvName.text = firebaseData?.name
                    binding.tvStatus.text = firebaseData?.status
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

}