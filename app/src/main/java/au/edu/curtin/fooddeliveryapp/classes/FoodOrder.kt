package au.edu.curtin.fooddeliveryapp.classes

data class FoodOrder (
    var orderNumber: Int,
    var foodID: Int,
    var amount: Int,
    var totalPrice: Int
)