package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tunaText = findViewById<TextView>(R.id.tunaText)
        val nachoText = findViewById<TextView>(R.id.nachoText)
        val noodleText = findViewById<TextView>(R.id.noodleText)
        val omeletteText = findViewById<TextView>(R.id.omeletteText)

        tunaText.setOnClickListener {
            val intent = RecipeIntentFactory.createIntent(this, "Herkullinen tonnikala pasta")
            intent.putExtra("recipe_ingredients", getString(R.string.recipe_tuna_ingredients))
            intent.putExtra("recipe_steps", getString(R.string.recipe_tuna_steps))
            startActivity(intent)
        }

        nachoText.setOnClickListener {
            val intent = RecipeIntentFactory.createIntent(this, "Jauheliha-nachopaistos")
            intent.putExtra("recipe_ingredients", getString(R.string.recipe_nacho_ingredients))
            intent.putExtra("recipe_steps", getString(R.string.recipe_nacho_steps))
            startActivity(intent)
        }

        noodleText.setOnClickListener {
            val intent = RecipeIntentFactory.createIntent(this, "Broileri-nuudelikeitto")
            intent.putExtra("recipe_ingredients", getString(R.string.recipe_broiler_ingredients))
            intent.putExtra("recipe_steps", getString(R.string.recipe_broiler_steps))
            startActivity(intent)
        }

        omeletteText.setOnClickListener {
            val intent = RecipeIntentFactory.createIntent(this, "Ruokaisa munakas")
            intent.putExtra("recipe_ingredients", getString(R.string.recipe_omelette_ingredients))
            intent.putExtra("recipe_steps", getString(R.string.recipe_omelette_steps))
            startActivity(intent)
        }
    }
}