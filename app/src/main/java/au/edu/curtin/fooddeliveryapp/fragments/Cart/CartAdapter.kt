package au.edu.curtin.fooddeliveryapp.fragments.Cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.FoodOrder


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
        var dateTimeText: TextView
        var addButton: ImageButton
        var removeButton: ImageButton
        var removeFromCartButton: ImageButton

        init {
            foodImage = itemView.findViewById(R.id.foodImageView)
            restaurantName = itemView.findViewById(R.id.restaurantNameText)
            foodName = itemView.findViewById(R.id.foodNameText)
            totalPrice = itemView.findViewById(R.id.priceText)
            amountText = itemView.findViewById(R.id.amountTextNumber)
            dateTimeText = itemView.findViewById(R.id.dateTimeText)
            addButton = itemView.findViewById(R.id.addBtn)
            removeButton = itemView.findViewById(R.id.removeBtn)
            removeFromCartButton = itemView.findViewById(R.id.removeFromCartBtn)

            addButton.setOnClickListener(this)
            removeButton.setOnClickListener(this)
            removeFromCartButton.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position: Int = absoluteAdapterPosition

            if (view != null) {
                when (view.id) {
                    R.id.addBtn -> {
                        listener.onAddItemClick(position)
                    }
                    R.id.removeBtn -> {
                        listener.onRemoveItemClick(position)
                    }
                    R.id.removeFromCartBtn -> {
                        listener.onRemoveItemFromCartClick(position)
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

            if (foodOrder.userID == 0) {
                dateTimeText.isVisible = false
            }
            else {
                dateTimeText.isVisible = true
                dateTimeText.text = foodOrder.datetime
            }
        }

    }

    interface OnItemClickListener {
        fun onAddItemClick(position: Int)
        fun onRemoveItemClick(position: Int)
        fun onRemoveItemFromCartClick(position: Int)
        fun onCheckOutClick()
    }


}