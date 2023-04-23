package com.example.recipeapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeDisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_display)

        // Get the recipe information from the intent extras
        val recipeName = intent.getStringExtra("recipeName")
        val ingredients = intent.getStringExtra("ingredients")
        val instructions = intent.getStringExtra("instructions")

        // Populate the TextViews or other UI elements with the recipe information
        findViewById<TextView>(R.id.recipe_name).text = recipeName
        findViewById<TextView>(R.id.recipe_ingredients).text = ingredients
        findViewById<TextView>(R.id.recipe_steps).text = instructions
    }
}