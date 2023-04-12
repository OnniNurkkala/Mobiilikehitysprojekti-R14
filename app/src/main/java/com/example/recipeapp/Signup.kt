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

class Signup : AppCompatActivity() {

    private lateinit var uname: EditText
    private  lateinit var pword: EditText
    private lateinit var cpword: EditText
    private lateinit var signupbtn: Button
    private lateinit var db: DBhelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        uname = findViewById(R.id.editTextTextUsername)
        pword = findViewById(R.id.editTextTextPassword1)
        cpword = findViewById(R.id.editTextTextPassword2)
        signupbtn = findViewById(R.id.buSignUp)
        db = DBhelper(this)
        val login = findViewById<TextView>(R.id.textLogin1)

        login.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            login.movementMethod = LinkMovementMethod.getInstance()
        }

        signupbtn.setOnClickListener{
            val unametext = uname.text.toString()
            val pwordtext = pword.text.toString()
            val cpwordtext = cpword.text.toString()
            val savedata = db.insertdata(unametext, pwordtext)


            if (TextUtils.isEmpty(unametext) || TextUtils.isEmpty(pwordtext) || TextUtils.isEmpty(cpwordtext)){
                Toast.makeText(this, "Add Username, password & Confirm Password", Toast.LENGTH_SHORT).show()
            }
            else{
                if (pwordtext.equals(cpwordtext)){
                    if (savedata==true){
                        Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, "Username Exists", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "Password Not Match", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}