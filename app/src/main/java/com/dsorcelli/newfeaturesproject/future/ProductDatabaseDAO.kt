package com.dsorcelli.newfeaturesproject.future

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dsorcelli.newfeaturesproject.models.Product

@Dao
interface ProductDatabaseDAO {
//DAO: Data Access Object -> interfaccia per operare sul DB che rappresenta

    @Insert
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Query("SELECT * FROM products WHERE id= :id")
    fun getById(id: Int): Product?

    @Query("SELECT * FROM products")
    fun getAll() : List<Product>?

    @Query("DELETE FROM products")
    fun clear()

}