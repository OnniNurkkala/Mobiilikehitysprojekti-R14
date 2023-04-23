package com.example.recipeapp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecipeListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeListAdapter: RecipeAdapter
    private lateinit var recipeList: ArrayList<RecipeClass>
    private val KEY_RECIPE_ID = "recipe_id"
    private val KEY_RECIPE_NAME = "recipeName"
    private val KEY_RECIPE_INGREDIENTS = "recipe_ing"
    private val KEY_RECIPE_INSTRUCTIONS = "recipe_inst"
    private val layoutResId: Int = R.layout.recipe_item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        recyclerView = findViewById(R.id.recipe_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recipeList = ArrayList<RecipeClass>()
        recipeListAdapter = RecipeAdapter(this, recipeList, layoutResId)
        recyclerView.adapter = recipeListAdapter

        loadRecipes()

        // Register a listener for the result of RecipeAddActivity
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Get the new recipe from the intent extras
                val recipe = result.data?.getSerializableExtra("recipe") as RecipeClass?
                if (recipe != null) {
                    // Add the new recipe to the list and notify the adapter
                    recipeList.add(recipe)
                    recipeListAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun loadRecipes() {
        Log.d("RecipeListActivity", "loadRecipes() called")
        val dbHandler = DatabaseHandler(this)
        val cursor = dbHandler.getRecipes()

        if (cursor.moveToFirst()) {
            do {
                val recipe_id = cursor.getInt(cursor.getColumnIndex(KEY_RECIPE_ID))
                val recipe_name = cursor.getString(cursor.getColumnIndex(KEY_RECIPE_NAME))
                val recipe_ing = cursor.getString(cursor.getColumnIndex(KEY_RECIPE_INGREDIENTS))
                val recipe_inst = cursor.getString(cursor.getColumnIndex(KEY_RECIPE_INSTRUCTIONS))
                val recipe = RecipeClass(recipe_id, recipe_name, recipe_ing, recipe_inst)
                recipeList.add(recipe)
            } while (cursor.moveToNext())
        }

        recipeListAdapter.notifyDataSetChanged()
    }
}