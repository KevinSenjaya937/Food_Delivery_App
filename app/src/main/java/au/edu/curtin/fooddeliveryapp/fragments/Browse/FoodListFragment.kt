package au.edu.curtin.fooddeliveryapp.fragments.Browse

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.fooddeliveryapp.MainActivity
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.Food
import au.edu.curtin.fooddeliveryapp.classes.FoodOrder
import au.edu.curtin.fooddeliveryapp.controller.FoodController
import au.edu.curtin.fooddeliveryapp.fragments.Browse.FoodListAdapter


class FoodListFragment(private val controller: FoodController, private val restaurantName: String): Fragment(), FoodListAdapter.OnItemClickListener {

    private lateinit var adapter: FoodListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodList: ArrayList<Food>
    private var orderAmount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller.load()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.foodList = controller.getList()
        val layoutManager = LinearLayoutManager(context)

        recyclerView = view.findViewById(R.id.food_recycler)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = FoodListAdapter(foodList, this)
        recyclerView.adapter = adapter
    }

    override fun onAddItemClick(position: Int, amount: Int) {
        Log.d("EMPTY", "Add")
    }

    override fun onRemoveItemClick(position: Int, amount: Int) {
        Log.d("EMPTY", "Remove")
    }

    override fun onAddItemToOrderClick(position: Int, amount: Int) {
        Log.d("EMPTY", "Add to OrderList")
        val food = foodList[position]

        if (amount == 0) {
            Toast.makeText(context, "Amount cannot be 0", Toast.LENGTH_SHORT).show()
        } else {
            controller.createOrder()
            controller.addFoodOrder(food, amount)
            (activity as MainActivity).badgeSetup(R.id.nav_cart, orderAmount++)
        }
    }
}