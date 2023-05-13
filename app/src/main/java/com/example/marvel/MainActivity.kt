package com.example.marvel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var TxtFirstName: EditText
    lateinit var TxtSecondName: EditText
    lateinit var EdtPassword: EditText
    lateinit var PhoneNum: EditText
    lateinit var Txtemail: EditText
    lateinit var Txtlogin: TextView
    lateinit var EdtConfirmPass: EditText
    lateinit var BtnSignUp: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TxtFirstName = findViewById(R.id.Edt_FisrtName)
        TxtSecondName = findViewById(R.id.Edt_SecondName)
        EdtPassword = findViewById(R.id.Edt_Pass)
        PhoneNum = findViewById(R.id.Edt_PhoneNumber)
        Txtemail = findViewById(R.id.Edt_Email)
        Txtlogin = findViewById(R.id.Txt_Login)
        BtnSignUp = findViewById(R.id.BtnSignup)
        EdtConfirmPass = findViewById(R.id.Edt_Confirmpass)
        auth = Firebase.auth

        BtnSignUp.setOnClickListener {
            SignUpUser()
        }
        Txtlogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    private fun SignUpUser() {
        val firstname = TxtFirstName.text.toString()
        val secondname = TxtSecondName.text.toString()
        val email = Txtemail.text.toString()
        val phonenum = PhoneNum.text.toString()
        val password = EdtPassword.text.toString()
        val confpass = EdtPassword.text.toString()
        if (firstname.isBlank() || secondname.isBlank() || email.isBlank() || phonenum.isBlank() || password.isBlank() || confpass.isBlank()) {
            Toast.makeText(this, "Fill missing fields", Toast.LENGTH_LONG).show()
            return
        } else if (password != confpass) {
            Toast.makeText(this, "Password do not match", Toast.LENGTH_LONG).show()
            return
        }
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Signed up successfully", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Failed to create", Toast.LENGTH_LONG).show()
            }
        }
    }
}
