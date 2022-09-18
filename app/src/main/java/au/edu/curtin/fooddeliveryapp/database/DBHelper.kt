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

        // Restaurant Table
        const val RESTAURANT_ID = "restaurantID"
        const val RESTAURANT_NAME = "restaurantName"
        const val FOOD_TYPE = "foodType"
        const val RESTAURANT_LOCATION = "location"
        const val RESTAURANT_LOGO = "logo"

        // Food Table
        // RESTAURANT_ID
        // RESTAURANT_NAME
        const val FOOD_NAME = "foodName"
        const val FOOD_PRICE = "price"
        const val FOOD_DESCRIPTION = "description"
        const val FOOD_PICTURE = "picture"

        // Food Order Table
        const val FOOD_ORDER_ID = "foodOrderID"
        //ORDER_ID
        //RESTAURANT_NAME
        //FOOD_NAME
        const val FOOD_ORDER_AMOUNT = "amountOrdered"
        const val TOTAL_FOOD_ORDER_PRICE = "foodOrderPrice"
        //FOOD_PICTURE


        // Orders Table
        const val ORDER_ID = "id"
        //RESTAURANT_NAME
        //RESTAURANT_ID
        const val ORDER_ITEM_AMOUNT = "amountOfItems"
        const val TOTAL_PRICE = "totalPrice"
        const val TIME = "time"
        const val DATE = "date"
        //RESTAURANT_LOGO
        //USER_ID

        // Users Table
        const val USER_ID = "userID"
        const val FIRST_NAME = "firstName"
        const val LAST_NAME = "lastName"
        const val EMAIL = "email"
        const val PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createRestaurantTable = ("CREATE TABLE $RESTAURANT_TABLE ($RESTAURANT_ID INTEGER PRIMARY KEY, $RESTAURANT_NAME TEXT, $FOOD_TYPE TEXT, $RESTAURANT_LOCATION TEXT, $RESTAURANT_LOGO INTEGER)")

        val createFoodTable = ("CREATE TABLE $FOOD_TABLE ($RESTAURANT_ID INTEGER PRIMARY KEY, $RESTAURANT_NAME TEXT, $FOOD_NAME TEXT, $FOOD_PRICE INTEGER, $FOOD_DESCRIPTION TEXT, $FOOD_PICTURE INTEGER)")

        val createFoodOrdersTable = ("CREATE TABLE $FOOD_ORDER_TABLE ($FOOD_ORDER_ID INTEGER PRIMARY KEY, $ORDER_ID INTEGER, $RESTAURANT_NAME TEXT, $RESTAURANT_ID INTEGER, $FOOD_NAME TEXT, $FOOD_ORDER_AMOUNT INTEGER, $TOTAL_FOOD_ORDER_PRICE INTEGER, $FOOD_PICTURE INTEGER)")

        val createOrdersTable = ("CREATE TABLE $ORDERS_TABLE ($ORDER_ID INTEGER PRIMARY KEY, $RESTAURANT_NAME TEXT, $ORDER_ITEM_AMOUNT INTEGER, $TOTAL_PRICE INTEGER, $TIME TEXT, $DATE TEXT, $RESTAURANT_LOGO INTEGER, $USER_ID INTEGER)")
        val createUsersTable = ("CREATE TABLE $USERS_TABLE ($USER_ID INTEGER PRIMARY KEY, $FIRST_NAME TEXT, $LAST_NAME TEXT, $EMAIL TEXT, $PASSWORD TEXT)")
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


    //Checked
    fun insertRestaurant(restaurant: Restaurant) {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(RESTAURANT_ID, restaurant.id)
        contentValues.put(RESTAURANT_NAME, restaurant.name)
        contentValues.put(FOOD_TYPE, restaurant.foodType)
        contentValues.put(RESTAURANT_LOCATION, restaurant.location)
        contentValues.put(RESTAURANT_LOGO, restaurant.logo)

        db.insert(RESTAURANT_TABLE, null, contentValues)
        db.close()
    }

    //Checked
    fun insertFood(food: Food) {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(RESTAURANT_ID, food.restaurantID)
        contentValues.put(RESTAURANT_NAME, food.restaurantName)
        contentValues.put(FOOD_NAME, food.name)
        contentValues.put(FOOD_PRICE, food.price)
        contentValues.put(FOOD_DESCRIPTION, food.description)
        contentValues.put(FOOD_PICTURE, food.picture)

        db.insert(FOOD_TABLE, null, contentValues)
        db.close()
    }

    //Checked
    fun insertFoodOrder(foodOrder: FoodOrder) {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(FOOD_ORDER_ID, foodOrder.foodOrderID)
        contentValues.put(ORDER_ID, foodOrder.orderID)
        contentValues.put(RESTAURANT_NAME, foodOrder.restaurantName)
        contentValues.put(FOOD_NAME, foodOrder.foodName)
        contentValues.put(FOOD_ORDER_AMOUNT, foodOrder.amount)
        contentValues.put(TOTAL_FOOD_ORDER_PRICE, foodOrder.totalPrice)
        contentValues.put(FOOD_PICTURE, foodOrder.foodPicture)

        db.insert(FOOD_ORDER_TABLE, null, contentValues)
        db.close()
    }

    //Checked
    fun insertOrder(order: Order) {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ORDER_ID, order.orderID)
        contentValues.put(RESTAURANT_NAME, order.restaurantName)
        contentValues.put(RESTAURANT_ID, order.restaurantID)
        contentValues.put(TOTAL_PRICE, order.totalPrice)
        contentValues.put(TIME, order.time)
        contentValues.put(DATE, order.date)
        contentValues.put(RESTAURANT_LOGO, order.restaurantLogo)
        contentValues.put(USER_ID, order.userID)

        db.insert(ORDERS_TABLE, null, contentValues)
        db.close()
    }

    //Checked
    fun insertUser(user: User) {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(USER_ID, user.userID)
        contentValues.put(FIRST_NAME, user.firstName)
        contentValues.put(LAST_NAME, user.lastName)
        contentValues.put(EMAIL, user.email)
        contentValues.put(PASSWORD, user.password)

        db.insert(USERS_TABLE, null, contentValues)
        db.close()
    }

    // Checked
    @SuppressLint("Range")
    fun getRestaurantNames(): ArrayList<String> {
        val restaurantNames: ArrayList<String> = ArrayList()
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

        var restaurantName: String

        if (cursor.moveToFirst()) {
            do {
                restaurantName = cursor.getString(cursor.getColumnIndex(RESTAURANT_NAME))

                restaurantNames.add(restaurantName)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return restaurantNames
    }

    // Checked
    @SuppressLint("Range")
    fun getRestaurantID(name: String): Int {

        val selectQuery = "SELECT * FROM $RESTAURANT_TABLE"
        val db = this.readableDatabase

        val cursor: Cursor? = db.rawQuery(selectQuery, null)

        var restaurantName: String
        var restaurantID : Int = -1

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    restaurantName = cursor.getString(cursor.getColumnIndex(RESTAURANT_NAME))
                    if (restaurantName == name) {
                        restaurantID = cursor.getInt(cursor.getColumnIndex(RESTAURANT_ID))
                    }
                } while (cursor.moveToNext())
            }
        }
        cursor?.close()
        return restaurantID
    }

    // Checked
    @SuppressLint("Range")
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
                id = cursor.getInt(cursor.getColumnIndex(RESTAURANT_ID))
                name = cursor.getString(cursor.getColumnIndex(RESTAURANT_NAME))
                foodType = cursor.getString(cursor.getColumnIndex(FOOD_TYPE))
                location = cursor.getString(cursor.getColumnIndex(RESTAURANT_LOCATION))
                logo = cursor.getInt(cursor.getColumnIndex(RESTAURANT_LOGO))

                val restaurant = Restaurant(id, name, foodType, location, logo)
                restaurantList.add(restaurant)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return restaurantList
    }

    // Checked
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

        var restaurantID: Int
        var restaurantName: String
        var name: String
        var price: Int
        var description: String
        var picture: Int

        if (cursor.moveToFirst()) {
            do {
                restaurantID = cursor.getInt(cursor.getColumnIndex(RESTAURANT_ID))
                restaurantName = cursor.getString(cursor.getColumnIndex(RESTAURANT_NAME))
                name = cursor.getString(cursor.getColumnIndex(FOOD_NAME))
                price = cursor.getInt(cursor.getColumnIndex(FOOD_PRICE))
                description = cursor.getString(cursor.getColumnIndex(FOOD_DESCRIPTION))
                picture = cursor.getInt(cursor.getColumnIndex(FOOD_PICTURE))

                val food = Food(restaurantID, restaurantName, name, price, description, picture)
                foodList.add(food)
            } while (cursor.moveToNext())
        }
        return foodList
    }

    // Checked
    @SuppressLint("Range")
    fun getLastFoodOrderId(): Int {
        val selectQuery = "SELECT * FROM $FOOD_ORDER_TABLE"
        val db = this.readableDatabase

        val cursor: Cursor? = db.rawQuery(selectQuery, null)

        var foodOrderID : Int = -1

        if (cursor != null) {
            if (cursor.moveToLast()) {
                foodOrderID = cursor.getInt(cursor.getColumnIndex(FOOD_ORDER_ID))
            }
        }
        cursor?.close()
        return foodOrderID
    }

    // Checked
    @SuppressLint("Range")
    fun getAllFoodOrders(id: Int) : ArrayList<FoodOrder> {

        val foodOrderList: ArrayList<FoodOrder> = ArrayList()
        val selectQuery = "SELECT * FROM $FOOD_ORDER_TABLE WHERE $ORDER_ID LIKE $id"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var foodOrderID: Int
        var orderID: Int
        var restaurantName: String
        var foodName: String
        var amount: Int
        var totalPrice: Int
        var foodPicture: Int

        if (cursor.moveToFirst()) {
            do {
                foodOrderID = cursor.getInt(cursor.getColumnIndex(FOOD_ORDER_ID))
                orderID = cursor.getInt(cursor.getColumnIndex(ORDER_ID))
                restaurantName = cursor.getString(cursor.getColumnIndex(RESTAURANT_NAME))
                foodName = cursor.getString(cursor.getColumnIndex(FOOD_NAME))
                amount = cursor.getInt(cursor.getColumnIndex(FOOD_ORDER_AMOUNT))
                totalPrice = cursor.getInt(cursor.getColumnIndex(TOTAL_FOOD_ORDER_PRICE))
                foodPicture = cursor.getInt(cursor.getColumnIndex(FOOD_PICTURE))

                val foodOrder = FoodOrder(foodOrderID, orderID, restaurantName, foodName, amount, totalPrice,  foodPicture)
                foodOrderList.add(foodOrder)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return foodOrderList
    }

    // Checked
    @SuppressLint("Range")
    fun getLastOrderId(): Int {
        val selectQuery = "SELECT * FROM $ORDERS_TABLE"
        val db = this.readableDatabase

        val cursor: Cursor? = db.rawQuery(selectQuery, null)

        var orderID : Int = -1

        if (cursor != null) {
            if (cursor.moveToLast()) {
                orderID = cursor.getInt(cursor.getColumnIndex(ORDER_ID))
            }
        }
        cursor?.close()
        return orderID
    }

    // Checked
    @SuppressLint("Range", "Recycle")
    fun getAllOrders() : ArrayList<Order> {

        val orderList: ArrayList<Order> = ArrayList()
        val selectQuery = """SELECT * FROM $ORDERS_TABLE"""
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var orderID: Int
        var restaurantName: String
        var restaurantID: Int
        var amountOfItems: Int
        var totalPrice: Int
        var time: String
        var date: String
        var restaurantLogo: Int
        var user: Int

        if (cursor.moveToFirst()) {
            do {
                orderID = cursor.getInt(cursor.getColumnIndex(ORDER_ID))
                restaurantName = cursor.getString(cursor.getColumnIndex(RESTAURANT_NAME))
                restaurantID = cursor.getInt(cursor.getColumnIndex(RESTAURANT_ID))
                amountOfItems = cursor.getInt(cursor.getColumnIndex(ORDER_ITEM_AMOUNT))
                totalPrice = cursor.getInt(cursor.getColumnIndex(TOTAL_PRICE))
                time = cursor.getString(cursor.getColumnIndex(TIME))
                date = cursor.getString(cursor.getColumnIndex(DATE))
                restaurantLogo = cursor.getInt(cursor.getColumnIndex(RESTAURANT_LOGO))
                user = cursor.getInt(cursor.getColumnIndex(USER_ID))

                val order = Order(orderID, restaurantName, restaurantID, amountOfItems, totalPrice, time, date, restaurantLogo, user)
                orderList.add(order)
            } while (cursor.moveToNext())
        }
        return orderList
    }

    // Checked
    @SuppressLint("Range", "Recycle")
    fun getUser(email: String, password: String): User? {

        val selectQuery = """SELECT * FROM $USERS_TABLE WHERE $EMAIL LIKE $email AND $PASSWORD LIKE $password"""
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
            userID = cursor.getInt(cursor.getColumnIndex(USER_ID))
            firstName = cursor.getString(cursor.getColumnIndex(FIRST_NAME))
            lastName = cursor.getString(cursor.getColumnIndex(LAST_NAME))
            userEmail = cursor.getString(cursor.getColumnIndex(EMAIL))
            userPassword = cursor.getString(cursor.getColumnIndex(PASSWORD))

            return User(userID, firstName, lastName, userEmail, userPassword)
        }
        return null
    }
}