package au.edu.curtin.fooddeliveryapp

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var restaurantName : TextView
    var logoBtn : ImageButton
    init {
        restaurantName = itemView.findViewById<TextView>(R.id.restaurant_name)
        logoBtn = itemView.findViewById<ImageButton>(R.id.logo_btn)
    }
}