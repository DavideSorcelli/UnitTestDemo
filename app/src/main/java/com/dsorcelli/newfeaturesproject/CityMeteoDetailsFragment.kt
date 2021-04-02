package com.dsorcelli.newfeaturesproject

import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.bumptech.glide.module.AppGlideModule
import com.dsorcelli.newfeaturesproject.databinding.FragmentProductDetailBinding
import com.dsorcelli.newfeaturesproject.viewmodels.CityMeteoDetailsVM
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*

class CityMeteoDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    // queste si chiamano delegate properties
    // per maggiori info vedi https://kotlinlang.org/docs/delegated-properties.html
    private val viewModel by viewModels<CityMeteoDetailsVM>()
    private val args: CityMeteoDetailsFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        val cityName = args.cityName

        viewModel.isLoading.observe(viewLifecycleOwner)
        {
            it?.let {
                if(it)
                    binding.cityMeteoDetailsProgressBar?.visibility = View.VISIBLE
                else
                    binding.cityMeteoDetailsProgressBar?.visibility = View.GONE
            }
        }
        viewModel.fetchMeteo(cityName)

        viewModel.cityMeteoLive.observe(viewLifecycleOwner) {

            it?.let {
                with(binding) {

                    ivProductImg.setImageResource(it.cityImg!!)
                    // TODO: ricordati che quando aggiungi/rimuovi delle view da un layout devi aggiornare anche le sue varianti (landscape, sw-600, ecc..)
                    tvWeatherGeneral.text = it.weatherCondition //Perchè vuole il nullable (sotto non lo chiede)?
                    // TODO: crea le stringhe in strings.xml
                    tvWeatherTemp?.text = "Avg temperature: ${it.weatherTemp}"
                    tvWeatherWind?.text ="Wind: ${ it.weatherWind}"
                    tvWeatherClouds?.text = "Clouds: ${it.weatherCloudsPerc}%"

                    // TODO: fai un metodo nelle utility per convertire le date (o un'extension function di kotlin)
                    val date = Date(it.lastUpdate)
                    val format = SimpleDateFormat("yyyy.MM.dd HH:mm") // TODO: manca il locale
                    tvWeatherLastUpdate?.text = "Last updated: ${format.format(date)}"

                    if(it.weatherIcon!=null){
                        // TODO: crea sempre delle costanti
                        val imgUrl = "https://openweathermap.org/img/w/"+it.weatherIcon+".png"
                        Glide.with(context)
                            .load(imgUrl)
                            .into(ivWeatherImg)
                    }

                }
            } ?: run {

                findNavController().navigate(CityMeteoDetailsFragmentDirections
                    .actionProductDetailsFragmentToBottomErrorDialog(
                        titleResId = R.string.error,
                        bodyResId = R.string.error_no_internet,
                        popUpToFragment = R.id.productsListFragment
                    ))
//                // TODO: show error dialog with navigateUp action
//                val error_btm_sheet = layoutInflater.inflate(R.layout.error_bottom_sheet, null)
//                val dialog = context?.let { it1 -> BottomSheetDialog(it1) }
//                dialog?.setContentView(error_btm_sheet)
//                error_btm_sheet.setOnClickListener {
//                    dialog?.dismiss()
//                }
//                dialog?.show()
            }

        }


        binding.btnProductDetailsBack.setOnClickListener {
            findNavController().navigateUp()
        }



        binding.meteoDetailsSwipe.setOnRefreshListener {
            viewModel.fetchMeteo(cityName)
            binding.meteoDetailsSwipe.isRefreshing = false
        }

        //Si setta come view la root dell'elemento del binding
        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "ProductDetailsFragment"
    }

}
