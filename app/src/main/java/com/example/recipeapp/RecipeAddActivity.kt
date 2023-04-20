package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class RecipeAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_add)

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
}