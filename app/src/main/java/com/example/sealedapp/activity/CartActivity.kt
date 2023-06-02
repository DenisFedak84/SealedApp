package com.example.sealedapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sealedapp.R
import com.example.sealedapp.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {

    private var _binding: ActivityCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initToolbar()
    }

    private fun initBinding() {
        _binding = ActivityCartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun initToolbar() {
        binding.toolbar.apply {
            setSupportActionBar(this)
            supportActionBar!!.title = ""
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            this.setNavigationOnClickListener { arrow -> onBackPressedDispatcher.onBackPressed() }
        }
    }
}