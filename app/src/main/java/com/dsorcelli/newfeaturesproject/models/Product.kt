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
    val img: Int? = -1 //risorsa R.drawable (rappresentato da intero)
)
