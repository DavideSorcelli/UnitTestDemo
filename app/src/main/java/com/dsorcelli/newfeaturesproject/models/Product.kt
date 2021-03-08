package com.dsorcelli.newfeaturesproject.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name="price")
    val price: Double
) {

    companion object {

        private const val CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

        fun randomProduct(): Product {
            return Product(
                id = (100..500).random(),
                name = (1..8)
                    .map { nextInt(0, CHAR_POOL.length) }
                    .map(CHAR_POOL::get)
                    .joinToString(""),
                price = Random.nextDouble(5.00, 50.00)
            )
        }

        fun staticProductsList() = listOf(
            Product(
                id = 1,
                name = "Product A",
                price = 19.99
            ),
            Product(
                id = 2,
                name = "Product B",
                price = 35.99
            ),
            Product(
                id = 3,
                name = "Product C",
                price = 9.99
            )
        )

    }
}
