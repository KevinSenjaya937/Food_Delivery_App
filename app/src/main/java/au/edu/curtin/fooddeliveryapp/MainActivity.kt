package au.edu.curtin.fooddeliveryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import au.edu.curtin.fooddeliveryapp.classes.Restaurant
import au.edu.curtin.fooddeliveryapp.controller.FoodController
import au.edu.curtin.fooddeliveryapp.controller.RestaurantController
import au.edu.curtin.fooddeliveryapp.controller.UserController
import au.edu.curtin.fooddeliveryapp.database.DBHelper
import au.edu.curtin.fooddeliveryapp.fragments.Account.AccountFragment
import au.edu.curtin.fooddeliveryapp.fragments.Browse.BrowseFragment
import au.edu.curtin.fooddeliveryapp.fragments.Cart.CartFragment
import au.edu.curtin.fooddeliveryapp.fragments.Home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    val data = ArrayList<Restaurant>()
    private val database = DBHelper(this)
    private val restaurantController = RestaurantController(database)
    private val foodController = FoodController(database)
    private val userController = UserController(database)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val restaurantData = InputStreamReader(assets.open("restaurants.csv"))
        val foodData = InputStreamReader(assets.open("food.csv"))
        val homeFragment = HomeFragment(foodController)
        val cartFragment = CartFragment(foodController, userController)
        val acctFragment = AccountFragment(userController, foodController)
        val restaurantsFragment = BrowseFragment(restaurantController, foodController)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        restaurantController.load(restaurantData)
        foodController.load(foodData)

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
                    badgeClear(R.id.nav_cart)
                }
                R.id.nav_account -> {
                    setCurrentFragment(acctFragment)
                    badgeClear(R.id.nav_account)
                }
            }
            true
        }
    }

    fun badgeSetup(id: Int, alerts: Int) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        val badge = bottomNav.getOrCreateBadge(id)
        badge.isVisible = true
        badge.number = alerts
    }

    private fun badgeClear(id: Int) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        val badgeDrawable = bottomNav.getBadge(id)
        if (badgeDrawable != null) {
            badgeDrawable.isVisible = false
            badgeDrawable.clearNumber()
        }
    }

    private fun setCurrentFragment(Fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.scrollingFragment, Fragment)
            commit()
    }

    fun clickHomeBtn() {
        val homeBtn = findViewById<BottomNavigationItemView>(R.id.nav_home)
        homeBtn.performClick()
    }
}