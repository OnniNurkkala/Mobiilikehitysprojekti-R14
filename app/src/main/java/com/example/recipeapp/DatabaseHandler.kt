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
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }

    fun addUser(emp: EmpModelClass):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, emp.userID)
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
        var userID: Int
        var userName: String
        var userPass: String
        if (cursor.moveToFirst()){
            do {
                userID = cursor.getInt(cursor.getColumnIndex("id"))
                userName = cursor.getString(cursor.getColumnIndex("name"))
                userPass = cursor.getString(cursor.getColumnIndex("pass"))
                val emp= EmpModelClass(userID = userID, userName = userName, userPassword = userPass)
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

    fun deleteUser(emp: EmpModelClass): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, emp.userName)
        val success = db.delete(TABLE_CONTACTS, "name"+emp.userName, null)
        db.close()
        return success
    }
}