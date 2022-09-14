package au.edu.curtin.fooddeliveryapp.fragments

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.Restaurant

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var restaurant : Restaurant? = null
    var foodImage : ImageView
    var restaurantName : TextView
    var foodType : TextView
    var location : TextView

    init {
        foodImage = itemView.findViewById(R.id.foodImageView)
        restaurantName = itemView.findViewById(R.id.restaurantNameText)
        foodType = itemView.findViewById(R.id.foodTypeText)
        location = itemView.findViewById(R.id.locationText)
    }

    fun bind(restaurant : Restaurant) {
        this.restaurant = restaurant
        foodImage.setImageResource(restaurant.logo)
        restaurantName.text = restaurant.name
        foodType.text = restaurant.foodType
        location.text = restaurant.location
    }
}