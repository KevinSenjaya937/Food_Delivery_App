package au.edu.curtin.fooddeliveryapp.fragments.Cart

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.FoodOrder
import au.edu.curtin.fooddeliveryapp.controller.FoodController
import au.edu.curtin.fooddeliveryapp.controller.UserController
import au.edu.curtin.fooddeliveryapp.fragments.Account.LoginFragment


class CartFragment(private val foodController: FoodController,
                   private val userController: UserController
                  ) : Fragment(), CartAdapter.OnItemClickListener {

    private lateinit var adapter: CartAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodOrderList: ArrayList<FoodOrder>
    private lateinit var checkOutBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.foodOrderList = foodController.getFoodOrders()
        val layoutManager = LinearLayoutManager(context)

        recyclerView = view.findViewById(R.id.cart_recycler)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = CartAdapter(foodOrderList, this)
        recyclerView.adapter = adapter

        checkOutBtn = view.findViewById(R.id.checkOutButton)
        checkOutBtn.setOnClickListener {

            if (userController.userLoggedIn()) {
                if (foodController.checkFoodOrders() && !foodController.checkFoodOrderEmpty()) {
                    foodController.checkOut(userController.getUserID())
                    Toast.makeText(context, "Order Made", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Please check order amounts.", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                if (foodController.checkFoodOrders() && !foodController.checkFoodOrderEmpty()) {
                    parentFragmentManager.beginTransaction().apply {
                        replace(R.id.scrollingFragment, LoginFragment(userController, foodController))
                        commit()
                    }
                } else {
                    if (foodController.checkFoodOrderEmpty()) {
                        Toast.makeText(context, "Food order cannot be empty", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Please check order amounts.", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }

    override fun onAddItemClick(position: Int) {
        foodOrderList[position].amount++
        adapter.notifyItemChanged(position)
    }

    override fun onRemoveItemClick(position: Int) {
        val foodOrder = foodOrderList[position]

        if (foodOrder.amount != 1) {
            foodOrder.amount--
            adapter.notifyItemChanged(position)
        }

    }

    override fun onRemoveItemFromCartClick(position: Int) {
        foodOrderList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    override fun onCheckOutClick() {
    }


}