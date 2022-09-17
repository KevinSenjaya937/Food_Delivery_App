package au.edu.curtin.fooddeliveryapp.controller

import android.util.Log
import au.edu.curtin.fooddeliveryapp.classes.FoodOrder
import au.edu.curtin.fooddeliveryapp.classes.Order
import au.edu.curtin.fooddeliveryapp.database.DBHelper
import kotlin.properties.Delegates

class OrderController(database: DBHelper) {

    private var db = database
    private var orderList = ArrayList<Order>()
    private lateinit var foodOrderList : ArrayList<FoodOrder>
    private var currentOrderNumber by Delegates.notNull<Int>()

    fun load() {
        this.currentOrderNumber = db.getLastOrderId()
        this.foodOrderList = getFoodOrders(currentOrderNumber)
        this.foodOrderList.sortWith(compareBy { it.restaurantName })

        for (foodOrder in foodOrderList) {
            Log.d("EMPTY", foodOrder.foodName)
        }
    }

    private fun calculateTotalCost(): Int {
        return foodOrderList.sumOf { it.totalPrice }
    }

    private fun getNumberOfFoodItems(): Int {
        return foodOrderList.size
    }


    fun getFoodOrders(foodOrderID: Int): ArrayList<FoodOrder> {
        return db.getAllFoodOrders(foodOrderID)
    }

    fun getList(): ArrayList<FoodOrder> {
        for (foodOrder in foodOrderList) {
            Log.d("EMPTY", foodOrder.foodName)
        }
        return this.foodOrderList
    }

}