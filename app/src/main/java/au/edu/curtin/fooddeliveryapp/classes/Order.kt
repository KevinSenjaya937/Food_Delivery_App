package au.edu.curtin.fooddeliveryapp.classes

import java.sql.Date
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList

data class Order (
    var orderNumber: Int,
    var totalPrice: Int,
    var time: Time,
    var date: Date,
    var restaurant: Restaurant,
    var userID: Int
)