package com.example.marvel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class Info : AppCompatActivity() {
    lateinit var txt_name:EditText
    lateinit var txt_desc:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        txt_desc=findViewById(R.id.Character_Desc)
        txt_name=findViewById(R.id.Character_Name)



    }
}