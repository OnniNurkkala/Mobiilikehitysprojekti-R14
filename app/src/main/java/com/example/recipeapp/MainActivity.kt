package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_login.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Menu definitions
        val menuButton = findViewById<ImageButton>(R.id.buMenu)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navigationView = findViewById<NavigationView>(R.id.navView)
        //Temporary buttons for navigating in the app
        var homeButton: ImageButton = findViewById(R.id.homeButton)
        var favoriteButton: ImageButton = findViewById(R.id.favoriteButton)
        var profileButton: ImageButton = findViewById(R.id.profileButton)


        //Hides actionbar
        if (supportActionBar != null) {
           supportActionBar!!.hide()
        }

        // Menu button click listener
        menuButton.setOnClickListener {
            drawerLayout.openDrawer(navigationView)
        }


        //Temporary buttons for navigating in the app
        homeButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java).apply {
            }
            startActivity(intent);
        }
        favoriteButton.setOnClickListener {
            val intent = Intent(this, StartScreenActivity::class.java).apply {
            }
            startActivity(intent);
        }
        profileButton.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java).apply {
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

    fun saveRecord(view: View){
        val name = editTextUsername.text.toString()
        val pass = editTextTextPassword.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if (name.trim()!="" && pass.trim()!=""){
            val status = databaseHandler.addUser(EmpModelClass(name,pass))
            if(status > -1){
                Toast.makeText(applicationContext, "Saved!", Toast.LENGTH_SHORT).show()
                editTextUsername.text.clear()
            }
        }else {
            Toast.makeText(applicationContext, "Username or password can't be blank!", Toast.LENGTH_SHORT).show()
        }

    }

    fun saveRecipe(view: View){
        val recipename = editTextUsername.text.toString()
        val recipeinfo = editTextTextPassword.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
    }

    fun deleteRecord(view: View){
        val userID = editTextUserID.text.toString().toInt()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        val status = databaseHandler.deleteUser(userID)
        if(status > -1) {
            Toast.makeText(applicationContext, "Record deleted successfully!", Toast.LENGTH_SHORT).show()
            editTextUserID.text.clear()
        } else {
            Toast.makeText(applicationContext, "Error deleting record!", Toast.LENGTH_SHORT).show()
        }
    }


}

