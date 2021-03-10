package com.dsorcelli.newfeaturesproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dsorcelli.newfeaturesproject.databinding.ActivityMainBinding
import com.dsorcelli.newfeaturesproject.utils.show
import com.dsorcelli.newfeaturesproject.viewmodels.ProductsListVM

class ShopActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //VIEW BINDING (!= DATA BINDING)
        //Facendo inflating del layout tramite il metodo statico inflate si riceve l'elemento di binding rispetto a quella view.
        //Per ogni elemento xml viene generato un binding object con nome ispirato (ActvityMainBinding per activity_main.xml)
        //In questo modo gli elementi delle viste sono accessibile direttamente tramite getter/setters senza findViewById
        binding = ActivityMainBinding.inflate(layoutInflater)
        //Si setta come view la root dell'elemento del binding
        setContentView(binding.root)
    }





}