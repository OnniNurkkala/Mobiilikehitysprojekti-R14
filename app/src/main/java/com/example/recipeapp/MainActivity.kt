package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)

        textView.setOnClickListener {
            val intent = Intent(this, RecipeActivity::class.java)
            intent.putExtra("recipe_name", "Herkullinen tonnikala pasta")
            startActivity(intent)
        }
    }
}