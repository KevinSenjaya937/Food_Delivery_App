package au.edu.curtin.fooddeliveryapp.controller

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import au.edu.curtin.fooddeliveryapp.classes.Food
import au.edu.curtin.fooddeliveryapp.classes.FoodOrder
import au.edu.curtin.fooddeliveryapp.database.DBHelper
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.random.Random

class FoodController(database: DBHelper) {

    private lateinit var foodList: List<Food>
    private var foodHashMap: HashMap<Int, ArrayList<Food>> = HashMap()
    private var foodOrderList = ArrayList<FoodOrder>()
    private var db = database


    fun load(data: InputStreamReader) {

        this.foodList = getList()

        if (foodList.isEmpty()) {
            initializeDB(data)
        } else {
            initializeHashmap()
        }
    }

    private fun getList(): ArrayList<Food> {
        return db.getAllFood()
    }

    private fun initializeHashmap() {
        foodHashMap.clear()
        val restaurants = db.getAllRestaurants()

        for (restaurant in restaurants) {
            foodHashMap[restaurant.id] = ArrayList()
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

    fun checkFoodOrders(): Boolean {
        var allValid = true

        for (fo in foodOrderList) {
            if (fo.amount < 1) {
                allValid = false
            }
        }
        return allValid
    }

    fun checkFoodOrderEmpty(): Boolean {
        return foodOrderList.isEmpty()
    }

    @SuppressLint("SimpleDateFormat")
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

        foodOrderList.clear()
    }

    fun getDailyDeals(): ArrayList<Food> {
        val size = 10
        val foodSet = HashSet<Int>(size)
        while (foodSet.size < size) {
            foodSet += Random.nextInt(1,156)
        }

        val dailyDeals = ArrayList<Food>()

        for (foodID in foodSet) {
            dailyDeals.add(foodList[foodID])
        }

        return dailyDeals
    }

    fun getTotalOrderPrice(): String {
        var totalPrice = 0

        for (order in foodOrderList) {
            totalPrice += order.totalPrice
        }
        return "$$totalPrice.00"
    }

    fun logoutUser() {
        this.foodOrderList.clear()
    }


















    private fun readFoodData(inputStreamReader: InputStreamReader): List<Food> {
        val reader = BufferedReader(inputStreamReader)
        reader.readLine()
        return reader.lineSequence()
            .filter { it.isNotBlank() }
            .map {
                val (foodID, rID, rName, fName, fPrice, fDesc, fPic) = it.split(',', ignoreCase = false, limit = 7)
                Food(foodID.toInt(), rID.toInt(), rName, fName, fPrice.toInt(), fDesc, fPic)
            }.toList()
    }

    private fun initializeDB(inputStreamReader: InputStreamReader){
        this.foodList = readFoodData(inputStreamReader)
        initializeHashmap()

        for (food in foodList) {
            db.insertFood(food)
        }
    }

}

private operator fun <E> List<E>.component6(): E = get(5)

private operator fun <E> List<E>.component7(): E = get(6)

