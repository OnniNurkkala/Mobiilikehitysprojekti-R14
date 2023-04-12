package com.example.recipeapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException

class RecipeHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object{
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "UserDatabase"
        private val TABLE_CONTACTS = "RecipeTable"
        private val KEY_NAME = "name"
        private val KEY_INFO = "info"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // TODO("Not yet implemented")
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE" + TABLE_CONTACTS + "("
                + KEY_NAME + "TEXT PRIMARY KEY," + KEY_INFO + "TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // TODO("Not yet implemented")
        db!!.execSQL("DROP TABLE IF EXISTS" + TABLE_CONTACTS)
        onCreate(db)
    }

    fun addRecipe(emp: RecipeClass):Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, emp.recipeName)
        contentValues.put(KEY_INFO, emp.recipeInfo)

        val success = db.insert(TABLE_CONTACTS, null, contentValues)

        db.close()
        return success
    }

    fun viewRecipe():List<RecipeClass>{
        val recipeList:ArrayList<RecipeClass> = ArrayList<RecipeClass>()
        val selectQuery = "SELECT * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var recipeName: String
        var recipeInfo: String
        if(cursor.moveToFirst()){
            do {
                recipeName = cursor.getString(cursor.getColumnIndex("name"))
                recipeInfo = cursor.getString(cursor.getColumnIndex("info"))
                val emp = RecipeClass(recipeName = recipeName, recipeInfo = recipeInfo)
                recipeList.add(emp)
            }while (cursor.moveToNext())
        }
        return recipeList
    }

    fun deleteRecipe(emp: RecipeClass):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, emp.recipeName)
        val success = db.delete(TABLE_CONTACTS, "name="+emp.recipeName, null)
        db.close()
        return success
    }

}