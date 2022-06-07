package com.example.searchviewapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView

class itemAdapter(private var dataList: List<ItemData>
): RecyclerView.Adapter<itemAdapter.ItemViewHolder>(), Filterable {

    var filteredList : List<ItemData> = dataList

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(data: ItemData)= with(itemView){
            val company = findViewById<TextView>(R.id.company)
            val country = findViewById<TextView>(R.id.country)

            company.text = data.brand
            country.text = data.country
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cars, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind(filteredList[position])

    override fun getItemCount() = filteredList.size

    override fun getFilter(): Filter {
       return object : Filter(){
           override fun performFiltering(p0: CharSequence?): FilterResults {
               val charString = p0.toString()

               filteredList = when{
                   charString.isEmpty() ->dataList
                   else ->{
                       val internalFilteredList: MutableList<ItemData> = mutableListOf()
                       for (data in dataList){
                           if (data.brand.contains(charString, ignoreCase = true)
                               || data.country.contains(charString, ignoreCase = true)){
                               internalFilteredList.add(data)
                           }
                       }
                       internalFilteredList
                   }
               }

               val filteredResults = FilterResults()
               filteredResults.values = filteredList
               return filteredResults
           }

           override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
               filteredList = p1!!.values as List<ItemData>
               notifyDataSetChanged()
           }

       }
    }
}