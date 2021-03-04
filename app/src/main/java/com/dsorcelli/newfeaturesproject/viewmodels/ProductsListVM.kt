package com.dsorcelli.newfeaturesproject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsorcelli.newfeaturesproject.models.Product
import com.dsorcelli.newfeaturesproject.repository.ProductRepository
import com.dsorcelli.newfeaturesproject.utils.SimpleDatabase
import kotlinx.coroutines.launch

class ProductsListVM : ViewModel() {

    val productsList: LiveData<List<Product>>
        get() = productsListMut
    private val productsListMut = MutableLiveData<List<Product>>()

    val isLoading: LiveData<Boolean>
        get() = isLoadingMut
    private val isLoadingMut = MutableLiveData<Boolean>()

    init {
        fetchProducts()
    }

    private fun fetchProducts() = viewModelScope.launch {
        isLoadingMut.postValue(true)
        ProductRepository.getAll().let {
            productsListMut.postValue(it)
        }
        isLoadingMut.postValue(false)
    }

}