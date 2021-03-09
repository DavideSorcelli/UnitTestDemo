package com.dsorcelli.newfeaturesproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dsorcelli.newfeaturesproject.databinding.ProductsListItemBinding
import com.dsorcelli.newfeaturesproject.models.Product

class ProductsListAdapter(
    val activity: ProductsActivity,
    var productsList: List<Product> = emptyList()
) : RecyclerView.Adapter<ProductsListAdapter.ProductsViewHolder>() {

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

        val product = productsList[position]

        with(holder.binding) {
            productNumberTv.text = product.id.toString()
            productNameTv.text = product.name
            productPriceTv.text = product.price.toString()
            holder.itemView.setOnClickListener{
                val intent = Intent( activity , ProductDetailsActivity::class.java)
                val bundle = Bundle()
                bundle.putInt("product_id", product.id)
                intent.putExtras(bundle)
                startActivity(activity, intent, bundle)
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

    override fun getItemCount() = productsList.size



}