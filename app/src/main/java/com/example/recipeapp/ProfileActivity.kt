package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        //Menu definitions
        val menuButton = findViewById<ImageButton>(R.id.buMenuProfile)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navigationView = findViewById<NavigationView>(R.id.navView)
        //PopUpMenu definitions
        val settingsButton = findViewById<ImageButton>(R.id.buSettings)

        //Hides actionbar
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        // HamburgerMenu button click listener
        menuButton.setOnClickListener {
            drawerLayout.openDrawer(navigationView)
        }
        // PopUpMenu button click listener
        settingsButton.setOnClickListener {
            showPopUpMenu(settingsButton)
        }

    }

    // PopUpMenu function
    private fun showPopUpMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.pop_up_menu)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.deleteprofile -> {
                    // Handle menu item 1 click
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
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
