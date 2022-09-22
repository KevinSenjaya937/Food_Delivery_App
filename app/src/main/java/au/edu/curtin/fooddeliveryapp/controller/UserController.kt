package au.edu.curtin.fooddeliveryapp.controller

import au.edu.curtin.fooddeliveryapp.classes.User
import au.edu.curtin.fooddeliveryapp.database.DBHelper

class UserController(database: DBHelper) {

    private var db = database
    private var userLoggedIn = false

    fun registerUser(email: String, password: String) {
        var lastUserID = db.getLastUserId()
        val user = User(lastUserID+1, email, password)
        db.insertUser(user)
    }

    fun loginUser(email: String, password: String) {

        if (db.userExists(email, password)) {
            this.userLoggedIn = true
        }
    }

    fun userLoggedIn(): Boolean {
        return userLoggedIn
    }

}