package au.edu.curtin.fooddeliveryapp.controller

import android.util.Log
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.Food
import au.edu.curtin.fooddeliveryapp.classes.FoodOrder
import au.edu.curtin.fooddeliveryapp.classes.Order
import au.edu.curtin.fooddeliveryapp.classes.Restaurant
import au.edu.curtin.fooddeliveryapp.database.DBHelper

class FoodController(database: DBHelper) {

    private lateinit var currentFoodList: ArrayList<Food>
    private var foodHashMap: HashMap<Int, ArrayList<Food>> = HashMap()
    private var orderList: ArrayList<Order> = ArrayList()
    private var foodOrderList = ArrayList<FoodOrder>()
    private var foodOrderHashMap: HashMap<Int, ArrayList<FoodOrder>> = HashMap()
    private var orderNumber: Int = -1
    private var db = database
    private var id = 0
    private var restaurantHashMap: HashMap<Int, String> = HashMap()


    fun load(restaurantID: Int) {
        this.currentFoodList = db.getAllFood()
        if (currentFoodList.isEmpty()) {
            initializeDB()
        }
    }

    fun getRestaurantName(restaurantID: Int): String? {
        return restaurantHashMap[restaurantID]
    }

    fun getFoodList(restaurantID: Int): java.util.ArrayList<Food>? {
        return foodHashMap[restaurantID]
    }

    fun getOrderList(): ArrayList<Order> {
        return orderList
    }

    fun getOrderItemCount(id: Int): Int? {
        return foodOrderHashMap[id]?.size
    }



    fun getFoodOrders(): ArrayList<Int> {
        val rIDs = ArrayList<Int>()
        for (restaurantID in foodOrderHashMap.keys) {
            rIDs.add(restaurantID)
        }
        return rIDs
    }

    fun getFoodOrdersDB(foodOrderID: Int): ArrayList<FoodOrder> {
        return db.getAllFoodOrders(foodOrderID)
    }

    fun createOrder(r: Restaurant) {
        val found = orderList.any { it.restaurantID == r.id }

        if (!found) {
            val order = Order(0, r.name, r.id, 0, 0, "", "", r.logo, 0)
            orderList.add(order)
        }
    }

    fun addFoodOrder(f: Food, amount: Int) {
        val totalPrice = f.price * amount
        val foodOrderID = db.getLastFoodOrderId()
        val foodOrder = FoodOrder(foodOrderID+1, 0, f.restaurantName, f.name, amount, totalPrice, f.picture)
        foodOrderHashMap[f.restaurantID]?.add(foodOrder)
    }

    fun calculateTotalOrderCost(id: Int): Int {
        val foodOrders = foodOrderHashMap[id]
        var total = 0
        if (foodOrders != null) {
            for (order in foodOrders) {
                total += order.totalPrice
            }
        }
        return total
    }

    fun getLastOrderID(): Int {
        val lastOrderID = db.getLastOrderId()

        if (lastOrderID == -1) {
            return 1
        }
        return lastOrderID
    }
















    private fun initializeDB(){

        val restaurantNames = db.getRestaurantNames()

        for (name in restaurantNames) {
            val foodItems = ArrayList<Food>()
            val id = db.getRestaurantID(name)

            insertFoodItems(id, name, foodItems)
            this.foodHashMap[id] = foodItems
            this.foodOrderHashMap[id] = ArrayList<FoodOrder>()
            this.restaurantHashMap[id] = name
            for (food in foodItems) {
                Log.d("EMPTY", "${food.name} : ${food.restaurantName}")
            }
        }
    }

    private fun insertFoodItems(id: Int, name: String, foodItems: ArrayList<Food>) {
        foodItems.add(Food(id, name,"Fries", 4, "860kJ", R.drawable.fries_pic))
        foodItems.add(Food(id, name, "Burger", 10, "1860kJ", R.drawable.burger_pic))
        foodItems.add(Food(id, name, "Soft Drink", 3, "560kJ", R.drawable.drink_pic))
        foodItems.add(Food(id, name, "Grilled Chicken", 9, "1560kJ", R.drawable.grilled_chicken_pic))
        foodItems.add(Food(id, name, "Fish and Chips", 12, "2860kJ", R.drawable.fish_and_chips_pic))
        foodItems.add(Food(id, name, "Kebab", 11, "1760kJ", R.drawable.kebab_pic))
        foodItems.add(Food(id, name, "Burrito", 9, "1740kJ", R.drawable.burrito_pic))
        foodItems.add(Food(id, name, "Tacos", 15, "1440kJ", R.drawable.tacos_pic))
        foodItems.add(Food(id, name, "Fried Chicken", 15, "2470kJ", R.drawable.fried_chicken_pic))
        foodItems.add(Food(id, name, "Pizza", 10, "1470kJ", R.drawable.pizza_pic))
        foodItems.add(Food(id, name, "Sub Sandwich", 10, "1870kJ", R.drawable.sub_sandwich_pic))
        foodItems.add(Food(id, name, "Cookies", 6, "1270kJ", R.drawable.cookies_pic))
    }
}