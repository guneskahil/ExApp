package com.gunes.exapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gunes.exapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=Firebase.auth
        db=Firebase.firestore
        val currentUser=auth.currentUser
        if(currentUser!=null) {
            val intent = Intent(this, pagethree::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun girisYap(view:View){


        val kbilgisimail=binding.emailText.text.toString()
        val kbilgisiisim=binding.kullainiciAdi.text.toString()
        val kbilgisisifre=binding.passwordText.text.toString()
        val isimMap = hashMapOf<String, Any>().also {
            it.put("kbilgisimail",kbilgisimail)
            it.put("kbilgisiisim",kbilgisiisim)
            it.put("kbilgisisifre",kbilgisisifre)

        }
        db.collection("Kullanıcıbilgisi").add(isimMap).addOnSuccessListener {
        if (kbilgisimail==""||kbilgisisifre==""){
            Toast.makeText(this,"Email ve şifre giriniz.",Toast.LENGTH_SHORT).show()
        }else{

                auth.signInWithEmailAndPassword(kbilgisimail, kbilgisisifre).addOnSuccessListener {
                    val currentUser = auth.currentUser?.displayName.toString()
                    Toast.makeText(this, "Hoşgeldin ${currentUser}", Toast.LENGTH_SHORT).show()
                    intent = Intent(this, pagethree::class.java)
                    startActivity(intent)
                }
            }
            }.addOnFailureListener {
                Toast.makeText(this,it.localizedMessage,Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
            Toast.makeText(this,it.localizedMessage,Toast.LENGTH_SHORT).show()
        }


    }
    fun kayitOl(view: View){
        binding.kayitOl.setOnClickListener {
            intent=Intent(this, pagetwoo::class.java)
            startActivity(intent)
        }
    }

}