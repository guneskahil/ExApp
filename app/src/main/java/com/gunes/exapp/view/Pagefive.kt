package com.gunes.exapp.view

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.gunes.exapp.R
import com.gunes.exapp.databinding.ActivityPagefiveBinding
import java.util.*


class pagefive : AppCompatActivity() {
    private lateinit var buttonDate: Button
    private lateinit var dateTextView:TextView
    private lateinit var binding: ActivityPagefiveBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore:FirebaseFirestore
    private lateinit var storage:FirebaseStorage
    private lateinit var db:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPagefiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db=Firebase.firestore
        auth=Firebase.auth
        firestore=Firebase.firestore

        buttonDate=findViewById(R.id.buttonDate)
        dateTextView=findViewById(R.id.dateTextView)
        val bugun=Calendar.getInstance()
        val year=bugun.get(Calendar.YEAR)
        val month=bugun.get(Calendar.MONTH)
        val day=bugun.get(Calendar.DAY_OF_MONTH)

        buttonDate.setOnClickListener{
            val dpd=DatePickerDialog(this,DatePickerDialog.OnDateSetListener{
                view,mmonth,mday,myear->
                dateTextView.text="$mday/$mmonth/$myear"
            },month,day,year).show()
        }
    }

    fun ekleClick(view: View){

        val name=binding.urunIsmiText.text.toString()
        val tur=binding.urunCesidText.text.toString()
        val tarih=binding.dateTextView.text.toString()
        val urunMap = hashMapOf<String, Any>().also {
            it.put("Name",name)
            it.put("Tur",tur)
            it.put("Tarih",tarih)
            it.put("Date",Timestamp.now())
        }
        db.collection("Ürünler").add(urunMap).addOnSuccessListener {
                if (name.isNotEmpty() && tur.isNotEmpty() && tarih.isNotEmpty()) {
                    Toast.makeText(this, "Ürün eklendi", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, pagethree::class.java)
                    startActivity(intent)
                }
            else{
                Toast.makeText(this,"Gerekli bilgileri giriniz.",Toast.LENGTH_SHORT).show()
                }
        }.addOnFailureListener {
            Toast.makeText(this,it.localizedMessage,Toast.LENGTH_SHORT).show()
        }

    }
}





