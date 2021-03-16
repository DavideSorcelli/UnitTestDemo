package com.dsorcelli.newfeaturesproject.database

import com.dsorcelli.newfeaturesproject.models.Product
import kotlinx.coroutines.delay

//object segnala una classe singleton -> quando verrà richiamata, se non esiste viene creata altrimenti si usa smepre la stessa sitanza
object ProductDao {
    //Il database fornisce dei metodi primitivi per esporre i dati -> questi non verranno mai chiamati dal VM ma da un'interfaccia,
    //ovvero la classe Repository.ho


    /*
    Suspending functions are at the center of everything coroutines. A suspending function is simply a function that can be paused and resumed at a later time.
     They can execute a long running operation and wait for it to complete without blocking.
     The syntax of a suspending function is similar to that of a regular function except for the addition of the suspend keyword.
     It can take a parameter and have a return type. However, suspending functions can only be invoked by another suspending function or within a coroutine.
     */

    //Sostanzialmente suspend è presente se  chiamata da altri metodi suspended o da una coroutine.
    // In questo caso getAllProducts viene chiamata da getAll in Repository (suspended) a sua volta chiamata da una coroutine nel ViewModel
    /*suspend fun getAllProducts(): List<Product> {
//        delay((3000L..5000).random())
        delay(500)
        return Product.staticProductsList()
//        val productsList = mutableListOf<Product>()
//        repeat(5) {
//            productsList.add(Product.randomProduct())
//        }
//        return productsList
    }

    fun getProductById(productId : Int) =
        Product.staticProductsList().firstOrNull { it.id == productId }

*/}