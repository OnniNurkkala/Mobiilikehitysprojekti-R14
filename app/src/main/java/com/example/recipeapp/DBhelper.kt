package com.example.recipeapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBhelper(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "TestDatabase"
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
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ KEY_NAME + " TEXT," + KEY_PASS + " TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)

        val CREATE_RECIPES_TABLE = ("CREATE TABLE " + TABLE_RECIPES + "("
                + KEY_RECIPE_ID + " INTEGER PRIMARY KEY,"
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
    fun insertdata(emp: EmpModelClass):Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, emp.userName)
        contentValues.put(KEY_PASS, emp.userPassword)
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        db.close()
        return success
    }

    fun checkuserpass(emp: EmpModelClass): Boolean {
        val db = this.writableDatabase
        val query = "select * from UserTable where $KEY_NAME= '${emp.userName}' and $KEY_PASS= '${emp.userPassword}'"
        val cursor = db.rawQuery(query, null)
        if (cursor.count<=0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }
}