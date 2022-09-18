package au.edu.curtin.fooddeliveryapp.fragments.Cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.Order
import au.edu.curtin.fooddeliveryapp.controller.FoodController

class CartByRestaurantFragment(private val controller: FoodController) : Fragment(), CartByRestaurantAdapter.OnItemClickListener {

    private lateinit var adapter: CartByRestaurantAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var orderList: ArrayList<Order>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart_by_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.orderList = controller.getOrderList()

        val layoutManager = LinearLayoutManager(context)

        recyclerView = view.findViewById(R.id.cart_by_restaurant_recycler)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = CartByRestaurantAdapter(orderList, controller,this)
        recyclerView.adapter = adapter

    }
    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }

}