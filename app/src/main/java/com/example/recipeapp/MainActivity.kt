package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_login.*
import android.view.*
import android.widget.PopupWindow
import android.widget.ImageButton
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_login.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //delete function
        if (intent?.action == "DELETE_RECORD") {
            val intValue = intent.getIntExtra("int_value", 0)
            //deleteRecord(intValue)
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

        val aboutButton = findViewById<ImageButton>(R.id.BU_about)
        aboutButton.setOnClickListener {
            popupWindow.showAtLocation(it.rootView, Gravity.CENTER, 0, 0)
        }
        popupWindow.dismiss()
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
        val databaseHandler: DBhelper = DBhelper(this)
        if (name.trim()!="" && pass.trim()!=""){
            val status = databaseHandler.insertdata(EmpModelClass(name,pass))
            if(status > -1){
                Toast.makeText(applicationContext, "Saved!", Toast.LENGTH_SHORT).show()
                editTextUsername.text.clear()
            }
        }else {
            Toast.makeText(applicationContext, "Username or password can't be blank!", Toast.LENGTH_SHORT).show()
        }
    }
}






