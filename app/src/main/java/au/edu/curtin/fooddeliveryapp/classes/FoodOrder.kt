package au.edu.curtin.fooddeliveryapp.classes

data class FoodOrder (
    var foodOrderID: Int,
    var restaurantName: String,
    var foodName: String,
    var amount: Int,
    var totalPrice: Int,
    var foodPicture: Int,
    var date: String,
    var time: String,
    var userID: Int,


)