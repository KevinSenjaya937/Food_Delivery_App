package au.edu.curtin.fooddeliveryapp.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import au.edu.curtin.fooddeliveryapp.classes.Food
import au.edu.curtin.fooddeliveryapp.classes.Restaurant
import java.lang.Exception

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "restaurants.db"
        const val DATABASE_VERSION = 1

        const val RESTAURANT_TABLE = "restaurants"
        const val FOOD_TABLE = "food"
        const val ID = "id"
        const val NAME = "name"

        const val FOOD_TYPE = "type"
        const val LOCATION = "location"
        const val LOGO = "logo"

        const val PRICE = "price"
        const val DESCRIPTION = "description"
        const val PICTURE = "picture"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createRestaurantTable = ("CREATE TABLE $RESTAURANT_TABLE ($ID INTEGER PRIMARY KEY, $NAME TEXT, $FOOD_TYPE TEXT, $LOCATION TEXT, $LOGO INTEGER)")
        val createFoodTable = ("CREATE TABLE $FOOD_TABLE ($ID INTEGER PRIMARY KEY, $NAME TEXT, $PRICE INTEGER, $DESCRIPTION TEXT, $PICTURE INTEGER)")
        db?.execSQL(createRestaurantTable)
        db?.execSQL(createFoodTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val dropDB = "DROP TABLE IF EXISTS $RESTAURANT_TABLE"
        db!!.execSQL(dropDB)
        onCreate(db)
    }



    fun insertRestaurant(restaurant: Restaurant) {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, restaurant.id)
        contentValues.put(NAME, restaurant.name)
        contentValues.put(FOOD_TYPE, restaurant.foodType)
        contentValues.put(LOCATION, restaurant.location)
        contentValues.put(LOGO, restaurant.logo)

        db.insert(RESTAURANT_TABLE, null, contentValues)
    }

    fun insertFood(food: Food) {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, food.id)
        contentValues.put(NAME, food.name)
        contentValues.put(PRICE, food.price)
        contentValues.put(DESCRIPTION, food.description)
        contentValues.put(PICTURE, food.picture)

        db.insert(FOOD_TABLE, null, contentValues)
        db.close()
    }

    @SuppressLint("Range")
    fun getAllRestaurants() : ArrayList<Restaurant> {

        val restaurantList: ArrayList<Restaurant> = ArrayList<Restaurant>()
        val selectQuery = "SELECT * FROM $RESTAURANT_TABLE"
        val db = this.readableDatabase

        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var foodType: String
        var location: String
        var logo: Int

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(ID))
                name = cursor.getString(cursor.getColumnIndex(NAME))
                foodType = cursor.getString(cursor.getColumnIndex(FOOD_TYPE))
                location = cursor.getString(cursor.getColumnIndex(LOCATION))
                logo = cursor.getInt(cursor.getColumnIndex(LOGO))

                val restaurant = Restaurant(id, name, foodType, location, logo)
                restaurantList.add(restaurant)
            } while (cursor.moveToNext())
        }
        return restaurantList
    }

    @SuppressLint("Range")
    fun getAllFood() : ArrayList<Food> {

        val foodList: ArrayList<Food> = ArrayList<Food>()
        val selectQuery = "SELECT * FROM $FOOD_TABLE"
        val db = this.readableDatabase

        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var price: Int
        var description: String
        var picture: Int

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(ID))
                name = cursor.getString(cursor.getColumnIndex(NAME))
                price = cursor.getInt(cursor.getColumnIndex(PRICE))
                description = cursor.getString(cursor.getColumnIndex(DESCRIPTION))
                picture = cursor.getInt(cursor.getColumnIndex(PICTURE))

                val food = Food(id, name, price, description, picture)
                foodList.add(food)
            } while (cursor.moveToNext())
        }
        return foodList
    }

}