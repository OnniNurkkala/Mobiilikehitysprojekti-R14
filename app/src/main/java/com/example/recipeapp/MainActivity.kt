package com.example.recipeapp

import android.content.ClipData.Item
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.PopupWindow
import android.widget.ImageButton
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.delete_profile_dialog.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //delete function
        if (intent?.action == "DELETE_RECORD") {
            val intValue = intent.getIntExtra("int_value", 0)
            deleteRecord(intValue)
        }
        //Menu definitions
        val menuButton = findViewById<ImageButton>(R.id.buMenu)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navigationView = findViewById<NavigationView>(R.id.navView)


        //Temporary buttons for navigating in the app
        var homeButton: ImageButton = findViewById(R.id.homeButton)
        var favoriteButton: ImageButton = findViewById(R.id.favoriteButton)
        var profileButton: ImageButton = findViewById(R.id.profileButton)


        //Popup menu definitions
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popup_layout, null)
        val popupWindow = PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true)


        //About button
        val aboutButton = findViewById<ImageButton>(R.id.BU_about)
        aboutButton.setOnClickListener {
            popupWindow.showAtLocation(it.rootView, Gravity.CENTER, 0, 0)
        }
        popupWindow.dismiss()

        //Hides actionbar
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        // Menu button click listener
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
                    val intent = Intent(this, StartScreenActivity::class.java).apply {
                    }
                    startActivity(intent);
                    true
                }
                else -> false
            }
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
        val recipeName = editTextUsername.text.toString()
        val recipe_ing = editTextTextPassword.text.toString()
        val recipe_inst = editTextUsername.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if (recipeName.trim()!="" && recipe_ing.trim()!="" && recipe_inst.trim()!=""){
            val status = databaseHandler.addRecipe(RecipeClass(recipeName,recipe_ing,recipe_inst))
            if (status > -1){
                Toast.makeText(applicationContext, "Tallennettu", Toast.LENGTH_SHORT).show()
            }
        }else {
            Toast.makeText(applicationContext, "Texts can't be blank", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteRecord(userID: Int){
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        val status = databaseHandler.deleteUser(userID)
        if(status > -1) {
            Toast.makeText(applicationContext, "Poistettu", Toast.LENGTH_SHORT).show()
            IdDelete.text.clear()
        } else {
            Toast.makeText(applicationContext, "Virhe tapahtui", Toast.LENGTH_SHORT).show()
        }

    }
}

