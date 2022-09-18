package au.edu.curtin.fooddeliveryapp.fragments.Browse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.Food

class FoodListAdapter (private val data: ArrayList<Food>,
                       private val listener: OnItemClickListener
                   ): RecyclerView.Adapter<FoodListAdapter.FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.food_item, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentItem = data[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return data.size
    }




    // FoodViewHolder //
    inner class FoodViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var food: Food
        var foodImage: ImageView
        var foodName: TextView
        var price: TextView
        var description: TextView
        var addButton: ImageButton
        var amountText: TextView
        var removeBtn: ImageButton
        var addToOrderBtn: ImageButton


        init {
            foodImage = itemView.findViewById(R.id.foodImageView)
            foodName = itemView.findViewById(R.id.foodNameText)
            price = itemView.findViewById(R.id.priceText)
            description = itemView.findViewById(R.id.descriptionText)
            addButton = itemView.findViewById(R.id.addBtn)
            amountText = itemView.findViewById(R.id.amountTextNumber)
            removeBtn = itemView.findViewById(R.id.removeBtn)
            addToOrderBtn = itemView.findViewById(R.id.addToOrderBtn)

            addButton.setOnClickListener(this)
            removeBtn.setOnClickListener(this)
            addToOrderBtn.setOnClickListener(this)
        }


        fun bind(food: Food) {
            this.food = food
            foodImage.setImageResource(food.picture)
            foodName.text = food.name
            price.text = "$${food.price}.00"
            description.text = food.description
            amountText.text = "0"
        }

        override fun onClick(view: View?) {
            val position: Int = absoluteAdapterPosition
            var amount = amountText.text.toString().toInt()

            if(view != null) {
                when (view.id) {
                    R.id.addBtn -> {
                        val new = amount + 1
                        listener.onAddItemClick(position, amount)
                        amountText.text = (new).toString()
                    }
                    R.id.removeBtn -> {
                        val new = amount - 1
                        listener.onRemoveItemClick(position, amount)
                        if (new >= 0) {
                            amountText.text = (new).toString()
                        }
                    }
                    R.id.addToOrderBtn -> {
                        listener.onAddItemToOrderClick(position, amount)
                    }
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onAddItemClick(position: Int, amount: Int)
        fun onRemoveItemClick(position: Int, amount: Int)
        fun onAddItemToOrderClick(position: Int, amount: Int)
    }
}