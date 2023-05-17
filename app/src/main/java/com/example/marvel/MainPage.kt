package com.example.marvel

import android.app.DownloadManager
import retrofit2.Call
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

        val characters = MarvelApiClient.getCharacters()
        characters.forEach { character ->
            println("${character.name}: ${character.description}")
        }


        Btn_Enter.setOnClickListener {
           DownloadManager.Request: GET http://gateway.marvel.com/v1/public/comics?apikey=yourAPIKEY& // callback=callback_param

            val intent=Intent(this,Info::class.java)
            startActivity(intent)
        }
    }
}