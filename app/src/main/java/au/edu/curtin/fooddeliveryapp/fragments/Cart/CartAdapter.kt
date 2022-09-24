package au.edu.curtin.fooddeliveryapp.fragments.Cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.FoodOrder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class CartAdapter (private val data: List<FoodOrder>,
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
        private var foodImage: de.hdodenhof.circleimageview.CircleImageView
        var restaurantName: TextView
        private var foodName: TextView
        private var totalPrice: TextView
        private var amountText: TextView
        private var amountTextTop: TextView
        private var dateTimeText: TextView
        private var addButton: ImageButton
        private var removeButton: ImageButton
        private var removeFromCartButton: ImageButton

        init {
            foodImage = itemView.findViewById(R.id.foodImageView)
            restaurantName = itemView.findViewById(R.id.restaurantNameText)
            foodName = itemView.findViewById(R.id.foodNameText)
            totalPrice = itemView.findViewById(R.id.priceText)
            amountText = itemView.findViewById(R.id.amountTextNumber)
            amountTextTop = itemView.findViewById(R.id.amountText)
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

        @SuppressLint("SetTextI18n")
        fun bind(foodOrder: FoodOrder) {

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .load(foodOrder.foodPicture)
                .into(foodImage)

            this.foodOrder = foodOrder
            val total = foodOrder.totalPrice.toString()


            restaurantName.text = foodOrder.restaurantName
            foodName.text = foodOrder.foodName
            totalPrice.text = "Price: $${total}.00"
            amountText.text = foodOrder.amount.toString()

            if (foodOrder.userID == 0) {
                dateTimeText.isVisible = false
                addButton.isVisible = true
                removeButton.isVisible = true
                removeFromCartButton.isVisible = true
                amountTextTop.isVisible = false
                amountText.isVisible = true
            }
            else {
                dateTimeText.isVisible = true
                dateTimeText.text = foodOrder.datetime
                addButton.isVisible = false
                removeButton.isVisible = false
                removeFromCartButton.isVisible = false
                amountTextTop.isVisible = true
                amountTextTop.text = "Amount: ${foodOrder.amount} . "
                amountText.isVisible = false
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