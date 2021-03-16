package com.dsorcelli.newfeaturesproject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsorcelli.newfeaturesproject.models.Product
import com.dsorcelli.newfeaturesproject.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductDetailsVM(private val repository : ProductRepository) : ViewModel() {

    val product: LiveData<Product>
        get() = productMut
    private val productMut = MutableLiveData<Product>()

    fun retrieveProduct(productId: Int) = viewModelScope.launch {
        repository.getProdById(productId).let {
           productMut.postValue(it)
        }
    }
}

