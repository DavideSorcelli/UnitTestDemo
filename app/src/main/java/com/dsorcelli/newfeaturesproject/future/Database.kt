package com.dsorcelli.newfeaturesproject.future

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dsorcelli.newfeaturesproject.CityMeteoApplication
import com.dsorcelli.newfeaturesproject.models.CityMeteo
import com.dsorcelli.newfeaturesproject.utils.vectorDrawableStringToIdMap
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object Database {

    //Singleton object that wraps the real database (ShopDatabase)
    @Database(entities = [CityMeteo::class], version = 1, exportSchema = false)
    abstract class ShopDatabase : RoomDatabase() {
        //The database contains the abstract constructor for the DAOs
        abstract fun productDao(): CityMeteoDatabaseDAO  //PERCHè UN METODO ASTRATTO?
    }

    private val productDatabaseCallback: RoomDatabase.Callback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val productDao = instance.productDao()

            //Delete content if exists
            //-----

            // Add sample elements.
            GlobalScope.launch {

                val productList = listOf(
                    CityMeteo(1, "London",  vectorDrawableStringToIdMap["img_london"]),
                    CityMeteo(2, "Rome",  vectorDrawableStringToIdMap["img_rome"]),
                    CityMeteo(3, "Paris", vectorDrawableStringToIdMap["img_paris"]),
                    CityMeteo(4, "Barcelona", vectorDrawableStringToIdMap["img_barcelona"]),
                    CityMeteo(5, "New York", vectorDrawableStringToIdMap["img_new_york"])
                )

                productList.forEach { productDao.insert(it)}
            }
        }


    }


    /**
     * Single point of truth for persistence storage.
     * There is one single instance.
     * https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/
     */
    val instance = Room.databaseBuilder(
        CityMeteoApplication.context,
        ShopDatabase::class.java,
        "shop-database.db"
    )
        .addCallback(productDatabaseCallback)
        .build()

}
