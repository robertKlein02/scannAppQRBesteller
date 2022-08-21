package com.example.scannertest

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(private val context : Activity, private val arrayList: ArrayList<Food>) : ArrayAdapter<Food>(context,
                R.layout.list_item,arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        // Make inflater to inflate the item view
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_item,null)

        // Refer to elements of the item view
        val imageView : ImageView = view.findViewById(R.id.foodImage)
        val txName : TextView = view.findViewById(R.id.resName)
        val txDesc : TextView = view.findViewById(R.id.desc)

        // Set data in view
        imageView.setImageResource(arrayList[position].image)
        txName.text = arrayList[position].name
        txDesc.text = arrayList[position].desc


        return view
    }

}