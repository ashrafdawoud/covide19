package com.example.covide19app.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covide19app.Model.DoctorsModel
import com.example.covide19app.R
import com.example.covide19app.View.LawyerProfileActivity
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class DoctorAdapter  constructor(val context: Context, val data :List<DoctorsModel>): RecyclerView.Adapter<DoctorAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pobular_item, parent, false));
    }

    override fun onBindViewHolder(holder: DoctorAdapter.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
           val yourIntent :Intent = Intent(context, LawyerProfileActivity::class.java)
            val b = Bundle()
            b.putSerializable("user", data.get(position))
            yourIntent.putExtras(b) //pass bundle to your intent
            context.startActivity(yourIntent)
        }
        Picasso.get().load(data.get(position).image).into(holder.image)
        holder.name.setText(data.get(position).name)
        holder.exp.setText(data.get(position).exp+" experiance years ")
    }

    override fun getItemCount(): Int {
        return data.size
    }
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image : CircleImageView=itemView.findViewById(R.id.profile_image)
        val name : TextView=itemView.findViewById(R.id.name)
        val exp : TextView=itemView.findViewById(R.id.exp)
    }
}