package com.example.scannertest


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.scannertest.databinding.*



internal class ImageListAdapter internal constructor(
    context: Context,
    private val resource: Int,
    private val itemList: Array<String>?
) : ArrayAdapter<ImageListAdapter.ItemViewHolder>(context, resource) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private lateinit var itemBinding: ItemlistBinding

    override fun getCount(): Int {
        return if (this.itemList != null) this.itemList.size else 0
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var convertView = view
        val holder: ItemViewHolder
        if (convertView == null) {
            itemBinding = ItemlistBinding.inflate(inflater)
            convertView = itemBinding.root
            holder = ItemViewHolder()
            holder.name = itemBinding.textView
            holder.icon = itemBinding.icon
            convertView.tag = holder
        } else {
            holder = convertView.tag as ItemViewHolder
        }
        holder.name!!.text = this.itemList!![position]

        if(holder.name!!.text=="Pizza")holder.icon!!.setImageResource(R.drawable.pizza)
        if(holder.name!!.text=="Pasta")holder.icon!!.setImageResource(R.drawable.pasta)
        if(holder.name!!.text=="Döner")holder.icon!!.setImageResource(R.drawable.doner)
        if(holder.name!!.text=="Reis")holder.icon!!.setImageResource(R.drawable.reis)
        if(holder.name!!.text=="Dessert")holder.icon!!.setImageResource(R.drawable.cupcake)
        if(holder.name!!.text=="Getränke")holder.icon!!.setImageResource(R.drawable.wasser)
        if(holder.name!!.text=="Burger")holder.icon!!.setImageResource(R.drawable.burger)
        if(holder.name!!.text=="Nudel")holder.icon!!.setImageResource(R.drawable.noodle)
        if(holder.name!!.text=="Cocktail")holder.icon!!.setImageResource(R.drawable.cocktail)
        if(holder.name!!.text=="Pommes")holder.icon!!.setImageResource(R.drawable.pommes)
        if(holder.name!!.text=="Sushi")holder.icon!!.setImageResource(R.drawable.sushi)
        if(holder.name!!.text=="Wein")holder.icon!!.setImageResource(R.drawable.wine)
        if(holder.name!!.text=="Bier")holder.icon!!.setImageResource(R.drawable.beer)

        return convertView
    }

    internal class ItemViewHolder {
        var name: TextView? = null
        var icon: ImageView? = null
    }
}
