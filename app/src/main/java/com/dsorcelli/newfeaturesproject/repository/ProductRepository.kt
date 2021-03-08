package com.dsorcelli.newfeaturesproject.repository

import com.dsorcelli.newfeaturesproject.database.ProductDao

object ProductRepository {

    //La classe Repository si occupa di fare da interfaccia tra la ViewModel e il data layer
    //In pratica fornisce dei metodi per prendere i dati dalla classe database invocandone i metodi primitivi
    //o per prendere i dati da interent tramite chiamate ad endpoint: è un'astrazione del data layer vero e proprio.

    //Una funzione suspended può essere chiamata solo da un' altra funzione supsended.
    //In questo caso la getAll viene chiamata nell'ambito di una coroutine in ProductsListVM
    suspend fun getAll() = ProductDao.getAllProducts()

    suspend fun getAllUnderPrice10() =
        ProductDao.getAllProducts().filter {
            it.price < 10.00
        }

}