package au.edu.curtin.fooddeliveryapp.fragments.Account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.controller.UserController

class AccountFragment(private val userController: UserController) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

}