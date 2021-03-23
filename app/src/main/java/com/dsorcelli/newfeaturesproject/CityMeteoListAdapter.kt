package com.dsorcelli.newfeaturesproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsorcelli.newfeaturesproject.databinding.ProductsListItemBinding
import com.dsorcelli.newfeaturesproject.models.CityMeteo

class CityMeteoListAdapter(
    var cityList: List<CityMeteo> = emptyList(),
    val listener: ProductListItemFace? = null
) : RecyclerView.Adapter<CityMeteoListAdapter.ProductsViewHolder>() {

    interface ProductListItemFace {
        fun onProductClick(cityId: Int, cityName: String)
    }

    // non hai bisogno di bindare le text view della vista sull'holder,
    // il view binding in questo caso semplifica le cose
    inner class ProductsViewHolder(val binding: ProductsListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val itemViewBinding = ProductsListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {

        val city = cityList[position]

        with(holder.binding) {
            productNumberTv.text = city.id.toString()
            productNameTv.text = city.name
            productIv.setImageResource(city.img!!)

            holder.itemView.setOnClickListener {
                listener?.onProductClick(city.id, city.name)
            }

        }


        // with() si usare per passare all'interno della lambda un parametro come this,
        // il codice scritto sopra è equivalente di:
        /*
            holder.binding.productNumberTv.text = product.id.toString()
            holder.binding.productNameTv.text = product.name
            holder.binding.productPriceTv.text = product.price.toString()
         */
    }

    override fun getItemCount() = cityList.size


}