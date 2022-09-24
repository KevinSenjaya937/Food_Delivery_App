package au.edu.curtin.fooddeliveryapp.fragments.Account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.fooddeliveryapp.MainActivity
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.FoodOrder
import au.edu.curtin.fooddeliveryapp.controller.FoodController
import au.edu.curtin.fooddeliveryapp.controller.UserController
import au.edu.curtin.fooddeliveryapp.fragments.Cart.CartAdapter


class AccountFragment(private val userController: UserController,
                      private val foodController: FoodController
                     ) : Fragment(), CartAdapter.OnItemClickListener {

    private lateinit var adapter: CartAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var pastFoodOrders: List<FoodOrder>
    private lateinit var userEmail: TextView
    private lateinit var logoutBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.pastFoodOrders = userController.getUserPastOrders()

        this.userEmail = view.findViewById(R.id.userEmail)
        userEmail.text = userController.getUserEmail()

        this.logoutBtn = view.findViewById(R.id.logoutBtn)
        logoutBtn.setOnClickListener() {
            userController.logoutUser()
            foodController.logoutUser()
            (activity as MainActivity).clickHomeBtn()
        }

        val layoutManager = LinearLayoutManager(context)

        recyclerView = view.findViewById(R.id.pastOrderRecyclerView)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = CartAdapter(pastFoodOrders, this)
        recyclerView.adapter = adapter
    }

    override fun onAddItemClick(position: Int) {
    }

    override fun onRemoveItemClick(position: Int) {
    }

    override fun onRemoveItemFromCartClick(position: Int) {
    }

    override fun onCheckOutClick() {
    }

}