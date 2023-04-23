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

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }


        login.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            login.movementMethod = LinkMovementMethod.getInstance()
        }

        signupbtn.setOnClickListener{
            val unametext = uname.text.toString()
            val pwordtext = pword.text.toString()
            val cpwordtext = cpword.text.toString()
            val savedata = db.insertdata(EmpModelClass(unametext,pwordtext))


            if (TextUtils.isEmpty(unametext) || TextUtils.isEmpty(pwordtext) || TextUtils.isEmpty(cpwordtext)){
                Toast.makeText(this, "Lisää käyttäjänimi, salasana & syötä salasana uudestaan", Toast.LENGTH_SHORT).show()
            }
            else{
                if (pwordtext.equals(cpwordtext)){
                    if (savedata != -1L) {
                        Toast.makeText(this, "Rekisteröityminen onnistui", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(this, "Käyttäjänimi on jo käytössä", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "Salasanat eivät täsmää", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}