package com.dsorcelli.newfeaturesproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.dsorcelli.newfeaturesproject.databinding.ActivityMainBinding
import com.dsorcelli.newfeaturesproject.viewmodels.ProductsListVM

class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<ProductsListVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerObservers()
    }

    private fun registerObservers() {

        viewModel.productsList.observe(this, {
            Log.d(TAG, "Received products update: $it")
        })

    }

    companion object {
        private const val TAG = "ProductsActivity"
    }

}