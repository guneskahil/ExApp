package com.gunes.exapp.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gunes.exapp.R
import com.gunes.exapp.adapter.ListeAdapter
import com.gunes.exapp.databinding.ActivityPagethreeBinding
import com.gunes.exapp.model.Ekleme
import kotlinx.android.synthetic.main.activity_pagethree.*

class pagethree : AppCompatActivity() {
    private lateinit var binding: ActivityPagethreeBinding
    private lateinit var auth: FirebaseAuth
    val db=Firebase.firestore
    var urunListesi = ArrayList<Ekleme>()
    private lateinit var recyclerViewAdapter: ListeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagethreeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth
        firebaseverilerial()
        val layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
        recyclerViewAdapter = ListeAdapter(urunListesi)
        recycler_view.adapter = recyclerViewAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settings) {
            val intent = Intent(applicationContext, pagefour::class.java)
            startActivity(intent)

        } else if (item.itemId == R.id.signout) {
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun urunSayfasiClick(view: View) {
        binding.urunSayfasiButton.setOnClickListener {
            intent = Intent(applicationContext, pagethree::class.java)
            startActivity(intent)
        }
    }
    fun exUrunSayfasiClick(view: View) {
        binding.urunSayfasiButton.setOnClickListener {
            intent = Intent(applicationContext, pagesix::class.java)
            startActivity(intent)
        }
    }
    fun addButton(view: View) {
        binding.addButton.setOnClickListener {
            intent = Intent(applicationContext, pagefive::class.java)
            startActivity(intent)
        }
    }

    fun firebaseverilerial() {
        db.collection("Ürünler").addSnapshotListener { snapshot, error ->
            if (error != null) {
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
            } else {
                if (snapshot != null) {
                    if (!snapshot.isEmpty) {
                        val documents = snapshot.documents
                        urunListesi.clear()
                        for (document in documents) {
                            val name = document.get("Name") as String
                            val tur = document.get("Tur") as String
                            val tarih = document.get("Tarih") as String
                            var eklenenUrun = Ekleme(name, tur, tarih)
                            urunListesi.add(eklenenUrun)
                        }
                        recyclerViewAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}
