package com.example.recipeapp

import android.content.Context
import android.content.Intent

// This is a companion object, which is similar to a static method in Java
// It allows you to define methods that can be called on the class itself, rather than an instance of the class
class RecipeIntentFactory {
    companion object {
        // This is a method that creates and returns an Intent object for a recipe activity
        fun createIntent(context: Context, recipeName: String): Intent {
            // Create a new Intent object, specifying the context and the class of the activity to launch
            val intent = Intent(context, RecipeActivity::class.java)
            // Add the recipe name as an extra to the Intent object
            intent.putExtra("recipe_name", recipeName)
            // Return the Intent object
            return intent
        }
    }
}