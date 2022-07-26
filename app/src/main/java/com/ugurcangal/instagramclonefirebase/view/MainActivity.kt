package com.ugurcangal.instagramclonefirebase.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ugurcangal.instagramclonefirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth
        val currentUser = auth.currentUser
        currentUser?.let {
            val intent = Intent(this, FeedActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.kayitOl.setOnClickListener {
            signUpClicked()
        }

        binding.girisYap.setOnClickListener {
            signInClicked()
        }

    }

    private fun signInClicked() {
        val email = binding.emailText.text.toString()
        val password = binding.editTextTextPassword.text.toString()
        if (binding.checkbox.isChecked){
            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    val intent = Intent(this, FeedActivity::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this, "Welcome : ${it.user!!.email}", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Enter email and password!", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"please tick the box.",Toast.LENGTH_SHORT).show()
        }
    }

    private fun signUpClicked() {
        val email = binding.emailText.text.toString()
        val password = binding.editTextTextPassword.text.toString()

        if (binding.checkbox.isChecked){
            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                    val intent = Intent(this@MainActivity, FeedActivity::class.java)
                    startActivity(intent)
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Enter email and password!", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"please tick the box.",Toast.LENGTH_SHORT).show()
        }
    }
}