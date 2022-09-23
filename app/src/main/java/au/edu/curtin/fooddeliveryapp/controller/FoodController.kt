package au.edu.curtin.fooddeliveryapp.controller

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.Food
import au.edu.curtin.fooddeliveryapp.classes.FoodOrder
import au.edu.curtin.fooddeliveryapp.classes.Restaurant
import au.edu.curtin.fooddeliveryapp.database.DBHelper
import java.io.BufferedReader
import java.io.InputStreamReader
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.log

class FoodController(database: DBHelper) {

    private lateinit var foodList: List<Food>
    private var foodHashMap: HashMap<Int, ArrayList<Food>> = HashMap()
    private var foodOrderList = ArrayList<FoodOrder>()
    private var db = database
    private var loaded = false


    fun load(data: InputStreamReader) {

        this.foodList = getList()

        if (foodList.isEmpty()) {
            initializeDB(data)
        } else {
            initializeHashmap()
        }
    }

    fun getList(): ArrayList<Food> {
        return db.getAllFood()
    }

    fun initializeHashmap() {
        foodHashMap.clear()
        val restaurants = db.getAllRestaurants()

        for (restaurant in restaurants) {
            foodHashMap[restaurant.id] = ArrayList<Food>()
        }

        for (food in foodList) {
            foodHashMap[food.restaurantID]?.add(food)
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


















    fun readFoodData(inputStreamReader: InputStreamReader): List<Food> {
        val reader = BufferedReader(inputStreamReader)
        val header = reader.readLine()
        return reader.lineSequence()
            .filter { it.isNotBlank() }
            .map {
                val (foodID, rID, rName, fName, fPrice, fDesc, fPic) = it.split(',', ignoreCase = false, limit = 7)
                Food(foodID.toInt(), rID.toInt(), rName, fName, fPrice.toInt(), fDesc, fPic)
            }.toList()
    }

    fun initializeDB(inputStreamReader: InputStreamReader){
        this.foodList = readFoodData(inputStreamReader)
        initializeHashmap()
    }

}

private operator fun <E> List<E>.component6(): E = get(5)

private operator fun <E> List<E>.component7(): E = get(6)

