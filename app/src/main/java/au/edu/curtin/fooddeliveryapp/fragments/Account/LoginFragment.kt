package au.edu.curtin.fooddeliveryapp.fragments.Account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.controller.UserController

class LoginFragment(private val controller: UserController) : Fragment() {

    private lateinit var loginBtn: Button
    private lateinit var registerSwitch: androidx.appcompat.widget.SwitchCompat
    private lateinit var emailBox: EditText
    private lateinit var passwordBox: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginBtn = view.findViewById(R.id.loginBtn)
        registerSwitch = view.findViewById(R.id.registerLoginSwitch)
        emailBox = view.findViewById(R.id.editTextTextEmailAddress)
        passwordBox = view.findViewById(R.id.editTextTextPassword)

        registerSwitch.setOnClickListener {
            if (registerSwitch.isChecked) {
                loginBtn.text = "Register"
                registerSwitch.text = "Register"

                loginBtn.setOnClickListener {
                    if (emailBox.text.isNotEmpty() && passwordBox.text.isNotEmpty()) {
                        controller.registerUser(emailBox.text.toString(), passwordBox.text.toString())
                    }
                }
            } else {
                loginBtn.text = "Login"
                registerSwitch.text = "Login"

                loginBtn.setOnClickListener {
                    if (emailBox.text.isNotEmpty() && passwordBox.text.isNotEmpty()) {
                        controller.loginUser(emailBox.text.toString(), passwordBox.text.toString())
                    }
                }
            }
        }
    }


}