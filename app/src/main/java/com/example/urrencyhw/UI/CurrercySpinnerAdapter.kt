package com.example.urrencyhw.UI

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.urrencyhw.R

class CurrencySpinnerAdapter(context: Context, resource: Int, objects: List<String>) :
    ArrayAdapter<String>(context, resource, objects) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_spinner, parent, false)
        view.findViewById<TextView>(R.id.tvSpinn).text = getItem(position)
        return view
    }


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_spinner, parent, false)

        view.findViewById<TextView>(R.id.tvSpinn).text = getItem(position)
        return view
    }
}