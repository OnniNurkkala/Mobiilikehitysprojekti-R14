package com.example.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val recipeNameTextView = findViewById<TextView>(R.id.recipe_name)
        val recipeIngredientsTextView = findViewById<TextView>(R.id.recipe_ingredients)
        val recipeStepsTextView = findViewById<TextView>(R.id.recipe_steps)

        val recipeName = intent.getStringExtra("recipe_name")
        val recipeIngredients = intent.getStringExtra("recipe_ingredients")
        val recipeSteps = intent.getStringExtra("recipe_steps")

        recipeNameTextView.text = recipeName
        recipeIngredientsTextView.text = recipeIngredients
        recipeStepsTextView.text = recipeSteps
    }
}