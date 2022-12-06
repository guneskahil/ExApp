package com.gunes.exapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gunes.exapp.R
import com.gunes.exapp.model.Ekleme
import kotlinx.android.synthetic.main.recycler_row.view.*

class ListeAdapter(val urunListesi: ArrayList<Ekleme>):RecyclerView.Adapter<ListeAdapter.ListeHolder>() {


    class ListeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListeHolder {
      val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.recycler_row,parent,false)
        return ListeHolder(view)
    }

    override fun onBindViewHolder(holder: ListeHolder, position: Int) {
        holder.itemView.rrurunadi.text=urunListesi[position].name
        holder.itemView.rrurunturu.text=urunListesi[position].tur
        holder.itemView.rruruntarihi.text=urunListesi[position].tarih
    }

    override fun getItemCount(): Int {
        return urunListesi.size
    }
}
