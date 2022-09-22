package au.edu.curtin.fooddeliveryapp.controller

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.Food
import au.edu.curtin.fooddeliveryapp.classes.FoodOrder
import au.edu.curtin.fooddeliveryapp.database.DBHelper
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class FoodController(database: DBHelper) {

    private lateinit var currentFoodList: ArrayList<Food>
    private var foodHashMap: HashMap<Int, ArrayList<Food>> = HashMap()
    private var foodOrderList = ArrayList<FoodOrder>()
    private var db = database
    private var restaurantHashMap: HashMap<Int, String> = HashMap()
    private var loaded = false


    fun load(restaurantID: Int) {

        if (!this.loaded) {
            initializeDB()
        }
    }

    fun getFoodList(restaurantID: Int): java.util.ArrayList<Food>? {
        return foodHashMap[restaurantID]
    }

    fun addFoodOrder(f: Food, amount: Int) {
        val totalPrice = f.price * amount
        val foodOrderID = db.getLastFoodOrderId()
        val foodOrder = FoodOrder(foodOrderID+1, f.restaurantName, f.name, amount, totalPrice, f.picture, "", 0)
        foodOrderList.add(foodOrder)
    }

    fun getFoodOrders(): ArrayList<FoodOrder> {
        foodOrderList.sortBy { it.restaurantName }
        return foodOrderList
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun checkOut(userID: Int) {
        val simpleDatetime = SimpleDateFormat("EEE, MMM d, h:mm a")

        val currentDate = simpleDatetime.format(Date())
        val datetime = "Ordered on $currentDate"

        for (fo in foodOrderList) {
            fo.datetime = datetime
            fo.userID = userID
            db.insertFoodOrder(fo)
        }
    }




















    private fun initializeDB(){

        val restaurantNames = db.getRestaurantNames()

        for (name in restaurantNames) {
            val foodItems = ArrayList<Food>()
            val id = db.getRestaurantID(name)

            insertFoodItems(id, name, foodItems)
            this.foodHashMap[id] = foodItems
            this.restaurantHashMap[id] = name
            for (food in foodItems) {
                Log.d("EMPTY", "${food.name} : ${food.restaurantName}")
            }
        }
        this.loaded = true
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