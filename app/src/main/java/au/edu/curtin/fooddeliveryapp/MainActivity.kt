package au.edu.curtin.fooddeliveryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val data = ArrayList<Restaurant>()

    fun importData() {
        data.add(Restaurant("kfc", "0123", "Address", R.drawable.kfc_logo_background))
        data.add(Restaurant("kfc", "0123", "Address", R.drawable.kfc_logo_background))
        data.add(Restaurant("kfc", "0123", "Address", R.drawable.kfc_logo_background))
        data.add(Restaurant("kfc", "0123", "Address", R.drawable.kfc_logo_background))
        data.add(Restaurant("kfc", "0123", "Address", R.drawable.kfc_logo_background))
        data.add(Restaurant("kfc", "0123", "Address", R.drawable.kfc_logo_background))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(RestaurantsFragment(data))
    }

    private fun replaceFragment(restaurantsFragment: RestaurantsFragment) {
        importData()
        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.replace(R.id.daily_deals,restaurantsFragment)
        fragmentTransaction.commit()
    }
}