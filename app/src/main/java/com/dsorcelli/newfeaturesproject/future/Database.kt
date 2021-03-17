package com.dsorcelli.newfeaturesproject.future

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dsorcelli.newfeaturesproject.ProductApplication
import com.dsorcelli.newfeaturesproject.models.Product
import com.dsorcelli.newfeaturesproject.utils.vectorDrawableStringToIdMap
import kotlin.coroutines.coroutineContext

object Database {

    //Singleton object that wraps the real database (ShopDatabase)
    @Database(entities = [Product::class], version = 1, exportSchema = false)
    abstract class ShopDatabase : RoomDatabase() {
        //The database contains the abstract constructor for the DAOs
        abstract fun productDao(): ProductDatabaseDAO  //PERCHè UN METODO ASTRATTO?
    }

    private val productDatabaseCallback: RoomDatabase.Callback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            instance?.let {

                val productDao = it.productDao()

                //Delete content if exists
                //-----

                // Add sample elements.
                var prod =
                    Product(1, "London", 600.00, vectorDrawableStringToIdMap.get("img_london"))
                productDao.insert(prod)
                // Add sample elements.
                prod = Product(2, "Rome", 300.00, vectorDrawableStringToIdMap.get("img_rome"))
                productDao.insert(prod)
                // Add sample elements.
                prod = Product(3, "Paris", 400.00, vectorDrawableStringToIdMap.get("img_paris"))
                productDao.insert(prod)
                // Add sample elements.
                prod = Product(
                    4,
                    "Barcelona",
                    500.00,
                    vectorDrawableStringToIdMap.get("img_barcelona")
                )
                productDao.insert(prod)
                // Add sample elements.
                prod =
                    Product(5, "New York", 900.00, vectorDrawableStringToIdMap.get("img_new_york"))
                productDao.insert(prod)

            }

        }
    }


    /**
     * Single point of truth for persistence storage.
     * There is one single instance.
     * https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/
     */

    @SuppressLint("StaticFieldLeak")
    val instance = Room.databaseBuilder(
        ProductApplication.context,
        ShopDatabase::class.java,
        "shop-database.db")
        .addCallback(productDatabaseCallback)
        .build()


}
