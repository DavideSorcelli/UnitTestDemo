package com.dsorcelli.newfeaturesproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.dsorcelli.newfeaturesproject.databinding.FragmentProductsListBinding
import com.dsorcelli.newfeaturesproject.utils.show
import com.dsorcelli.newfeaturesproject.viewmodels.ProductsListVM


class ProductsListFragment : Fragment(), ProductsListAdapter.ProductListItemFace {

    //MVVM (Model-view-viewmodel)
    // L'activity/fragment (V) si occupa di aggiornare le viste.
    // Ogni vista deve avere una ViewModel che gestisca i dati e le operazioni sui dati della vista stessa
    // I dati vanno presi da un data layer interfacciandosi con la classe Repository

    //View Binding - per creare una corrispoindenza tra elementi della vista e modello dati si usa view binding (versione leggera del data binding).
    //L'oggetto Binding viene creato sulla classe di cui rappresenta i dati (ActivityMainBinding in questo caso, implicita)
    private lateinit var binding: FragmentProductsListBinding
    private val viewModel by viewModels<ProductsListVM>()

    // evita di usare le var se puoi, in questo caso productsListAdapter rimarrà sempre di tipo ProductsListAdapter,
    // quindi non avrebbe senso usare una lateinit var, puoi inizializzarlo già vuoto al momento della dichiarazione
    private val productsListAdapter: ProductsListAdapter = ProductsListAdapter(listener = this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //VIEW BINDING (!= DATA BINDING)
        //Facendo inflating del layout tramite il metodo statico inflate si riceve l'elemento di binding rispetto a quella view.
        //Per ogni elemento xml viene generato un binding object con nome ispirato (ActvityMainBinding per activity_main.xml)
        //In questo modo gli elementi delle viste sono accessibile direttamente tramite getter/setters senza findViewById
        binding = FragmentProductsListBinding.inflate(inflater, container, false)
        //Si torna come view la root dell'elemento di binding

        binding.productsRv.adapter = productsListAdapter
        registerObservers()

        return binding.root
    }

    private fun registerObservers() {
        //i dati sono presi dalla view model -> in questo caso la lista di prodotti è istanziata non in ProductsActivity
        //ma in ProductsListVM e quindi va presa da lì.
        //In questo caso è una LiveData che contiene una lista di prodotti e qui
        // viene implementato un observer che aggiorna le viste automaticamente con i nuovi dati
        //non appena ursti ultimi (liveData) cambiano.
        viewModel.productsList.observe(viewLifecycleOwner) {
            Log.d(TAG, "Received products update: $it")
            productsListAdapter.productsList = it
            productsListAdapter.notifyDataSetChanged()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.listLoadingProgressBar.show(it)
        }
    }


    override fun onProductClick(productId: Int) {
        binding.root.findNavController().navigate(
            ProductsListFragmentDirections
                .actionProductsListFragmentToProductDetailsFragment(productId)
        )

    }

    companion object {
        private const val TAG = "ProductsListFragment"
    }

}