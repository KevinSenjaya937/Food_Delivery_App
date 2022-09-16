package au.edu.curtin.fooddeliveryapp.classes

import java.sql.Date
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList

data class Order (
    var orderNumber: Int,
    var totalPrice: Int,
    var time: String,
    var date: String,
    var restaurantID: Int,
    var userID: Int
)