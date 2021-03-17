package com.dsorcelli.newfeaturesproject.repository

import androidx.annotation.WorkerThread
import com.dsorcelli.newfeaturesproject.future.Database
import com.dsorcelli.newfeaturesproject.models.Product


// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
object ProductRepository{

    //La classe Repository si occupa di fare da interfaccia tra la ViewModel e il data layer
    //In pratica fornisce dei metodi per prendere i dati dalla classe database invocandone i metodi primitivi
    //o per prendere i dati da api tramite chiamate ad endpoint: è un'astrazione del data layer vero e proprio.

    //Una funzione suspended può essere chiamata solo da un' altra funzione supsended.
    //In questo caso la getAll viene chiamata nell'ambito di una coroutine in ProductsListVM
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    //val allWords: Flow<Product>> = productDao.gtAll()


    // DAO
    private val mProductDao = Database.instance.productDao()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(product: Product) {
        mProductDao.insert(product)
    }

    suspend fun getAll() = mProductDao.getAll()

    suspend fun getProdById(productId: Int) = mProductDao.getById(productId)

}