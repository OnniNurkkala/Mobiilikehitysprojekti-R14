package com.example.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        // Get references to the views in the activity
        val recipeNameTextView = findViewById<TextView>(R.id.recipe_name)
        val recipeIngredientsTextView = findViewById<TextView>(R.id.recipe_ingredients)
        val recipeStepsTextView = findViewById<TextView>(R.id.recipe_steps)

        // Get the recipe name, ingredients, and steps from the intent that started this activity
        val recipeName = intent.getStringExtra("recipe_name")
        val recipeIngredients = intent.getStringExtra("recipe_ingredients")
        val recipeSteps = intent.getStringExtra("recipe_steps")

        // Set the text of the views to the recipe name, ingredients, and steps
        recipeNameTextView.text = recipeName
        recipeIngredientsTextView.text = recipeIngredients
        recipeStepsTextView.text = recipeSteps
    }
}