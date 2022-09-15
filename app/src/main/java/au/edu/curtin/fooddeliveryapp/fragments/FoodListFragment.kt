package au.edu.curtin.fooddeliveryapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.Food
import au.edu.curtin.fooddeliveryapp.controller.FoodController


class FoodListFragment(private val controller: FoodController): Fragment(), FoodAdapter.OnItemClickListener {

    private lateinit var adapter: FoodAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodList: ArrayList<Food>

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
        adapter = FoodAdapter(foodList, this)
        recyclerView.adapter = adapter
    }

    override fun onAddItemClick(position: Int, amount: Int) {
        Log.d("EMPTY", "Add")
    }

    override fun onRemoveItemClick(position: Int, amount: Int) {
        Log.d("EMPTY", "Remove")
    }
}