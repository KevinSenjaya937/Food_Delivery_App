package au.edu.curtin.fooddeliveryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import au.edu.curtin.fooddeliveryapp.classes.Restaurant
import au.edu.curtin.fooddeliveryapp.fragments.AccountFragment
import au.edu.curtin.fooddeliveryapp.fragments.BrowseFragment
import au.edu.curtin.fooddeliveryapp.fragments.CartFragment
import au.edu.curtin.fooddeliveryapp.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val data = ArrayList<Restaurant>()

    fun importData() {
        data.add(Restaurant("kfc", "Fast Food", "Bentley", R.drawable.kfc_logo))
        data.add(Restaurant("kfc", "Fast Food", "Bentley", R.drawable.kfc_logo))
        data.add(Restaurant("kfc", "Fast Food", "Bentley", R.drawable.kfc_logo))
        data.add(Restaurant("kfc", "Fast Food", "Bentley", R.drawable.kfc_logo))
        data.add(Restaurant("kfc", "Fast Food", "Bentley", R.drawable.kfc_logo))
        data.add(Restaurant("kfc", "Fast Food", "Bentley", R.drawable.kfc_logo))
        data.add(Restaurant("kfc", "Fast Food", "Bentley", R.drawable.kfc_logo))
        data.add(Restaurant("kfc", "Fast Food", "Bentley", R.drawable.kfc_logo))
        data.add(Restaurant("kfc", "Fast Food", "Bentley", R.drawable.kfc_logo))
        data.add(Restaurant("kfc", "Fast Food", "Bentley", R.drawable.kfc_logo))
        data.add(Restaurant("kfc", "Fast Food", "Bentley", R.drawable.kfc_logo))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        importData()

        val homeFragment = HomeFragment()
        val cartFragment = CartFragment()
        val acctFragment = AccountFragment()
        val restaurantsFragment = BrowseFragment(data)

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

    private fun replaceFragment(restaurantsFragment: BrowseFragment) {
        importData()
        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.replace(R.id.scrollingFragment,restaurantsFragment)
        fragmentTransaction.commit()
    }
}