package com.dsorcelli.newfeaturesproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dsorcelli.newfeaturesproject.databinding.FragmentProductsListBinding
import com.dsorcelli.newfeaturesproject.utils.show
import com.dsorcelli.newfeaturesproject.viewmodels.CityMeteoListVM


class CityMeteoListFragment : Fragment(), CityMeteoListAdapter.ProductListItemFace {

    //MVVM (Model-view-viewmodel)
    // L'activity/fragment (V) si occupa di aggiornare le viste.
    // Ogni vista deve avere una ViewModel che gestisca i dati e le operazioni sui dati della vista stessa
    // I dati vanno presi da un data layer interfacciandosi con la classe Repository

    //View Binding - per creare una corrispoindenza tra elementi della vista e modello dati si usa view binding (versione leggera del data binding).
    //L'oggetto Binding viene creato sulla classe di cui rappresenta i dati (ActivityMainBinding in questo caso, implicita)

    private var _binding: FragmentProductsListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!! //quando chiami binding, stai chiamando la sua versione nullable(?) ma facendo in modo che
    //se fosse null (!!) una chiamata su essa generi una NPE.
    //Le inizializzazioni e distruzioni saranno fatte su _binding, la chiamata degli elementi verrà fatta su binding per il controllo non null

    private val viewModel by viewModels<CityMeteoListVM>()

    // evita di usare le var se puoi, in questo caso productsListAdapter rimarrà sempre di tipo ProductsListAdapter,
    // quindi non avrebbe senso usare una lateinit var, puoi inizializzarlo già vuoto al momento della dichiarazione
    private val cityMeteoListAdapter: CityMeteoListAdapter = CityMeteoListAdapter(listener = this)

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
        _binding = FragmentProductsListBinding.inflate(inflater, container, false)
        //Si torna come view la root dell'elemento di binding

        binding.productsRv.adapter = cityMeteoListAdapter
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
            //Per evitare si pianti se it è null uso la forma nullable e let
            it?.let {
                cityMeteoListAdapter.cityList = it
                cityMeteoListAdapter.notifyDataSetChanged()
                binding.listLoadingProgressBar.show(false)
            }
        }
    }


    override fun onProductClick(cityId: Int, cityName:String) {
        findNavController().navigate(
            CityMeteoListFragmentDirections
                .actionProductsListFragmentToProductDetailsFragment(cityId, cityName)
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        private const val TAG = "ProductsListFragment"
    }

}