package com.dsorcelli.newfeaturesproject.viewmodels

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.dsorcelli.newfeaturesproject.CityMeteoDetailsFragmentArgs
import com.dsorcelli.newfeaturesproject.R
import com.dsorcelli.newfeaturesproject.databinding.ActivityMainBinding.bind
import com.dsorcelli.newfeaturesproject.databinding.ActivityMainBinding.inflate
import com.dsorcelli.newfeaturesproject.databinding.FragmentProductDetailBinding
import com.dsorcelli.newfeaturesproject.databinding.FragmentSettingsBinding
import com.dsorcelli.newfeaturesproject.utils.Storage
import kotlin.system.measureNanoTime

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    // queste si chiamano delegate properties
    // per maggiori info vedi https://kotlinlang.org/docs/delegated-properties.html
    private val viewModel by viewModels<SettingsVM>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)


        when(viewModel.measureUnit) {
            Storage.TempMeasureUnit.DEF ->   binding.rbUnitDefault.isChecked = true
            Storage.TempMeasureUnit.EU ->   binding.rbUnitEuro.isChecked = true
            Storage.TempMeasureUnit.UK ->   binding.rbUnitBritish.isChecked = true
        }

        binding.rbUnitGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_unit_euro -> viewModel.saveMeasureUnit(Storage.TempMeasureUnit.EU)
                R.id.rb_unit_british -> viewModel.saveMeasureUnit(Storage.TempMeasureUnit.UK)
                R.id.rb_unit_default -> viewModel.saveMeasureUnit(Storage.TempMeasureUnit.DEF)
            }
        }
        return binding.root
    }


}