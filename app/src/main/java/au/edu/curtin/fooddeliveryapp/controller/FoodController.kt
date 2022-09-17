package au.edu.curtin.fooddeliveryapp.controller

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.Food
import au.edu.curtin.fooddeliveryapp.classes.FoodOrder
import au.edu.curtin.fooddeliveryapp.database.DBHelper
import java.io.ByteArrayOutputStream

class FoodController(database: DBHelper) {

    private lateinit var foodList: ArrayList<Food>
    private var foodOrderList = ArrayList<FoodOrder>()
    private var orderNumber: Int = -1
    private var db = database
    private var id = 0

    fun load() {
        this.foodList = getList()
        if (foodList.isEmpty()) {
            initializeDB()
            Log.d("EMPTY", "List is empty")
        } else {
            for (food in foodList) {
                Log.d("EMPTY", food.name)
            }
        }
    }

    fun getFoodOrders(): ArrayList<FoodOrder> {
        return foodOrderList
    }

    fun getList(): ArrayList<Food> {
        return db.getAllFood()
    }

    fun getFoodOrders(foodOrderID: Int): ArrayList<FoodOrder> {
        return db.getAllFoodOrders(foodOrderID)
    }

    fun addFoodOrder(foodOrder: FoodOrder) {
        foodOrder.orderNumber
        foodOrderList.add(foodOrder)
    }

    fun getLastOrderID(): Int {
        val lastOrderID = db.getLastOrderId()

        if (lastOrderID == -1) {
            return 1
        }
        return lastOrderID
    }

    fun initializeDB(){
        foodList.add(Food(id++, "Fries", 4, "860kJ", R.drawable.fries_pic))
        foodList.add(Food(id++, "Burger", 10, "1860kJ", R.drawable.burger_pic))
        foodList.add(Food(id++, "Soft Drink", 3, "560kJ", R.drawable.drink_pic))
        foodList.add(Food(id++, "Grilled Chicken", 9, "1560kJ", R.drawable.grilled_chicken_pic))
        foodList.add(Food(id++, "Fish and Chips", 12, "2860kJ", R.drawable.fish_and_chips_pic))
        foodList.add(Food(id++, "Kebab", 11, "1760kJ", R.drawable.kebab_pic))
        foodList.add(Food(id++, "Burrito", 9, "1740kJ", R.drawable.burrito_pic))
        foodList.add(Food(id++, "Tacos", 15, "1440kJ", R.drawable.tacos_pic))
        foodList.add(Food(id++, "Fried Chicken", 15, "2470kJ", R.drawable.fried_chicken_pic))
        foodList.add(Food(id++, "Pizza", 10, "1470kJ", R.drawable.pizza_pic))
        foodList.add(Food(id++, "Sub Sandwich", 10, "1870kJ", R.drawable.sub_sandwich_pic))
        foodList.add(Food(id++, "Cookies", 6, "1270kJ", R.drawable.cookies_pic))

        for (food in foodList) {
            db.insertFood(food)
        }
    }
}