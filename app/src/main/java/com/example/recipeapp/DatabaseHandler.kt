package com.example.recipeapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "UserDatabase"
        private val TABLE_CONTACTS = "UserTable"
        private val TABLE_RECIPES = "RecipeTable"
        private val KEY_RECIPE_ID = "recipe_id"
        private val KEY_RECIPE_NAME = "recipeName"
        private val KEY_RECIPE_INGREDIENTS = "recipe_ing"
        private val KEY_RECIPE_INSTRUCTIONS = "recipe_inst"
        private val KEY_ID = "id"
        private val KEY_NAME = "name"
        private val KEY_PASS = "password"

    }
    override fun onCreate(db: SQLiteDatabase?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //creating table with fields
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"+ KEY_NAME + " TEXT," + KEY_PASS + " TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)

        val CREATE_RECIPES_TABLE = ("CREATE TABLE " + TABLE_RECIPES + "("
                + KEY_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_RECIPE_NAME + " TEXT,"
                + KEY_RECIPE_INGREDIENTS + " TEXT,"
                + KEY_RECIPE_INSTRUCTIONS + " TEXT"
                + ")")
        db?.execSQL(CREATE_RECIPES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }


    fun addRecipe(recipe: RecipeClass): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_RECIPE_NAME, recipe.recipeName)
        contentValues.put(KEY_RECIPE_INGREDIENTS, recipe.recipe_ing)
        contentValues.put(KEY_RECIPE_INSTRUCTIONS, recipe.recipe_inst)
        val success = db.insert(TABLE_RECIPES, null, contentValues)
        db.close()
        return success
    }
    fun addUser(emp: EmpModelClass):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, emp.userName)
        contentValues.put(KEY_PASS, emp.userPassword)
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        db.close()
        return success
    }

    fun viewUser():List<EmpModelClass>{
        val empList:ArrayList<EmpModelClass> = ArrayList<EmpModelClass>()
        val selectQuery = "SELECT * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var userName: String
        var userPass: String
        if (cursor.moveToFirst()){
            do {
                userName = cursor.getString(cursor.getColumnIndex("name"))
                userPass = cursor.getString(cursor.getColumnIndex("pass"))
                val emp= EmpModelClass(userName = userName, userPassword = userPass)
                empList.add(emp)
            }while (cursor.moveToNext())
        }
        return empList
    }

    fun udpateUser(emp: EmpModelClass):Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, emp.userName)
        contentValues.put(KEY_PASS, emp.userPassword)
        val success = db.update(TABLE_CONTACTS, contentValues, "name"+emp.userName, null)
        db.close()
        return success
    }

    fun updateRecipe(recipe: RecipeClass): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_RECIPE_NAME, recipe.recipeName)
        contentValues.put(KEY_RECIPE_INGREDIENTS, recipe.recipe_ing)
        contentValues.put(KEY_RECIPE_INSTRUCTIONS, recipe.recipe_inst)
        val success = db.update(TABLE_RECIPES, contentValues, "recipe"+ recipe.recipeName, null)
        db.close()
        return success
    }

    fun deleteUser(userID: Int): Int {
        val db = this.writableDatabase
        val success = db.delete(TABLE_CONTACTS, "$KEY_ID = ?", arrayOf(userID.toString()))
        db.close()
        return success
    }
}