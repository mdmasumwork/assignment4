package com.example.assignemnt4

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.example.assignemnt4.databinding.ActivityMainBinding
import com.example.assignemnt4.dataclass.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var userList: ArrayList<User> = arrayListOf(
        User("Rana", "Hossain", "rana@                                                                                  miu.edu", "pass1"),
        User("Md", "Masum", "md.masum@miu.edu", "pass1"),
        User("Kafi", "Rashid", "kafi@miu.edu", "pass1"),
        User("Yadab", "Sutradhar", "yadab@miu.edu", "pass1"),
        User("Priyanka", "Mondal", "priyanka@miu.edu", "pass1"),
    )

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var resultContracts = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                var resultIntent: Intent? = result.data
                var user: User = resultIntent?.getSerializableExtra("user") as User
                userList.add(user)
            }
        }

        binding.createNewAccountButton.setOnClickListener() {
            var intent = Intent(this, CreateAccount::class.java)
            resultContracts.launch(intent)
        }
    }

    fun signIn(view: View) {
        var email: String = binding.emailField.text.toString()
        var password: String = binding.passwordField.text.toString()
        var loggedIn: Boolean = false

        userList.forEach {
            if (it.email.equals(email) && it.password.equals(password)) {
                loggedIn = true
            }
        }

        if (loggedIn) {
            var intent: Intent = Intent(this, ShoppingCategory::class.java)
            intent.putExtra("email", email)

            startActivity(intent)
        } else {
            binding.errorMessage.text = getString(R.string.login_error)
        }
    }

    fun forgotPassword(view: View) {
        var email: String = binding.emailField.text.toString()
        var password: String? = null
        userList.forEach { user ->
            if (user.email.equals(email))
                password = user.password
        }

        if (password != null) {
            var intent: Intent = Intent().apply {
                action = Intent.ACTION_SENDTO
                data = Uri.parse("mailto:")
                type = "text/html"
                putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                putExtra(Intent.EXTRA_SUBJECT, "Password of MIU portal")
                putExtra(Intent.EXTRA_TEXT, "Your password is: $password")
            }
            startActivity(intent)
        } else {
            binding.errorMessage.text = getString(R.string.forget_pass_message)
        }
    }
}