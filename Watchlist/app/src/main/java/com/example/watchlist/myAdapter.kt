package com.example.watchlist

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class myAdapter(private val context: Activity, private val arrayList:List<Movie>): ArrayAdapter<Movie>(
    context, R.layout.list_item, arrayList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflator:LayoutInflater = LayoutInflater.from(context)
        val view: View = inflator.inflate(R.layout.list_item, null)
        val title = view.findViewById<TextView>(R.id.itemTitle)
        val service = view.findViewById<TextView>(R.id.itemService)
        val rating = view.findViewById<TextView>(R.id.itemRating)
        val seen = view.findViewById<TextView>(R.id.itemSeen)

        title.text = "Title: ${arrayList[position].title}"
        service.text = "Streaming Service: ${arrayList[position].service}"
        rating.text = "Rating: ${arrayList[position].rating.toString()}"
        if(arrayList[position].seen == true){
            seen.text = "Have seen"
        }else{
            seen.text = "Have not seen"
        }
        return view
    }
}