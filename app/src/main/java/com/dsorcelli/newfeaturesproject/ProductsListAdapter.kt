package com.dsorcelli.newfeaturesproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dsorcelli.newfeaturesproject.models.Product

class ProductsListAdapter: RecyclerView.Adapter<ProductsListAdapter.ProductsViewHolder>()
{
    var productsList = listOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.products_list_item, parent, false)
        return ProductsViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = productsList[position]
        holder.productNumberTv.text = product.id.toString()
        holder.productNameTv.text = product.name
        holder.productPriceTv.text = product.price.toString()
    }


     inner class ProductsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var productNumberTv: TextView = view.findViewById(R.id.product_number_tv)
        var productNameTv : TextView = view.findViewById(R.id.product_name_tv)
        var productPriceTv: TextView = view.findViewById(R.id.product_price_tv)
    }

}