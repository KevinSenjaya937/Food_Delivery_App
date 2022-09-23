package au.edu.curtin.fooddeliveryapp.fragments.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.Food
import au.edu.curtin.fooddeliveryapp.controller.FoodController
import au.edu.curtin.fooddeliveryapp.fragments.Browse.FoodListAdapter
import com.bumptech.glide.Glide

class HomeFragment(private val controller: FoodController
                  ) : Fragment(), FoodListAdapter.OnItemClickListener {

    private lateinit var adapter: HomeAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var dailyDeals: ArrayList<Food>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.dailyDeals = controller.getDailyDeals()

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        recyclerView = view.findViewById(R.id.dailyDealRecycler)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = HomeAdapter(dailyDeals)
        recyclerView.adapter = adapter
    }

    override fun onAddItemClick(position: Int, amount: Int) {
    }

    override fun onRemoveItemClick(position: Int, amount: Int) {
    }

    override fun onAddItemToOrderClick(position: Int, amount: Int) {
    }
}