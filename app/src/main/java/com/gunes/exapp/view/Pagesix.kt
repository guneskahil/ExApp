package com.gunes.exapp.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gunes.exapp.R
import com.gunes.exapp.adapter.kullaniciAdapter
import com.gunes.exapp.databinding.ActivityPagesixBinding
import com.gunes.exapp.model.kayit
import kotlinx.android.synthetic.main.activity_pagethree.*

class pagesix : AppCompatActivity() {
    private lateinit var binding:ActivityPagesixBinding
    val db= Firebase.firestore
    var isim = ArrayList<kayit>()
    private lateinit var recyclerViewAdapter: kullaniciAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPagesixBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseverial()
        val layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
        recyclerViewAdapter = kullaniciAdapter(isim)
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
        }
        return super.onOptionsItemSelected(item)
    }
    fun settingButton(view: View){
        binding.settingButton.setOnClickListener {
            intent= Intent(applicationContext, pagefour::class.java)
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
                        for (document in documents) {
                            val kbilgisimail = document.get("kbilgisimail") as String
                            val kbilgisiisim = document.get("kbilgisiisim") as String
                            val kbilgisisifre = document.get("kbilgisisifre") as String
                            var eklenenKayit = kayit(kbilgisimail, kbilgisiisim, kbilgisisifre)
                            isim.add(eklenenKayit)
                        }
                        recyclerViewAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}