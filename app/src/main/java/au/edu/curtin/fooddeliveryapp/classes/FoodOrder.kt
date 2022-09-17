package au.edu.curtin.fooddeliveryapp.classes

data class FoodOrder (
    var orderNumber: Int,
    var restaurantName: String,
    var foodName: String,
    var amount: Int,
    var totalPrice: Int,
    var foodPicture: Int
)