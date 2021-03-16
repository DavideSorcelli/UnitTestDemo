package com.dsorcelli.newfeaturesproject

import android.app.Application
import com.dsorcelli.newfeaturesproject.future.ShopDatabase
import com.dsorcelli.newfeaturesproject.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope

class ProductApplication(scope: CoroutineScope) : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { ShopDatabase.getDatabase(this, scope)}
    val repository by lazy { ProductRepository(database.productDatabaseDAO()) }
}