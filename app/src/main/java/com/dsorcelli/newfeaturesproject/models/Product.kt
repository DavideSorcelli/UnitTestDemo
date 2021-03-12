package com.dsorcelli.newfeaturesproject.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dsorcelli.newfeaturesproject.R
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name="price")
    val price: Double,
    val img: Int = -1 //risorsa R.drawable (rappresentato da intero)
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
                price = 19.99,
                img = R.drawable.ic_beer
            ),
            Product(
                id = 2,
                name = "Product B",
                price = 35.99,
                img = R.drawable.ic_ice_cream_cone
            ),
            Product(
                id = 3,
                name = "Product C",
                price = 9.99,
                img = R.drawable.ic_lemonade
            ),
            Product(
                id = 4,
                name = "Product D",
                price = 2.50,
                img = R.drawable.ic_popsicle_stick
            ),
            Product(
                id = 5,
                name = "Product E",
                price = 6.78,
                img = R.drawable.ic_watermelon
            )
        )

    }
}
