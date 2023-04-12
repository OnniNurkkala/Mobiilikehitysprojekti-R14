package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class LoginActivity : AppCompatActivity() {

    private lateinit var loginbtn: Button
    private lateinit var editusername: EditText
    private lateinit var editpword: EditText
    private lateinit var dbh: DBhelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginbtn = findViewById(R.id.buLogin)
        editusername = findViewById(R.id.editTextUsername)
        editpword = findViewById(R.id.editTextTextPassword)
        dbh = DBhelper(this)
       val signup = findViewById<TextView>(R.id.textSignUp)

        signup.setOnClickListener{
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)

            signup.movementMethod = LinkMovementMethod.getInstance()
        }

        loginbtn.setOnClickListener {
            val usertx = editusername.text.toString()
            val pwordtx = editpword.text.toString()

            if (TextUtils.isEmpty(usertx) || TextUtils.isEmpty(pwordtx)) {
                Toast.makeText(this, "Add Username & Password", Toast.LENGTH_SHORT).show()
            } else {
                val checkuser = dbh.checkuserpass(usertx, pwordtx)
                if (checkuser == true) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Wrong Username or Password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}