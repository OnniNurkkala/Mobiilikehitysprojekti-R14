package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import android.view.*
import android.widget.PopupWindow
import android.widget.ImageButton
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.delete_profile_dialog.*
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the TextView elements in the layout and store them in variables
        val tunaText = findViewById<TextView>(R.id.tunaText)
        val nachoText = findViewById<TextView>(R.id.nachoText)
        val noodleText = findViewById<TextView>(R.id.noodleText)
        val omeletteText = findViewById<TextView>(R.id.omeletteText)

        // Set click listeners for each TextView
        tunaText.setOnClickListener {
            // Create a new intent using a factory method and pass in the recipe name, ingredients, and steps as extras
            val intent = RecipeIntentFactory.createIntent(this, "Herkullinen tonnikala pasta")
            intent.putExtra("recipe_ingredients", getString(R.string.recipe_tuna_ingredients))
            intent.putExtra("recipe_steps", getString(R.string.recipe_tuna_steps))
            // Start the RecipeActivity with the new intent
            startActivity(intent)
        }

        nachoText.setOnClickListener {
            // Create a new intent using a factory method and pass in the recipe name, ingredients, and steps as extras
            val intent = RecipeIntentFactory.createIntent(this, "Jauheliha-nachopaistos")
            intent.putExtra("recipe_ingredients", getString(R.string.recipe_nacho_ingredients))
            intent.putExtra("recipe_steps", getString(R.string.recipe_nacho_steps))
            // Start the RecipeActivity with the new intent
            startActivity(intent)
        }

        noodleText.setOnClickListener {
            // Create a new intent using a factory method and pass in the recipe name, ingredients, and steps as extras
            val intent = RecipeIntentFactory.createIntent(this, "Broileri-nuudelikeitto")
            intent.putExtra("recipe_ingredients", getString(R.string.recipe_broiler_ingredients))
            intent.putExtra("recipe_steps", getString(R.string.recipe_broiler_steps))
            // Start the RecipeActivity with the new intent
            startActivity(intent)
        }

        omeletteText.setOnClickListener {
            // Create a new intent using a factory method and pass in the recipe name, ingredients, and steps as extras
            val intent = RecipeIntentFactory.createIntent(this, "Ruokaisa munakas")
            intent.putExtra("recipe_ingredients", getString(R.string.recipe_omelette_ingredients))
            intent.putExtra("recipe_steps", getString(R.string.recipe_omelette_steps))
            // Start the RecipeActivity with the new intent
            startActivity(intent)
        }

        //delete function
        if (intent?.action == "DELETE_RECORD") {
            val intValue = intent.getIntExtra("int_value", 0)
            //deleteRecord(intValue)
        }


        //Button for navigating to RecipeAddActivity
        val recipeAddButton = findViewById<ImageButton>(R.id.buRecipeAdd)
        recipeAddButton.setOnClickListener{
            val intent = Intent(this, RecipeAddActivity::class.java)
            startActivity(intent)
        }



        //Menu definitions
        val menuButton = findViewById<ImageButton>(R.id.buMenuRecipe)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navigationView = findViewById<NavigationView>(R.id.navView)

        //Buttons for navigating in the app
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
        // Hides action bar
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
                    val intent = Intent(this, MainActivity::class.java).apply {
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

    //Inflate the hamburger menu
    fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favoriteItem -> {
                val intent = Intent(this, LoginActivity::class.java).apply {
                }
                startActivity(intent);
                return true
            }
            R.id.userItem -> {
                val intent = Intent(this, StartScreenActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.homeItem -> {
                val intent = Intent(this, MainActivity::class.java).apply {
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
                Toast.makeText(applicationContext, "Tallennettu!", Toast.LENGTH_SHORT).show()
                editTextUsername.text.clear()
            }
        }else {
            Toast.makeText(applicationContext, "Käyttäjänimi tai salasana ei voi olla tyhjä!", Toast.LENGTH_SHORT).show()
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
            Toast.makeText(applicationContext, "Tekstit eivät voi olla tyhjiä", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteRecord(userID: Int){
        val databaseHandler: DBhelper = DBhelper(this)
        val status = databaseHandler.deleteUser(userID)
        if(status > -1) {
            Toast.makeText(applicationContext, "Poistettu", Toast.LENGTH_SHORT).show()
            IdDelete.text.clear()
        } else {
            Toast.makeText(applicationContext, "Virhe tapahtui", Toast.LENGTH_SHORT).show()
        }

    }
}
}



