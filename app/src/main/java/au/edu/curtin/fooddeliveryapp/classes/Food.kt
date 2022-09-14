package au.edu.curtin.fooddeliveryapp.classes

data class Food (
    var id: Int,
    var name: String,
    var price: Int,
    var description: String,
    var picture: ByteArray
)

