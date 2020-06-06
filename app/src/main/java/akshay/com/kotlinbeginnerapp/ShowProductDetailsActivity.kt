package akshay.com.kotlinbeginnerapp

import akshay.com.kotlinbeginnerapp.adapter.ProductDetailsAdapter
import akshay.com.kotlinbeginnerapp.model.ProductDetails
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_show_product_details.*
import android.support.v7.widget.DividerItemDecoration



class ShowProductDetailsActivity : AppCompatActivity() {

    private var productList  = mutableListOf<ProductDetails>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_product_details)

    }

    private fun setProductDetailsData(){
        val productDetails = ProductDetails("TV","Rs.10000")
        val productDetailsFridge = ProductDetails("Fridge","Rs.20000")
        val productDetailsWM = ProductDetails("Washing Machine","Rs.10000")
        val productDetailsFan = ProductDetails("Fan","Rs.5000")
        val productDetailsMobilePhone = ProductDetails("Mobile Phone","Rs.12000")



        productList.add(productDetails)
        productList.add(productDetailsFridge)
        productList.add(productDetailsWM)
        productList.add(productDetailsFan)
        productList.add(productDetailsMobilePhone)


        val productDetailsAdapter = ProductDetailsAdapter(productList)
        rv_product_details.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        rv_product_details.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(
            rv_product_details.context,layoutManager.orientation
        )
       rv_product_details.addItemDecoration(dividerItemDecoration)
        rv_product_details.adapter = productDetailsAdapter
    }

    override fun onResume() {
        super.onResume()
        setProductDetailsData()
    }


}