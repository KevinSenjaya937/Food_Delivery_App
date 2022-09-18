package au.edu.curtin.fooddeliveryapp.fragments.Cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.FoodOrder
import au.edu.curtin.fooddeliveryapp.classes.Order
import au.edu.curtin.fooddeliveryapp.controller.FoodController

class CartByRestaurantAdapter (private val data: ArrayList<Order>,
                               private val controller: FoodController,
                               private val listener: OnItemClickListener
): RecyclerView.Adapter<CartByRestaurantAdapter.CartByRestaurantViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartByRestaurantViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.cart_by_restaurant_item, parent, false)
        return CartByRestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartByRestaurantViewHolder, position: Int) {
        val currentRestaurant = data[position]
        holder.bind(currentRestaurant)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class CartByRestaurantViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var order: Order
        var restaurantLogo: de.hdodenhof.circleimageview.CircleImageView
        var restaurantName: TextView
        var amountOfItems: TextView
        var totalOrderPrice: TextView

        init {
            restaurantLogo = itemView.findViewById(R.id.logoImageView)
            restaurantName = itemView.findViewById(R.id.restaurantNameText)
            amountOfItems = itemView.findViewById(R.id.amountOfItemsText)
            totalOrderPrice = itemView.findViewById(R.id.priceText)
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }

        fun bind(order: Order) {
            restaurantLogo.setImageResource(order.restaurantLogo)
            restaurantName.text = order.restaurantName
            amountOfItems.text = controller.getOrderItemCount(order.restaurantID).toString()
            totalOrderPrice.text = controller.calculateTotalOrderCost(order.restaurantID).toString()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


}