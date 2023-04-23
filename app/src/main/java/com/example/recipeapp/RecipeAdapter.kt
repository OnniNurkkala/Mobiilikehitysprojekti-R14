package com.example.recipeapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(private val context: Context, private val recipeList: List<RecipeClass>, private val layoutResId: Int) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(layoutResId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.recipeNameTextView.text = recipe.recipeName
        holder.recipeIngredientsTextView.text = recipe.recipe_ing
        holder.recipeInstructionsTextView.text = recipe.recipe_inst
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recipeNameTextView: TextView = itemView.findViewById(R.id.recipe_name)
        var recipeIngredientsTextView: TextView = itemView.findViewById(R.id.recipe_ingredients)
        var recipeInstructionsTextView: TextView = itemView.findViewById(R.id.recipe_steps)
    }
}