package au.edu.curtin.fooddeliveryapp.fragments.Cart

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.FoodOrder
import au.edu.curtin.fooddeliveryapp.fragments.Browse.BrowseAdapter
import org.w3c.dom.Text

class CartAdapter (private val data: ArrayList<FoodOrder>,
                   private val listener: OnItemClickListener
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
        var addButton: ImageButton
        var removeButton: ImageButton
        var removeFromCartButton: ImageButton

        init {
            foodImage = itemView.findViewById(R.id.foodImageView)
            restaurantName = itemView.findViewById(R.id.restaurantNameText)
            foodName = itemView.findViewById(R.id.foodNameText)
            totalPrice = itemView.findViewById(R.id.priceText)
            amountText = itemView.findViewById(R.id.amountTextNumber)
            addButton = itemView.findViewById(R.id.addBtn)
            removeButton = itemView.findViewById(R.id.removeBtn)
            removeFromCartButton = itemView.findViewById(R.id.removeFromCartBtn)

            addButton.setOnClickListener(this)
            removeButton.setOnClickListener(this)
            removeFromCartButton.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            if (view != null) {
                when (view.id) {
                    R.id.addBtn -> {

                    }
                    R.id.removeBtn -> {

                    }
                    R.id.removeFromCartBtn -> {

                    }

                }
            }
        }

        fun bind(foodOrder: FoodOrder) {
            this.foodOrder = foodOrder
            val total = foodOrder.totalPrice.toString()

            foodImage.setImageResource(foodOrder.foodPicture)
            restaurantName.text = foodOrder.restaurantName
            foodName.text = foodOrder.foodName
            totalPrice.text = "$${total}.00"
            amountText.text = foodOrder.amount.toString()
        }

    }

    interface OnItemClickListener {
        fun onAddItemClick(position: Int)
        fun onRemoveItemClick(position: Int)
        fun onRemoveItemFromCartClick(position: Int)
    }


}