package com.example.marvel

import android.app.DownloadManager
import retrofit2.Call
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.net.HttpURLConnection
import java.net.URL
import retrofit2.http.GET
import android.widget.Toast

class MainPage : AppCompatActivity() {
    lateinit var txt_welcom:TextView
    lateinit var Edt_EnterName:EditText
    lateinit var Btn_Enter:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        txt_welcom=findViewById(R.id.Txt_Welcom)
        Edt_EnterName=findViewById(R.id.Edt_Superhero)
        Btn_Enter=findViewById(R.id.Btn_Enter)


        Btn_Enter.setOnClickListener {
            val intent=Intent(this,Info::class.java)
            startActivity(intent)
        }
    }
}