package com.example.covide19app.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.covide19app.Model.AdvicesModel
import com.example.covide19app.R
import com.example.covide19app.View.AdviceDetailsActivity
import com.squareup.picasso.Picasso


class AdviceAdapter(val context: Context, val data: List<AdvicesModel>): RecyclerView.Adapter<AdviceAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.advice_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(data.get(position).image).into(holder.image)
        holder.discription.setText((data.get(position).topic))
        holder.title.setText((data.get(position).title))
        holder.itemView.setOnClickListener {
            val yourIntent : Intent = Intent(context, AdviceDetailsActivity::class.java)
            val b = Bundle()
            b.putSerializable("user", data.get(position))
            yourIntent.putExtras(b) //pass bundle to your intent
            context.startActivity(yourIntent)
        }
    }
    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image:ImageView=itemView.findViewById(R.id.image)
        val title:TextView=itemView.findViewById(R.id.title1)
        val discription:TextView=itemView.findViewById(R.id.discription)
    }
}