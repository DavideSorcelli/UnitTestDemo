package com.dsorcelli.newfeaturesproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.dsorcelli.newfeaturesproject.databinding.ProductsListItemBinding
import com.dsorcelli.newfeaturesproject.models.Product

class ProductsListAdapter: RecyclerView.Adapter<ProductsListAdapter.ProductsViewHolder>()
{
    var productsList = listOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val itemViewBinding = ProductsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(itemViewBinding)

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


     inner class ProductsViewHolder(viewBinding: ProductsListItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        var productNumberTv: TextView = viewBinding.productNumberTv
        var productNameTv : TextView = viewBinding.productNameTv
        var productPriceTv: TextView = viewBinding.productPriceTv
    }

}