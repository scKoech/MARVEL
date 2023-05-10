package com.example.marvel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    lateinit var TxtUserdetails:EditText
    lateinit var EdtPass:EditText
    lateinit var RedirectReg:TextView
    lateinit var BtnLogin:Button
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        TxtUserdetails=findViewById(R.id.Txt_userdetails)
        BtnLogin=findViewById(R.id.BtnLogin)
        EdtPass=findViewById(R.id.Edt_PassLogin)
        RedirectReg=findViewById(R.id.Txt_Register)
        auth= FirebaseAuth.getInstance()

        BtnLogin.setOnClickListener {
            login()
        }
        RedirectReg.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)

            finish()
        }
    }
    private fun login(){
        val email=TxtUserdetails.text.toString()
        val username=TxtUserdetails.text.toString()
        val pass=EdtPass.text.toString()

        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this,"Logged in successfully",Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(this,"Log in failed",Toast.LENGTH_SHORT).show()
        }
    }
}