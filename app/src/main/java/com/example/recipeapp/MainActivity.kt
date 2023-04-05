package com.example.recipeapp


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.PopupWindow
import android.widget.ImageButton
import android.widget.LinearLayout
import android.view.ViewGroup.LayoutParams



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popup_layout, null)

        val popupWindow = PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true)

        val aboutButton = findViewById<ImageButton>(R.id.BU_about)
        aboutButton.setOnClickListener {
            popupWindow.showAtLocation(it.rootView, Gravity.CENTER, 0, 0)
    }

            popupWindow.dismiss()
    }
}


