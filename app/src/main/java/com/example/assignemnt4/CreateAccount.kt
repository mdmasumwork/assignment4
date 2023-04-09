package com.example.assignemnt4

import android.app.Activity
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.assignemnt4.databinding.ActivityCreateAccountBinding
import com.example.assignemnt4.dataclass.User

class CreateAccount : AppCompatActivity() {
    private lateinit var binding: ActivityCreateAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun createAccountHandler(view: View) {
        var firstName: String = binding.firstName.text.toString()
        var lastName: String = binding.lastName.text.toString()
        var email: String = binding.emailAddress.text.toString()
        var password: String = binding.password.text.toString()

        var user: User = User(firstName, lastName, email, password)

        var resultIntent = intent
        resultIntent.putExtra("user", user)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}