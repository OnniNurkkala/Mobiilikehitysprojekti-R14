package com.example.recipeapp

import android.content.Context
import android.content.Intent

class RecipeIntentFactory {
    companion object {
        fun createIntent(context: Context, recipeName: String): Intent {
            val intent = Intent(context, RecipeActivity::class.java)
            intent.putExtra("recipe_name", recipeName)
            return intent
        }
    }
}