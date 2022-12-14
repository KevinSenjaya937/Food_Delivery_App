package au.edu.curtin.fooddeliveryapp.fragments.Browse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.Restaurant
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class BrowseAdapter (private val data : ArrayList<Restaurant>,
                     private val listener: OnItemClickListener
                     ): RecyclerView.Adapter<BrowseAdapter.RestaurantViewHolder>() {

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




    // RestaurantViewHolder //
    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var restaurant: Restaurant
        private var logoImage: ImageView
        var restaurantName: TextView
        private var foodType: TextView
        private var location: TextView

        init {
            logoImage = itemView.findViewById(R.id.logoImageView)
            restaurantName = itemView.findViewById(R.id.restaurantNameText)
            foodType = itemView.findViewById(R.id.restaurantTypeText)
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
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .load(restaurant.logo)
                .into(logoImage)


            this.restaurant = restaurant
            restaurantName.text = restaurant.name
            foodType.text = restaurant.foodType
            location.text = restaurant.location
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}
