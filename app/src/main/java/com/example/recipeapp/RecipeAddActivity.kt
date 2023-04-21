package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_recipe_add.*

class RecipeAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_add)

        /*recipeNameTextView = findViewById(R.id.name)
        recipeIngredientsTextView = findViewById(R.id.ing)
        recipeInstructionsTextView = findViewById(R.id.inst)

        val recipeId = intent.getIntExtra("recipe_id", 2)
        val dbHandler = DBhelper(this)
        val recipe = dbHandler.getRecipeById(recipeId)

        recipeNameTextView.text = recipe?.recipeName
        recipeIngredientsTextView.text = recipe?.recipe_ing
        recipeInstructionsTextView.text = recipe?.recipe_inst*/

        // Hides action bar
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        //HamburgerMenu definitions
        val menuButton = findViewById<ImageButton>(R.id.buMenuRecipe)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navigationView = findViewById<NavigationView>(R.id.navView)

        // HamburgerMenu button click listener
        menuButton.setOnClickListener {
            drawerLayout.openDrawer(navigationView)
        }

        // Menu item click listeners
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.userItem -> {
                    // Handle click for first menu item
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.favoriteItem -> {
                    val intent = Intent(this, LoginActivity::class.java).apply {
                    }
                    startActivity(intent);
                    true
                }
                R.id.homeItem -> {
                    val intent = Intent(this, MainActivity::class.java).apply {
                    }
                    startActivity(intent);
                    true
                }
                else -> false
            }
        }
    }

    fun saveRecipe(view: View){
        val recipeName = editTextRecipeName.text.toString()
        val recipe_ing = editTextIngredient.text.toString()
        val recipe_inst = editTextInstructions.text.toString()
        val databaseHandler: DBhelper = DBhelper(this)
        if (recipeName.trim()!="" && recipe_ing.trim()!="" && recipe_inst.trim()!=""){
            val status = databaseHandler.addRecipe(RecipeClass(-1, recipeName,recipe_ing,recipe_inst))
            if (status > -1){
                Toast.makeText(applicationContext, "Tallennettu", Toast.LENGTH_SHORT).show()
            }
        }else {
            Toast.makeText(applicationContext, "Texts can't be blank", Toast.LENGTH_SHORT).show()
        }
    }
}