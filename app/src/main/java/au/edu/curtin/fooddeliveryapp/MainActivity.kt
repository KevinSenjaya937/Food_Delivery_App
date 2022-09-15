package au.edu.curtin.fooddeliveryapp

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import au.edu.curtin.fooddeliveryapp.classes.Restaurant
import au.edu.curtin.fooddeliveryapp.controller.FoodController
import au.edu.curtin.fooddeliveryapp.controller.RestaurantController
import au.edu.curtin.fooddeliveryapp.database.DBHelper
import au.edu.curtin.fooddeliveryapp.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val data = ArrayList<Restaurant>()
    private val database = DBHelper(this)
    val restaurantController = RestaurantController(database)
    val foodController = FoodController(database)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val homeFragment = HomeFragment()
        val cartFragment = CartFragment()
        val acctFragment = AccountFragment()
        val restaurantsFragment = BrowseFragment(restaurantController, foodController)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        setCurrentFragment(homeFragment)

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    setCurrentFragment(homeFragment)
                }
                R.id.nav_browse -> {
                    setCurrentFragment(restaurantsFragment)
                }
                R.id.nav_cart -> {
                    setCurrentFragment(cartFragment)
                }
                R.id.nav_account -> {
                    setCurrentFragment(acctFragment)
                }
            }
            true
        }
    }



    private fun setCurrentFragment(Fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.scrollingFragment, Fragment)
            commit()
    }
}