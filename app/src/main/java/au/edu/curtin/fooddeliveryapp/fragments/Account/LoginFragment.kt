package au.edu.curtin.fooddeliveryapp.fragments.Account

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import au.edu.curtin.fooddeliveryapp.MainActivity
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.controller.FoodController
import au.edu.curtin.fooddeliveryapp.controller.UserController

class LoginFragment(private val controller: UserController,
                    private val foodController: FoodController) : Fragment() {

    private lateinit var loginBtn: Button
    private lateinit var registerSwitch: androidx.appcompat.widget.SwitchCompat
    private lateinit var emailBox: EditText
    private lateinit var passwordBox: EditText
    private lateinit var helperText: TextView
    private var numOfOrder = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginBtn = view.findViewById(R.id.loginBtn)
        registerSwitch = view.findViewById(R.id.registerLoginSwitch)
        emailBox = view.findViewById(R.id.editTextTextEmailAddress)
        passwordBox = view.findViewById(R.id.editTextTextPassword)
        helperText = view.findViewById(R.id.loginHelperText)

        switchLoginBtnAction(false)

        registerSwitch.setOnClickListener {
            if (registerSwitch.isChecked) {
                loginBtn.text = "Register"
                helperText.text = "Register"
                switchLoginBtnAction(true)

            } else {
                loginBtn.text = "Login"
                helperText.text = "Login"
                switchLoginBtnAction(false)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun switchLoginBtnAction(switched: Boolean) {

        if (switched) {
            // Register Function - True
            loginBtn.setOnClickListener {
                if (emailBox.text.isNotEmpty() && passwordBox.text.isNotEmpty()) {
                    if (controller.registerUser(emailBox.text.toString(), passwordBox.text.toString())) {
                        foodController.checkOut(controller.getUserID())
                        Toast.makeText(context, "User Successfully Registered. Order Complete.", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).badgeSetup(R.id.nav_account, numOfOrder)
                    }
                }
            }
        } else {
            // Login Function - False
            loginBtn.setOnClickListener {
                if (emailBox.text.isNotEmpty() && passwordBox.text.isNotEmpty()) {
                    if (controller.loginUser(emailBox.text.toString(), passwordBox.text.toString())) {
                        foodController.checkOut(controller.getUserID())
                        Toast.makeText(context, "User Login Successful. Order Complete.", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).badgeSetup(R.id.nav_account, numOfOrder)
                    } else {
                        Toast.makeText(context, "User Login Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


}