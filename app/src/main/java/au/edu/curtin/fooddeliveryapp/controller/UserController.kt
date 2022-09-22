package au.edu.curtin.fooddeliveryapp.controller

import au.edu.curtin.fooddeliveryapp.classes.FoodOrder
import au.edu.curtin.fooddeliveryapp.classes.User
import au.edu.curtin.fooddeliveryapp.database.DBHelper

class UserController(database: DBHelper) {

    private var db = database
    private var userLoggedIn = false
    private lateinit var currentUser: User
    private var statusText = ""

    fun registerUser(email: String, password: String): Boolean {
        val lastUserID = db.getLastUserId()
        val user = User(lastUserID+1, email, password)
        db.insertUser(user)

        this.currentUser = user
        this.userLoggedIn = true

        this.statusText = "User successfully registered. User logged in."

        return true
    }

    fun loginUser(email: String, password: String): Boolean {

        val user = db.userExists(email, password)

        return if (user != null) {
            this.userLoggedIn = true
            this.currentUser = user
            this.statusText =  "User successfully logged in."

            true
        } else {
            this.userLoggedIn = false
            this.statusText = "User login failed."

            false
        }
    }

    fun userLoggedIn(): Boolean {
        return userLoggedIn
    }

    fun getStatusText(): String {
        return statusText
    }

    fun getUserPastOrders(): List<FoodOrder> {
        if (userLoggedIn) {
            return db.getAllFoodOrdersByUser(currentUser.userID)
        } else {
            return ArrayList<FoodOrder>()
        }
    }

}