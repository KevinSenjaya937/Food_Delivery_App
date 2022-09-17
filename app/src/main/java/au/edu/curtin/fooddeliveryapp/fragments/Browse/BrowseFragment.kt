package au.edu.curtin.fooddeliveryapp.fragments.Browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller.load()
    }

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
        val layoutManager = LinearLayoutManager(context)

        recyclerView = view.findViewById(R.id.restaurant_recycler)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = BrowseAdapter(restaurantList,this)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(context, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = restaurantList[position]
        clickedItem.name = "Clicked"
        adapter.notifyItemChanged(position)
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.scrollingFragment, FoodListFragment(foodController, clickedItem.name))
            commit()
        }
    }

}