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
        const val FOOD_ID = "foodID"
        // RESTAURANT_ID
        // RESTAURANT_NAME
        const val FOOD_NAME = "foodName"
        const val FOOD_PRICE = "price"
        const val FOOD_DESCRIPTION = "description"
        const val FOOD_PICTURE = "picture"

        // Food Order Table
        const val FOOD_ORDER_ID = "foodOrderID"
        //RESTAURANT_NAME
        //FOOD_NAME
        const val FOOD_ORDER_AMOUNT = "amountOrdered"
        const val TOTAL_FOOD_ORDER_PRICE = "foodOrderPrice"
        //FOOD_PICTURE
        const val ORDER_DATE_TIME = "orderDateTime"
        //USER_ID

        // Users Table
        const val USER_ID = "userID"
        const val EMAIL = "email"
        const val PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createRestaurantTable = ("CREATE TABLE $RESTAURANT_TABLE ($RESTAURANT_ID INTEGER PRIMARY KEY, $RESTAURANT_NAME TEXT, $FOOD_TYPE TEXT, $RESTAURANT_LOCATION TEXT, $RESTAURANT_LOGO TEXT)")

        val createFoodTable = ("CREATE TABLE $FOOD_TABLE ($FOOD_ID INTEGER PRIMARY KEY, $RESTAURANT_ID INTEGER, $RESTAURANT_NAME TEXT, $FOOD_NAME TEXT, $FOOD_PRICE INTEGER, $FOOD_DESCRIPTION TEXT, $FOOD_PICTURE TEXT)")

        val createFoodOrdersTable = ("CREATE TABLE $FOOD_ORDER_TABLE ($FOOD_ORDER_ID INTEGER PRIMARY KEY, $RESTAURANT_NAME TEXT, $FOOD_NAME TEXT, $FOOD_ORDER_AMOUNT INTEGER, $TOTAL_FOOD_ORDER_PRICE INTEGER, $FOOD_PICTURE TEXT, $ORDER_DATE_TIME TEXT, $USER_ID INTEGER)")

        val createUsersTable = ("CREATE TABLE $USERS_TABLE ($USER_ID INTEGER PRIMARY KEY, $EMAIL TEXT, $PASSWORD TEXT)")
        db?.execSQL(createRestaurantTable)
        db?.execSQL(createFoodTable)
        db?.execSQL(createFoodOrdersTable)
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
        contentValues.put(RESTAURANT_NAME, foodOrder.restaurantName)
        contentValues.put(FOOD_NAME, foodOrder.foodName)
        contentValues.put(FOOD_ORDER_AMOUNT, foodOrder.amount)
        contentValues.put(TOTAL_FOOD_ORDER_PRICE, foodOrder.totalPrice)
        contentValues.put(FOOD_PICTURE, foodOrder.foodPicture)
        contentValues.put(ORDER_DATE_TIME, foodOrder.datetime)
        contentValues.put(USER_ID, foodOrder.userID)

        db.insert(FOOD_ORDER_TABLE, null, contentValues)
        db.close()
    }

    //Checked
    fun insertUser(user: User) {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(USER_ID, user.userID)
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
        var logo: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(RESTAURANT_ID))
                name = cursor.getString(cursor.getColumnIndex(RESTAURANT_NAME))
                foodType = cursor.getString(cursor.getColumnIndex(FOOD_TYPE))
                location = cursor.getString(cursor.getColumnIndex(RESTAURANT_LOCATION))
                logo = cursor.getString(cursor.getColumnIndex(RESTAURANT_LOGO))

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

        var foodID: Int
        var restaurantID: Int
        var restaurantName: String
        var name: String
        var price: Int
        var description: String
        var picture: String

        if (cursor.moveToFirst()) {
            do {
                foodID = cursor.getInt(cursor.getColumnIndex(FOOD_ID))
                restaurantID = cursor.getInt(cursor.getColumnIndex(RESTAURANT_ID))
                restaurantName = cursor.getString(cursor.getColumnIndex(RESTAURANT_NAME))
                name = cursor.getString(cursor.getColumnIndex(FOOD_NAME))
                price = cursor.getInt(cursor.getColumnIndex(FOOD_PRICE))
                description = cursor.getString(cursor.getColumnIndex(FOOD_DESCRIPTION))
                picture = cursor.getString(cursor.getColumnIndex(FOOD_PICTURE))

                val food = Food(foodID, restaurantID, restaurantName, name, price, description, picture)
                foodList.add(food)
            } while (cursor.moveToNext())
        }
        return foodList
    }

    @SuppressLint("Range")
    fun getLastFoodId(): Int {
        val selectQuery = "SELECT * FROM $FOOD_TABLE"
        val db = this.readableDatabase

        val cursor: Cursor? = db.rawQuery(selectQuery, null)

        var foodID : Int = -1

        if (cursor != null) {
            if (cursor.moveToLast()) {
                foodID = cursor.getInt(cursor.getColumnIndex(FOOD_ID))
            }
        }
        cursor?.close()
        return foodID
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
    fun getAllFoodOrdersByUser(userID: Int) : ArrayList<FoodOrder> {

        val foodOrderList: ArrayList<FoodOrder> = ArrayList()
        val selectQuery = "SELECT * FROM $FOOD_ORDER_TABLE"
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
        var restaurantName: String
        var foodName: String
        var amount: Int
        var totalPrice: Int
        var foodPicture: String
        var datetime: String
        var user: Int

        if (cursor.moveToFirst()) {
            do {
                foodOrderID = cursor.getInt(cursor.getColumnIndex(FOOD_ORDER_ID))
                restaurantName = cursor.getString(cursor.getColumnIndex(RESTAURANT_NAME))
                foodName = cursor.getString(cursor.getColumnIndex(FOOD_NAME))
                amount = cursor.getInt(cursor.getColumnIndex(FOOD_ORDER_AMOUNT))
                totalPrice = cursor.getInt(cursor.getColumnIndex(TOTAL_FOOD_ORDER_PRICE))
                foodPicture = cursor.getString(cursor.getColumnIndex(FOOD_PICTURE))
                datetime = cursor.getString(cursor.getColumnIndex(ORDER_DATE_TIME))
                user = cursor.getInt(cursor.getColumnIndex(USER_ID))

                val foodOrder = FoodOrder(
                    foodOrderID,
                    restaurantName,
                    foodName,
                    amount,
                    totalPrice,
                    foodPicture,
                    datetime,
                    userID
                )

                if (user == userID) {
                    foodOrderList.add(foodOrder)
                }

            } while (cursor.moveToNext())
        }
        cursor.close()
        return foodOrderList
    }

    @SuppressLint("Range")
    fun userExists(email: String, password: String): User? {
        val selectQuery = "SELECT * FROM $USERS_TABLE"
        val db = this.readableDatabase

        val cursor: Cursor? = db.rawQuery(selectQuery, null)

        var user: User? = null
        var userEmail: String
        var userPassword: String
        var userID: Int

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    userEmail = cursor.getString(cursor.getColumnIndex(EMAIL))
                    userPassword = cursor.getString(cursor.getColumnIndex(PASSWORD))
                    userID = cursor.getInt(cursor.getColumnIndex(USER_ID))

                    if (userEmail == email && userPassword == password) {
                        user = User(userID, userEmail, userPassword)
                    }
                } while (cursor.moveToNext())
            }
        }
        cursor?.close()
        return user
    }

    @SuppressLint("Range")
    fun getLastUserId(): Int {
        val selectQuery = "SELECT * FROM $USERS_TABLE"
        val db = this.readableDatabase

        val cursor: Cursor? = db.rawQuery(selectQuery, null)

        var userID : Int = -1

        if (cursor != null) {
            if (cursor.moveToLast()) {
                userID = cursor.getInt(cursor.getColumnIndex(USER_ID))
            }
        }
        cursor?.close()
        return userID
    }
}