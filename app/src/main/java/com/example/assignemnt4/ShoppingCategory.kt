package com.example.assignemnt4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.assignemnt4.databinding.ActivityShoppingCategoryBinding

class ShoppingCategory : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent: Intent = getIntent()
        var email: String? = intent.getStringExtra("email")
        println(email)
        binding.welcomeText.text = "Welcome $email"
    }

    fun imageClick(view: View) {
        when(view.id) {
            R.id.imageView -> Toast.makeText(this, "Book is clicked", Toast.LENGTH_LONG).show()
            R.id.imageView3 -> Toast.makeText(this, "Clothing is clicked", Toast.LENGTH_LONG).show()
            R.id.imageView4 -> Toast.makeText(this, "Organic Foods is clicked", Toast.LENGTH_LONG).show()
            R.id.imageView6 -> Toast.makeText(this, "Medicines is clicked", Toast.LENGTH_LONG).show()
        }
    }
}