package au.edu.curtin.fooddeliveryapp.classes

data class Food (
    var foodID: Int,
    var restaurantID: Int,
    var restaurantName: String,
    var name: String,
    var price: Int,
    var description: String,
    var picture: String
)


