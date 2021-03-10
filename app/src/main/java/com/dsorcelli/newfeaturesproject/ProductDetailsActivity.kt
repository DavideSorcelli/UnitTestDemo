package com.dsorcelli.newfeaturesproject

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dsorcelli.newfeaturesproject.databinding.ActivityProductDetailBinding
import com.dsorcelli.newfeaturesproject.viewmodels.ProductDetailsVM

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private val viewModel by viewModels<ProductDetailsVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        //Si setta come view la root dell'elemento del binding
        setContentView(binding.root)

        val productId = intent.getIntExtra(ProductsActivity.EXTRA_PRODUCT_ID, 0)
        print("productID = $productId")

        viewModel.retrieveProduct(productId)

        viewModel.product.observe(this) {
            it?.let {
                with(binding) {
                    tvProductName.text = it.name
                    tvProductId.text = it.id.toString()
                    tvProductPrice.text = it.price.toString()
                }
            } ?: run {
                Log.e("ProductDetailActivity", "Product not found")
            }
        }
    }


}