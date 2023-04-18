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
        // PopUpMenu button click listener
        settingsButton.setOnClickListener {
            showPopUpMenu(settingsButton)
        }
        // Profile name edit button listener
        userNameEditButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Change username")
            val input = EditText(this)
            input.setText(userNameTextView.text)
            builder.setView(input)
            builder.setPositiveButton("OK") { dialog, which ->
                userNameTextView.text = input.text.toString()
                Toast.makeText(this, "Username changed", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }
            builder.show()
        }
        // Profile password edit button listener
        passWordEditButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Enter new password")
            val input = EditText(this)
            input.setText("")
            builder.setView(input)
            builder.setPositiveButton("OK") { dialog, which ->
                    passWordTextView.text = input.text.toString()
                    Toast.makeText(this, "Password changed", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Cancel") { dialog, which ->
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
