package au.edu.curtin.fooddeliveryapp.fragments.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.Food
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class HomeAdapter(private val data: ArrayList<Food>): RecyclerView.Adapter<HomeAdapter.DealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.deal_item, parent, false)
        return DealViewHolder(view)
    }

    override fun onBindViewHolder(holder: DealViewHolder, position: Int) {
        val currentItem = data[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class DealViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private lateinit var food: Food

        var foodImage: de.hdodenhof.circleimageview.CircleImageView
        var restaurantName: TextView
        var foodName: TextView
        var foodPrice: TextView
        var foodDesc: TextView

        init {
            foodImage = itemView.findViewById(R.id.foodImageView)
            restaurantName = itemView.findViewById(R.id.restaurantNameText)
            foodName = itemView.findViewById(R.id.foodNameText)
            foodPrice = itemView.findViewById(R.id.priceText)
            foodDesc = itemView.findViewById(R.id.descriptionText)
        }

        fun bind(food: Food) {
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .load(food.picture)
                .into(foodImage)

            this.food = food
            restaurantName.text = food.restaurantName
            foodName.text = food.name
            foodPrice.text = food.price.toString()
            foodDesc.text = food.description
        }
    }


}