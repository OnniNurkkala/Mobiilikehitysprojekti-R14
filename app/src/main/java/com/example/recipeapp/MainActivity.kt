package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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