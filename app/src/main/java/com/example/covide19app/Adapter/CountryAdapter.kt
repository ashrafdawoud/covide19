package com.example.covide19app.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covide19app.Model.CountriesModel
import com.example.covide19app.R

class CountryAdapter(val dataset: List<CountriesModel>) :RecyclerView.Adapter<CountryAdapter.ViewHolder> (){
    class ViewHolder(itemview: View) :RecyclerView.ViewHolder(itemview){
        val position:TextView=itemview.findViewById(R.id.position)
        val country:TextView=itemview.findViewById(R.id.country_name)
        val totalcases:TextView=itemview.findViewById(R.id.totalCases)
        val death:TextView=itemview.findViewById(R.id.deathCases)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.countery_item, parent, false)
        return CountryAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryAdapter.ViewHolder, position: Int) {
        if (dataset.get(position).Country_text!=null) {
            if (dataset.get(position).Country_text.equals("Egypt"))
                holder.itemView.setBackgroundColor(Color.CYAN)
            holder.position.setText("$position - ")
            holder.country.setText(dataset.get(position).Country_text)
            holder.totalcases.setText(dataset.get(position).Total_Cases_text)
            holder.death.setText(dataset.get(position).Total_Deaths_text)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}