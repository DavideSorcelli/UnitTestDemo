package com.dsorcelli.newfeaturesproject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsorcelli.newfeaturesproject.models.Product
import com.dsorcelli.newfeaturesproject.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductsListVM : ViewModel() {

    val productsList: LiveData<List<Product>> //Dall'esterno si dà accesso a questa, per non dare possibilità alla view di modificarla.
                                            //internamente la VM si poggia alla ProductsListMut che è accessibile trmaite get ma private, pertanto può essere usata solo dalla VM
        get() = productsListMut
    private val productsListMut = MutableLiveData<List<Product>>()

    val isLoading: LiveData<Boolean>
        get() = isLoadingMut
    private val isLoadingMut = MutableLiveData<Boolean>()

    init {
        fetchProducts()
    }


    //Coroutine -> con scope view model (il thread ha lo stesso ciclo di vita del VM) viene lanciato un thread
    // che si occupa di settare isLoading a true (postValue aspetta un ciclo libero, value lo forza, ma per noi vale la pena fare sempre le cose più safe possibile)
    // E poi chiama Repository che fa da interfaccia rispetto al Database e a chiamate HTTP che invece non vanno toccate dal programma

    private fun fetchProducts() = viewModelScope.launch {
        isLoadingMut.postValue(true)

        //let permette di eseguire il blocco di funzione che viene dopo sull'elemento che l'ha chiamata
        //in questo caso su la lista di prodotti tornati dal repository
        //la products list mut viene settata con i valori ottenuti da Repository
        ProductRepository.getAll().let {
            productsListMut.postValue(it)
        }
        isLoadingMut.postValue(false)
    }

}