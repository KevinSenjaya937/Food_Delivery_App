package au.edu.curtin.fooddeliveryapp.controller

import android.util.Log
import au.edu.curtin.fooddeliveryapp.classes.Restaurant
import au.edu.curtin.fooddeliveryapp.database.DBHelper
import java.io.BufferedReader
import java.io.InputStreamReader

class RestaurantController(database: DBHelper) {

    private lateinit var restaurantList : List<Restaurant>
    private var db = database

    fun load(data: InputStreamReader) {
        this.restaurantList = getList()

        if (restaurantList.isEmpty()) {
            initializeDB(data)
        }
        for (restaurant in restaurantList) {
            Log.d("EMPTY", "${restaurant.id} : ${restaurant.name}")
        }
    }

    fun getList(): ArrayList<Restaurant> {
        return db.getAllRestaurants()
    }

    private fun readRestaurantData(inputStream: InputStreamReader): List<Restaurant> {
        val reader = BufferedReader(inputStream)
        reader.readLine()
        return reader.lineSequence()
            .filter { it.isNotBlank() }
            .map {
                val (id, name, foodType, location, logo) = it.split(',', ignoreCase = false, limit = 5)
                Restaurant(id.toInt(), name, foodType, location, logo)
            }.toList()
    }

    private fun initializeDB(inputStream: InputStreamReader) {
        this.restaurantList = readRestaurantData(inputStream)

        for (restaurant in restaurantList) {
            db.insertRestaurant(restaurant)
        }
    }
}