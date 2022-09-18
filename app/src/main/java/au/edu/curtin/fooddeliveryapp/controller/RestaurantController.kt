package au.edu.curtin.fooddeliveryapp.controller

import android.content.Context
import android.util.Log
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.Restaurant
import au.edu.curtin.fooddeliveryapp.database.DBHelper

class RestaurantController(database: DBHelper) {

    private lateinit var restaurantList : ArrayList<Restaurant>
    private var db = database
    private var id = 0

    fun load() {
        this.restaurantList = getList()

        if (restaurantList.isEmpty()) {
            initializeDB()
        }
        for (restaurant in restaurantList) {
            Log.d("EMPTY", "${restaurant.id} : ${restaurant.name}")
        }
    }

    fun add(restaurant: Restaurant) {
        db.insertRestaurant(restaurant)
    }

    fun getList(): ArrayList<Restaurant> {
        return db.getAllRestaurants()
    }

    fun initializeDB() {
        restaurantList = ArrayList()
        restaurantList.add(Restaurant(id++, "KFC", "Fast Food", "Bentley", R.drawable.kfc_logo))
        restaurantList.add(Restaurant(id++, "McDonald's", "Fast Food", "Cannington", R.drawable.mcdonalds_logo))
        restaurantList.add(Restaurant(id++, "Hungry Jack's", "Fast Food", "Bentley", R.drawable.hungry_jacks_logo))
        restaurantList.add(Restaurant(id++, "Subway", "Fast Food", "Cannington", R.drawable.subway_logo))
        restaurantList.add(Restaurant(id++, "Taco Bell", "Fast Food", "Canning Vale", R.drawable.taco_bell_logo))
        restaurantList.add(Restaurant(id++, "Zambrero", "Mexican", "Victoria Park", R.drawable.zambrero_logo))
        restaurantList.add(Restaurant(id++, "Guzman Y Gomez", "Mexican", "Cockburn Central", R.drawable.gyg_logo))
        restaurantList.add(Restaurant(id++, "Nandos", "Flame-grilled Chicken", "Cannington", R.drawable.nandos_logo))
        restaurantList.add(Restaurant(id++, "Dominos", "Pizza", "Bentley", R.drawable.dominos_logo))
        restaurantList.add(Restaurant(id++, "Pizza Hut", "Pizza", "Cannington", R.drawable.pizza_hut_logo))
        restaurantList.add(Restaurant(id++, "Wok In A Box", "Asian", "Cannington", R.drawable.wok_in_a_box_logo))
        restaurantList.add(Restaurant(id++, "Red Rooster", "Fast Food", "Bentley", R.drawable.red_rooster_logo))
        restaurantList.add(Restaurant(id++, "Chicken Treat", "Fast Food", "Canning Vale", R.drawable.chicken_treat_logo))

        for (restaurant in restaurantList) {
            db.insertRestaurant(restaurant)
        }
    }
}