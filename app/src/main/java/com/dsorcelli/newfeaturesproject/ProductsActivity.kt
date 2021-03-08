package com.dsorcelli.newfeaturesproject

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import com.dsorcelli.newfeaturesproject.databinding.ActivityMainBinding
import com.dsorcelli.newfeaturesproject.models.Product
import com.dsorcelli.newfeaturesproject.viewmodels.ProductsListVM
import java.util.stream.Collectors.toList

class ProductsActivity : AppCompatActivity() {


    //MVVC
    // L'activity/fragment (V) si occupa di aggiornare le viste.
    // Ogni vista deve avere una ViewModel che gestisca i dati e le operaizoni sui dati della vista stessa
    // I dati vanno presi da un data layer interfacciandosi con la classe Repository

    //View Binding - per creare una corrispoindenza tra elementi della vista e modello dati si usa view binding (versione leggera del data binding).
    //L'oggetto Binding viene creato sulla classe di cui rappresenta i dati (ActivityMainBinding in questo caso, implicita)
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<ProductsListVM>()
    private lateinit var productsListAdapter : ProductsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //VIEW BINDING (!= DATA BINDING)
        //Facendo inflating del layout tramite il metodo statico inflate si riceve l'elemento di binding rispetto a quella view.
        //Per ogni elemento xml viene generato un binding object con nome ispirato (ActvityMainBinding per activity_main.xml)
        //In questo modo gli elementi delle viste sono accessibile direttamente tramite getter/setters senza findViewById
        binding = ActivityMainBinding.inflate(layoutInflater)
        //Si setta come view la root dell'elemento del binding
        setContentView(binding.root)

        productsListAdapter = ProductsListAdapter()
        binding.productsRv.adapter = productsListAdapter
        registerObservers()
    }

    private fun registerObservers() {
        //i dati sono presi dalla view model -> in questo caso la lista di prodotti è istanziata non in ProductsActivity
        //ma in ProductsListVM e quindi va presa da lì.
        //In questo caso è una lista di LiveData e qui viene implementato un observer che aggiorna le viste automaticamente con i nuovi dati
        //non appena ursti ultimi (liveData) cambiano.
        viewModel.productsList.observe(this) {
            Log.d(TAG, "Received products update: $it")
            productsListAdapter.productsList = it
            productsListAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        private const val TAG = "ProductsActivity"
    }

}