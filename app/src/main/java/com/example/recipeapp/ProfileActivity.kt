package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.delete_profile_dialog.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        //HamburgerMenu definitions
        val menuButton = findViewById<ImageButton>(R.id.buMenuProfile)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navigationView = findViewById<NavigationView>(R.id.navView)
        //PopUpMenu definitions
        val settingsButton = findViewById<ImageButton>(R.id.buSettings)
        //Profile text edit definitions
        val userNameEditButton = findViewById<ImageButton>(R.id.buEditUserName)
        val userNameTextView = findViewById<TextView>(R.id.textViewUsername)
        val passWordEditButton = findViewById<ImageButton>(R.id.buEditPassword)
        val passWordTextView = findViewById<TextView>(R.id.textViewPassword)


        //Hides actionbar
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

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

        // PopUpMenu button click listener
        settingsButton.setOnClickListener {
            showPopUpMenu(settingsButton)
        }
        // Profile name edit button listener
        userNameEditButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Vaihda käyttäjänimi")
            val input = EditText(this)
            input.setText(userNameTextView.text)
            builder.setView(input)
            builder.setPositiveButton("OK") { dialog, which ->
                userNameTextView.text = input.text.toString()
                Toast.makeText(this, "Käyttäjänimi vaihdettu", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Peruuta") { dialog, which ->
                dialog.cancel()
            }
            builder.show()
        }
        // Profile password edit button listener
        passWordEditButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Anna uusi salasana")
            val input = EditText(this)
            input.setText("")
            builder.setView(input)
            builder.setPositiveButton("OK") { dialog, which ->
                    passWordTextView.text = input.text.toString()
                    Toast.makeText(this, "Salasana vaihdettu", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Peruuta") { dialog, which ->
                dialog.cancel()
            }
            builder.show()
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
                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setView(R.layout.delete_profile_dialog)
                    dialogBuilder.setTitle("Delete profile")
                    dialogBuilder.setMessage("Are you sure you want to delete this profile?")
                    dialogBuilder.setPositiveButton("OK") { dialog, which ->
                        // Handle OK button click here
                        val deleteId = "1"
                        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
                        /*val status = databaseHandler.deleteUser(EmpModelClass(Integer.parseInt(deleteId),"",""))
                        if(status > -1){
                            Toast.makeText(applicationContext,"record deleted",Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(applicationContext,"no work",Toast.LENGTH_LONG).show()
                        }*/

                        val userInput = findViewById<EditText>(R.id.IdDelete).text.toString()
                        val intValue = userInput.toInt()
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("int_value", intValue)
                        intent.setClass(this, MainActivity::class.java)
                        intent.action = "DELETE_RECORD"
                        startActivity(intent)
                    }
                    dialogBuilder.setNegativeButton("CANCEL") { dialog, which ->
                        // Handle CANCEL button click here
                        dialog.cancel()
                    }
                    val dialog = dialogBuilder.create()
                    dialog.show()
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}
