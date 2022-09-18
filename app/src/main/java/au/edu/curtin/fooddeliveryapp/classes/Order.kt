package au.edu.curtin.fooddeliveryapp.classes

import java.sql.Date
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList

data class Order (
    var orderID: Int,
    var restaurantName: String,
    var restaurantID: Int,
    var amountOfItems: Int,
    var totalPrice: Int,
    var time: String,
    var date: String,
    var restaurantLogo: Int,
    var userID: Int
)