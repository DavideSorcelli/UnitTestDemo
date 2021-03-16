package com.dsorcelli.newfeaturesproject

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dsorcelli.newfeaturesproject.databinding.FragmentProductMeteoBinding
import com.dsorcelli.newfeaturesproject.viewmodels.ProductMeteoVM

class ProductMeteoFragment : Fragment(){

    //Sfrutta i dati gestiti da ProductMeteoVM per tenere aggiornata la vista e gestirne le interazioni

    private val viewModel by viewModels<ProductMeteoVM>()
    private var _binding: FragmentProductMeteoBinding? = null
    private val binding get() = _binding!!


}