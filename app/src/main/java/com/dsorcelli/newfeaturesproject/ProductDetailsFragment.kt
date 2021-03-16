package com.dsorcelli.newfeaturesproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dsorcelli.newfeaturesproject.databinding.FragmentProductDetailBinding
import com.dsorcelli.newfeaturesproject.viewmodels.ProductDetailsVM

class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    // queste si chiamano delegate properties
    // per maggiori info vedi https://kotlinlang.org/docs/delegated-properties.html
    private val viewModel by viewModels<ProductDetailsVM>()
    private val args: ProductDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        val productId = args.productId
        Log.d(TAG, "Product ID: $productId")

        viewModel.retrieveProduct(productId)

        viewModel.product.observe(viewLifecycleOwner) {
            it?.let {
                with(binding) {
                    ivProductImg.setImageResource(it.img!!)
                    tvProductName.text = it.name
                    tvProductPrice.text = it.price.toString()
                }
            } ?: run {
                Log.e(TAG, "Product not found")
            }
        }

        binding.btnProductDetailsBack.setOnClickListener {
            findNavController().navigateUp()
        }

        //Si setta come view la root dell'elemento del binding
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "ProductDetailsFragment"
    }

}
