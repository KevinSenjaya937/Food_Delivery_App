package au.edu.curtin.fooddeliveryapp.classes

import android.graphics.drawable.Drawable

data class Restaurant(
    var id: Int,
    var name: String,
    var foodType: String,
    var location: String,
    var logo: String,
)