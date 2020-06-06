package akshay.com.kotlinbeginnerapp.adapter

import akshay.com.kotlinbeginnerapp.R
import akshay.com.kotlinbeginnerapp.model.ProductDetails
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ProductDetailsAdapter
    (private var productDetails: List<ProductDetails>) : RecyclerView.Adapter<ProductDetailsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.item_product_details, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val product = productDetails[position]
        holder.tvProductName.text = product.productName
        holder.tvProductPrice.text = product.productPrice
    }


    override fun getItemCount(): Int {
        return productDetails.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvProductName: TextView = itemView.findViewById<View>(R.id.tv_product_name) as TextView
        var tvProductPrice: TextView = itemView.findViewById<View>(R.id.tv_product_price) as TextView

    }
}
