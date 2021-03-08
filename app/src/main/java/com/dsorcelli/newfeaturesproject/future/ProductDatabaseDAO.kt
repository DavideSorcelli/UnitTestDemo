package com.dsorcelli.newfeaturesproject.future

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dsorcelli.newfeaturesproject.models.Product

@Dao
interface ProductDatabaseDAO {
//DAO: Data Access Object -> interf accia per operare sul DB che rappresenta

    @Insert
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Query("SELECT * FROM products WHERE id= :id")
    fun get(id: Int): Product?

    @Query("SELECT * FROM products")
    fun getAll(id: Int): Product?

    @Query("DELETE FROM products")
    fun clear()

}