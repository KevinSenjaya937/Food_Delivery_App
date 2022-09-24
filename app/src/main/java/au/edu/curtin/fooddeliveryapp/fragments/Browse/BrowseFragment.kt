package au.edu.curtin.fooddeliveryapp.fragments.Browse

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.Restaurant
import au.edu.curtin.fooddeliveryapp.controller.FoodController
import au.edu.curtin.fooddeliveryapp.controller.RestaurantController


class BrowseFragment(private val controller : RestaurantController,
                     private val foodController: FoodController,
                     ) : Fragment(), BrowseAdapter.OnItemClickListener {

    private lateinit var adapter: BrowseAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var restaurantList: ArrayList<Restaurant>
    private var layoutManager = LinearLayoutManager(context)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurants, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.restaurantList = controller.getList()

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            this.layoutManager = LinearLayoutManager(context)
        } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        recyclerView = view.findViewById(R.id.restaurant_recycler)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = BrowseAdapter(restaurantList,this)
        recyclerView.adapter = adapter
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            this.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onItemClick(position: Int) {
        val clickedItem = restaurantList[position]

        parentFragmentManager.beginTransaction().apply {
            replace(R.id.scrollingFragment, FoodListFragment(foodController, clickedItem))
            commit()
        }
    }



}