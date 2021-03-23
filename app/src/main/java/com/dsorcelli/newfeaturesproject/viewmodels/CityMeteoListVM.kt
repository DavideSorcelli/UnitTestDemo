package com.dsorcelli.newfeaturesproject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dsorcelli.newfeaturesproject.models.CityMeteo
import com.dsorcelli.newfeaturesproject.repository.CityMeteoRepository

class CityMeteoListVM : ViewModel() {


    //In qyesto modo lego questa products list al risultato live della select * dal DB
    //L'observer si mette nel fragment e guarderà questo elemento
    var productsList  : LiveData<List<CityMeteo>> = CityMeteoRepository.getAllAsLiveData()

    //Dall'esterno si dà accesso a questa, per non dare possibilità alla view di modificarla.
    //internamente la VM si poggia alla ProductsListMut che è accessibile trmaite get ma private, pertanto può essere usata solo dalla VM
    val isLoading: LiveData<Boolean>
        get() = isLoadingMut
    private val isLoadingMut = MutableLiveData<Boolean>()




    //Coroutine -> con scope view model (il thread ha lo stesso ciclo di vita del VM) viene lanciato un thread
    // che si occupa di settare isLoading a true (postValue aspetta un ciclo libero, value lo forza, ma per noi vale la pena fare sempre le cose più safe possibile)
    // E poi chiama Repository che fa da interfaccia rispetto al Database e a chiamate HTTP che invece non vanno toccate dal programma

   // private fun fetchProducts() = viewModelScope.launch {
   //     isLoadingMut.postValue(true)
    //}

}