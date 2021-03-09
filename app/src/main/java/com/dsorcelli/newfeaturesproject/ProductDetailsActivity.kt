package com.dsorcelli.newfeaturesproject

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.dsorcelli.newfeaturesproject.databinding.ActivityMainBinding
import com.dsorcelli.newfeaturesproject.databinding.ActivityProductDetailBinding
import com.dsorcelli.newfeaturesproject.viewmodels.ProductDetailsVM
import com.dsorcelli.newfeaturesproject.viewmodels.ProductsListVM

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private val viewModel by viewModels<ProductDetailsVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        //Si setta come view la root dell'elemento del binding
        setContentView(binding.root)
        val productId = intent.getIntExtra("product_id", 0)
        print("productID = $productId")

        viewModel.retrieveProduct(productId)

        viewModel.product.observe(this) {
            binding.detailsIdValueTv.text = it.id.toString()
            binding.detailsNameValueTv.text = it.name
            binding.detailsPriceValueTv.text = it.price.toString()

        }
    }



}