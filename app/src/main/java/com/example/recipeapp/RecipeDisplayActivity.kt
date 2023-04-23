package com.example.recipeapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_recipe_display.*

class RecipeDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_display)

        val recipeName = intent.getStringExtra("recipeName")
        val ingredients = intent.getStringExtra("ingredients")
        val instructions = intent.getStringExtra("instructions")

        recipeNameTextView.text = recipeName
        ingredientsTextView.text = ingredients
        instructionsTextView.text = instructions
    }
}