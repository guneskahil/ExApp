package com.gunes.exapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_pagetwoo.*
import kotlinx.android.synthetic.main.activity_pagetwoo.view.*

class pagetwoo : AppCompatActivity() {
    private lateinit var binding: com.gunes.exapp.databinding.ActivityPagetwooBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=com.gunes.exapp.databinding.ActivityPagetwooBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= Firebase.auth


    }
    fun kayitOlClick(view: View) {
        val email = binding.emailText.text.toString()
        val password = binding.passwordtext.text.toString()
        val kullaniciAdi=kullainiciAdi.kullainiciAdi.text.toString()
        if (email=="" || password=="") {
            Toast.makeText(this, "Email ve şifre giriniz.", Toast.LENGTH_LONG).show()
        } else {
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                val currentUser=auth.currentUser
                val profilGuncelİstek= userProfileChangeRequest {
                    displayName=kullaniciAdi
                }
                if (currentUser!=null){
                currentUser.updateProfile(profilGuncelİstek).addOnCompleteListener {
                    Toast.makeText(applicationContext,"Profil Kullanıcı Adı Eklendi",Toast.LENGTH_SHORT).show()
                }
            }

                intent = Intent(this, pagethree::class.java)
                Toast.makeText(this,"Kayıt Başarılı",Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
    fun giriseDon(view: View) {
        binding.giriseDon.setOnClickListener {
            intent=Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}