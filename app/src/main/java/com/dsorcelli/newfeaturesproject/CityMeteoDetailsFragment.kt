package com.dsorcelli.newfeaturesproject

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
import com.bumptech.glide.Glide
import com.bumptech.glide.module.AppGlideModule
import com.dsorcelli.newfeaturesproject.databinding.FragmentProductDetailBinding
import com.dsorcelli.newfeaturesproject.viewmodels.CityMeteoDetailsVM
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

        val cityId = args.cityId
        val cityName = args.cityName
        val lastUpdate = args.lastUpdate


        viewModel.fetchMeteo(lastUpdate, cityName)
        viewModel.registerForMeteoUpdates(cityId).observe(viewLifecycleOwner) {
            it?.let {
                with(binding) {
                    ivProductImg.setImageResource(it.cityImg!!)
                    tvProductName?.text = it.cityName
                    tvWeatherGeneral?.text = it.weatherCondition //Perchè vuole il nullable (sotto non lo chiede)?
                    tvWeatherTemp?.text = "Avg temperature: ${it.weatherTemp}"
                    tvWeatherWind?.text ="Wind: ${ it.weatherWind}"
                    tvWeatherClouds?.text = "Clouds: ${it.weatherCloudsPerc}%"

                    val date = Date(it.lastUpdate)
                    val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
                    tvWeatherLastUpdate?.text = "Last updated: ${format.format(date)}"

                    if(it.weatherIcon!=null){
                        val imgUrl = "https://openweathermap.org/img/w/"+it.weatherIcon+".png"
                        Glide.with(context)
                            .load(imgUrl)
                            .into(ivWeatherImg)
                    }
                }
            } ?: run {
                Log.e(TAG, "Product not found")
            }
        }


        binding.btnProductDetailsBack.setOnClickListener {
            findNavController().navigateUp()
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
