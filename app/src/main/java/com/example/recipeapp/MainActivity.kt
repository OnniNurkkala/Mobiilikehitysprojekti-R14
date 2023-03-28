package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Menu definitions
        val menuButton = findViewById<ImageButton>(R.id.buMenu)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navigationView = findViewById<NavigationView>(R.id.navView)
        //Temporary buttons for navigating in the app
        var imageButton: ImageButton = findViewById(R.id.imageButton)
        var imageButton2: ImageButton = findViewById(R.id.imageButton2)


        //Hides actionbar
        if (supportActionBar != null) {
           supportActionBar!!.hide()
        }

        // Menu button click listener
        menuButton.setOnClickListener {
            drawerLayout.openDrawer(navigationView)
        }


        //Temporary buttons for navigating in the app
        imageButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java).apply {
            }
            startActivity(intent);
        }
        imageButton2.setOnClickListener {
            val intent = Intent(this, StartScreenActivity::class.java).apply {
            }
            startActivity(intent);
        }
    }

    //Inflate the hamburger menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    //Click event handlers for hamburger menu items
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favoriteItem -> {
                Toast.makeText(this, "Favorite selected", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LoginActivity::class.java).apply {
                }
                startActivity(intent);
                return true
            }
            R.id.userItem -> {
                Toast.makeText(this, "User selected", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, StartScreenActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.homeItem -> {
                Toast.makeText(this, "Home selected", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, StartScreenActivity::class.java).apply {
                }
                startActivity(intent);
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}

