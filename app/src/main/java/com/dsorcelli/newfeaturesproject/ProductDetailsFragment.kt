package com.dsorcelli.newfeaturesproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.bind
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.dsorcelli.newfeaturesproject.databinding.FragmentProductDetailBinding
import com.dsorcelli.newfeaturesproject.repository.ProductRepository
import com.dsorcelli.newfeaturesproject.viewmodels.ProductDetailsVM
import kotlinx.coroutines.launch

class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding
    private val viewModel by viewModels<ProductDetailsVM>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        val args = ProductDetailsFragmentArgs.fromBundle(requireArguments())
        val productId = args.productId
        Log.d("e",productId.toString())

        viewModel.retrieveProduct(productId)

        viewModel.product.observe(viewLifecycleOwner) {
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

            binding.btnProductDetailsBack.setOnClickListener{
                binding.root.findNavController().navigate(R.id.action_productDetailsFragment_to_productsListFragment)
            }


            //Si setta come view la root dell'elemento del binding
            return binding.root
        }
}
