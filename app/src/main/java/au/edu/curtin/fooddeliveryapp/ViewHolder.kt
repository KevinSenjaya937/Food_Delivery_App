package au.edu.curtin.fooddeliveryapp

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var foodImage : ImageView
    var restaurantName : TextView
    var foodName : TextView
    var price : TextView
    init {
        foodImage = itemView.findViewById(R.id.foodImageView)
        restaurantName = itemView.findViewById(R.id.restaurantNameText)
        foodName = itemView.findViewById(R.id.foodNameText)
        price = itemView.findViewById(R.id.priceText)
    }
}