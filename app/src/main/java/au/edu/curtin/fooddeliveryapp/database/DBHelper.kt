package au.edu.curtin.fooddeliveryapp.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import au.edu.curtin.fooddeliveryapp.classes.*
import java.lang.Exception

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "restaurants.db"
        const val DATABASE_VERSION = 1

        const val RESTAURANT_TABLE = "restaurants"
        const val FOOD_TABLE = "food"
        const val FOOD_ORDER_TABLE = "foodOrders"
        const val ORDERS_TABLE = "orders"
        const val USERS_TABLE = "users"

        const val ID = "id"
        const val NAME = "name"

        // Restaurant Table
        const val FOOD_TYPE = "type"
        const val LOCATION = "location"
        const val LOGO = "logo"

        // Food Table
        const val PRICE = "price"
        const val DESCRIPTION = "description"
        const val PICTURE = "picture"

        // Food Order Table
        const val FOOD_ID = "foodID"
        const val AMOUNT = "amount"

        // Orders Table
        const val ORDER_NUMBER = "id"
        const val TOTAL_PRICE = "total"
        const val TIME = "time"
        const val DATE = "date"
        const val RESTAURANT = "restaurantID"
        const val USER = "userID"

        // Users Table
        const val FIRST_NAME = "firstName"
        const val LAST_NAME = "lastName"
        const val EMAIL = "email"
        const val PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createRestaurantTable = ("CREATE TABLE $RESTAURANT_TABLE ($ID INTEGER PRIMARY KEY, $NAME TEXT, $FOOD_TYPE TEXT, $LOCATION TEXT, $LOGO INTEGER)")
        val createFoodTable = ("CREATE TABLE $FOOD_TABLE ($ID INTEGER PRIMARY KEY, $NAME TEXT, $PRICE INTEGER, $DESCRIPTION TEXT, $PICTURE INTEGER)")
        val createFoodOrdersTable = ("CREATE TABLE $FOOD_ORDER_TABLE ($ORDER_NUMBER INTEGER PRIMARY KEY, $RESTAURANT INTEGER, $FOOD_ID INTEGER, $AMOUNT INTEGER, $TOTAL_PRICE INTEGER)")
        val createOrdersTable = ("CREATE TABLE $ORDERS_TABLE ($ORDER_NUMBER INTEGER PRIMARY KEY, $TOTAL_PRICE INTEGER, $TIME TEXT, $DATE TEXT, $RESTAURANT INTEGER, $USER INTEGER)")
        val createUsersTable = ("CREATE TABLE $USERS_TABLE ($USER INTEGER PRIMARY KEY, $FIRST_NAME TEXT, $LAST_NAME TEXT, $EMAIL TEXT, $PASSWORD TEXT)")
        db?.execSQL(createRestaurantTable)
        db?.execSQL(createFoodTable)
        db?.execSQL(createFoodOrdersTable)
        db?.execSQL(createOrdersTable)
        db?.execSQL(createUsersTable)
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
        db.close()
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

    fun insertFoodOrder(foodOrder: FoodOrder) {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ORDER_NUMBER, foodOrder.orderNumber)
        contentValues.put(FOOD_ID, foodOrder.foodID)
        contentValues.put(AMOUNT, foodOrder.amount)
        contentValues.put(TOTAL_PRICE, foodOrder.totalPrice)

        db.insert(FOOD_ORDER_TABLE, null, contentValues)
        db.close()
    }

    fun insertOrder(order: Order) {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ORDER_NUMBER, order.orderNumber)
        contentValues.put(TOTAL_PRICE, order.totalPrice)
        contentValues.put(TIME, order.time)
        contentValues.put(DATE, order.date)
        contentValues.put(RESTAURANT, order.restaurantID)
        contentValues.put(USER, order.userID)

        db.insert(ORDERS_TABLE, null, contentValues)
        db.close()
    }

    fun insertUser(user: User) {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(USER, user.userID)
        contentValues.put(FIRST_NAME, user.firstName)
        contentValues.put(LAST_NAME, user.lastName)
        contentValues.put(EMAIL, user.email)
        contentValues.put(PASSWORD, user.password)

        db.insert(USERS_TABLE, null, contentValues)
        db.close()
    }


    @SuppressLint("Range", "Recycle")
    fun getAllRestaurants() : ArrayList<Restaurant> {

        val restaurantList: ArrayList<Restaurant> = ArrayList()
        val selectQuery = "SELECT * FROM $RESTAURANT_TABLE"
        val db = this.readableDatabase

        val cursor: Cursor?

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

    @SuppressLint("Range", "Recycle")
    fun getAllFood() : ArrayList<Food> {

        val foodList: ArrayList<Food> = ArrayList()
        val selectQuery = "SELECT * FROM $FOOD_TABLE"
        val db = this.readableDatabase

        val cursor: Cursor?

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

    @SuppressLint("Range")
    fun getAllFoodOrders(orderNumber: Int) : ArrayList<FoodOrder> {

        val foodOrderList: ArrayList<FoodOrder> = ArrayList()
        val selectQuery = "SELECT * FROM $FOOD_ORDER_TABLE WHERE $ORDER_NUMBER LIKE $orderNumber"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var orderNumber: Int
        var restaurantID: Int
        var foodID: Int
        var amount: Int
        var totalPrice: Int

        if (cursor.moveToFirst()) {
            do {
                orderNumber = cursor.getInt(cursor.getColumnIndex(ORDER_NUMBER))
                restaurantID = cursor.getInt(cursor.getColumnIndex(RESTAURANT))
                foodID = cursor.getInt(cursor.getColumnIndex(FOOD_ID))
                amount = cursor.getInt(cursor.getColumnIndex(AMOUNT))
                totalPrice = cursor.getInt(cursor.getColumnIndex(TOTAL_PRICE))

                val foodOrder = FoodOrder(orderNumber, restaurantID, foodID, amount, totalPrice)
                foodOrderList.add(foodOrder)
            } while (cursor.moveToNext())
        }
        return foodOrderList
    }

    @SuppressLint("Range")
    fun getLastOrderId(): Int {
        val selectQuery = "SELECT * FROM $ORDERS_TABLE"
        val db = this.readableDatabase

        val cursor: Cursor? = db.rawQuery(selectQuery, null)

        var orderID : Int = -1

        if (cursor != null) {
            if (cursor.moveToLast()) {
                orderID = cursor.getInt(cursor.getColumnIndex(ORDER_NUMBER))
            }
        }
        return orderID
    }

    @SuppressLint("Range")
    fun getAllOrders() : ArrayList<Order> {

        val orderList: ArrayList<Order> = ArrayList()
        val selectQuery = "SELECT * FROM $ORDERS_TABLE"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var orderNumber: Int
        var totalPrice: Int
        var time: String
        var date: String
        var restaurant: Int
        var user: Int

        if (cursor.moveToFirst()) {
            do {
                orderNumber = cursor.getInt(cursor.getColumnIndex(ORDER_NUMBER))
                totalPrice = cursor.getInt(cursor.getColumnIndex(TOTAL_PRICE))
                time = cursor.getString(cursor.getColumnIndex(TIME))
                date = cursor.getString(cursor.getColumnIndex(DATE))
                restaurant = cursor.getInt(cursor.getColumnIndex(RESTAURANT))
                user = cursor.getInt(cursor.getColumnIndex(USER))

                val order = Order(orderNumber, totalPrice, time, date, restaurant, user)
                orderList.add(order)
            } while (cursor.moveToNext())
        }
        return orderList
    }



    @SuppressLint("Range")
    fun getUser(email: String, password: String): User? {

        val selectQuery = "SELECT * FROM $USERS_TABLE WHERE $EMAIL LIKE $email AND $PASSWORD LIKE $password"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return null
        }

        val userID: Int
        val firstName: String
        val lastName: String
        val userEmail: String
        val userPassword: String

        if (cursor.moveToFirst()) {
            userID = cursor.getInt(cursor.getColumnIndex(USER))
            firstName = cursor.getString(cursor.getColumnIndex(FIRST_NAME))
            lastName = cursor.getString(cursor.getColumnIndex(LAST_NAME))
            userEmail = cursor.getString(cursor.getColumnIndex(EMAIL))
            userPassword = cursor.getString(cursor.getColumnIndex(PASSWORD))

            return User(userID, firstName, lastName, userEmail, userPassword)
        }
        return null
    }
}