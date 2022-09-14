package au.edu.curtin.fooddeliveryapp.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.Restaurant

class BrowseAdapter (private val data : ArrayList<Restaurant>,
                     private val listener: OnItemClickListener
                     ) : RecyclerView.Adapter<BrowseAdapter.RestaurantViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.restaurant_item, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentItem = data[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return data.size
    }



    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var restaurant: Restaurant? = null
        var foodImage: ImageView
        var restaurantName: TextView
        var foodType: TextView
        var location: TextView

        init {
            foodImage = itemView.findViewById(R.id.foodImageView)
            restaurantName = itemView.findViewById(R.id.restaurantNameText)
            foodType = itemView.findViewById(R.id.foodTypeText)
            location = itemView.findViewById(R.id.locationText)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position : Int = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

        fun bind(restaurant: Restaurant) {
            this.restaurant = restaurant
            foodImage.setImageResource(restaurant.logo)
            restaurantName.text = restaurant.name
            foodType.text = restaurant.foodType
            location.text = restaurant.location
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}
