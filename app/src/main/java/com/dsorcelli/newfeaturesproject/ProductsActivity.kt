package com.dsorcelli.newfeaturesproject

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dsorcelli.newfeaturesproject.databinding.ActivityMainBinding
import com.dsorcelli.newfeaturesproject.viewmodels.ProductsListVM

class ProductsActivity : AppCompatActivity() {

    //MVVM (Model-view-viewmodel)
    // L'activity/fragment (V) si occupa di aggiornare le viste.
    // Ogni vista deve avere una ViewModel che gestisca i dati e le operazioni sui dati della vista stessa
    // I dati vanno presi da un data layer interfacciandosi con la classe Repository

    //View Binding - per creare una corrispoindenza tra elementi della vista e modello dati si usa view binding (versione leggera del data binding).
    //L'oggetto Binding viene creato sulla classe di cui rappresenta i dati (ActivityMainBinding in questo caso, implicita)
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<ProductsListVM>()

    // evita di usare le var se puoi, in questo caso productsListAdapter rimarrà sempre di tipo ProductsListAdapter,
    // quindi non avrebbe senso usare una lateinit var, puoi inizializzarlo già vuoto al momento della dichiarazione
    private val productsListAdapter: ProductsListAdapter = ProductsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //VIEW BINDING (!= DATA BINDING)
        //Facendo inflating del layout tramite il metodo statico inflate si riceve l'elemento di binding rispetto a quella view.
        //Per ogni elemento xml viene generato un binding object con nome ispirato (ActvityMainBinding per activity_main.xml)
        //In questo modo gli elementi delle viste sono accessibile direttamente tramite getter/setters senza findViewById
        binding = ActivityMainBinding.inflate(layoutInflater)
        //Si setta come view la root dell'elemento del binding
        setContentView(binding.root)

        binding.productsRv.adapter = productsListAdapter
        registerObservers()
    }

    private fun registerObservers() {
        //i dati sono presi dalla view model -> in questo caso la lista di prodotti è istanziata non in ProductsActivity
        //ma in ProductsListVM e quindi va presa da lì.
        //In questo caso è una LiveData che contiene una lista di prodotti e qui
        // viene implementato un observer che aggiorna le viste automaticamente con i nuovi dati
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