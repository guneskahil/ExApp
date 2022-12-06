package com.gunes.exapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gunes.exapp.R
import com.gunes.exapp.model.kayit
import kotlinx.android.synthetic.main.recyler_row_kbilgisi.view.*

class kullaniciAdapter(val isim:ArrayList<kayit>): RecyclerView.Adapter<kullaniciAdapter.kullaniciHolder>()  {
    class kullaniciHolder(itemView:View):RecyclerView.ViewHolder(itemView)  {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kullaniciHolder {
        val inflater= LayoutInflater.from(parent.context)
        val View=inflater.inflate(R.layout.recyler_row_kbilgisi,parent,false)
        return kullaniciHolder(View)
    }

    override fun onBindViewHolder(holder: kullaniciHolder, position: Int) {
        holder.itemView.rrkadi.text=isim[position].kbilgisiisim
        holder.itemView.rrkmaili.text=isim[position].kbilgisimail
        holder.itemView.rrksifresi.text=isim[position].kbilgisisifre
    }

    override fun getItemCount(): Int {
        return isim.size
    }
}