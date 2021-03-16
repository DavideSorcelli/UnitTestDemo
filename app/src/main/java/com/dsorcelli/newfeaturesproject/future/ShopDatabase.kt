package com.dsorcelli.newfeaturesproject.future

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dsorcelli.newfeaturesproject.models.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import com.dsorcelli.newfeaturesproject.utils.vectorDrawableStringToIdMap

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ShopDatabase : RoomDatabase() {

    //Database che fa riferimento alle varie Entities ed espone i DAO sottoforma di metodi getter astratti per le varie tabelle

    abstract fun productDatabaseDAO(): ProductDatabaseDAO


    private class ProductDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var productDao = database.productDatabaseDAO()

                    //Delete content if exists
                    //-----

                    // Add sample elements.
                    var prod = Product(1,"London",600.00,vectorDrawableStringToIdMap.get("img_london"))
                    productDao.insert(prod)
                    // Add sample elements.
                    prod = Product(2,"Rome",300.00,vectorDrawableStringToIdMap.get("img_rome"))
                    productDao.insert(prod)
                    // Add sample elements.
                    prod = Product(3,"Paris",400.00,vectorDrawableStringToIdMap.get("img_paris"))
                    productDao.insert(prod)
                    // Add sample elements.
                    prod = Product(4,"Barcelona",500.00, vectorDrawableStringToIdMap.get("img_barcelona"))
                    productDao.insert(prod)
                    // Add sample elements.
                    prod = Product(5,"New York",900.00,vectorDrawableStringToIdMap.get("img_new_york"))
                    productDao.insert(prod)

                }
            }
        }
    }

        //Singleton: created just the first time
    companion object {


            @Volatile
            private var INSTANCE: ShopDatabase? = null

            fun getDatabase(context: Context, scope: CoroutineScope): ShopDatabase {
                // if the INSTANCE is not null, then return it,
                // if it is, then create the database
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ShopDatabase::class.java,
                        "shop_database"
                    )
                        .addCallback(ProductDatabaseCallback(scope))
                        .build()
                    INSTANCE = instance
                    // return instance
                    instance
                }
            }
        }
    }
