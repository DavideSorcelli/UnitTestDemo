package com.dsorcelli.newfeaturesproject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsorcelli.newfeaturesproject.models.Product
import com.dsorcelli.newfeaturesproject.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductDetailsVM() : ViewModel() {

    val product: LiveData<Product>
        get() = productMut
    private val productMut = MutableLiveData<Product>()

    fun retrieveProduct(productId: Int) = viewModelScope.launch {
        ProductRepository.getProdById(productId).let {
           productMut.postValue(it)
        }
    }
}

