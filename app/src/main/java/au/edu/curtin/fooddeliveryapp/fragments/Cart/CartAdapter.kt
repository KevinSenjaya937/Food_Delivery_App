package au.edu.curtin.fooddeliveryapp.fragments.Cart

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.FoodOrder
import au.edu.curtin.fooddeliveryapp.fragments.Browse.BrowseAdapter
import org.w3c.dom.Text

class CartAdapter (private val data: ArrayList<FoodOrder>,
                   private val listener: CartAdapter.OnItemClickListener
                   ): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = data[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var foodOrder: FoodOrder
        var foodImage: de.hdodenhof.circleimageview.CircleImageView
        var restaurantName: TextView
        var foodName: TextView
        var totalPrice: TextView
        var amountText: TextView

        init {
            foodImage = itemView.findViewById(R.id.foodImageView)
            restaurantName = itemView.findViewById(R.id.restaurantNameText)
            foodName = itemView.findViewById(R.id.foodNameText)
            totalPrice = itemView.findViewById(R.id.priceText)
            amountText = itemView.findViewById(R.id.amountTextNumber)
        }

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }

        fun bind(foodOrder: FoodOrder) {
            this.foodOrder = foodOrder
            // Stopped Here
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


}