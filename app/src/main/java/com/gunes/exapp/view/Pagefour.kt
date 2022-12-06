package com.gunes.exapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gunes.exapp.adapter.kullaniciAdapter
import com.gunes.exapp.databinding.ActivityPagefourBinding
import com.gunes.exapp.model.kayit
import kotlinx.android.synthetic.main.activity_pagefour.*

class pagefour : AppCompatActivity() {
    private lateinit var binding: ActivityPagefourBinding
    private lateinit var auth: FirebaseAuth
    val db= Firebase.firestore
    var isim = ArrayList<kayit>()
    private lateinit var recyclerviewAdapter: kullaniciAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPagefourBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=Firebase.auth
        firebaseverial()

       /* auth.addAuthStateListener {
            //binding.kBilgisiMail.text.toString()
            //binding.kBilgisiSifre.text.toString()
            intent=Intent(applicationContext, pagethree::class.java)
            startActivity(intent)
        }*/
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerviewAdapter = kullaniciAdapter(isim)
        recyclerView.adapter = recyclerviewAdapter
    }
    fun cikisYap(view: View) {
        binding.buttonCikisYap.setOnClickListener {
            intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun backPage(view: View){
        binding.backPage.setOnClickListener {
            intent= Intent(applicationContext, pagethree::class.java)
            startActivity(intent)
        }
    }
    fun firebaseverial() {
        db.collection("Kullanıcıbilgisi").addSnapshotListener { snapshot, error ->
            if (error != null) {
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
            } else {
                if (snapshot != null) {
                    if (!snapshot.isEmpty) {
                        val documents = snapshot.documents
                        isim.clear()
                        for (documentt in documents) {
                            val kbilgisimail = documentt.get("kbilgisimail") as String
                            val kbilgisiisim = documentt.get("kbilgisiisim") as String
                            val kbilgisisifre = documentt.get("kbilgisisifre") as String?
                            var eklenenKayit = kayit(kbilgisimail, kbilgisiisim, kbilgisisifre)
                            isim.add(eklenenKayit)
                        }
                        recyclerviewAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}