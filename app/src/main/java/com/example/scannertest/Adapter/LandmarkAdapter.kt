package com.example.scannertest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scannertest.databinding.RecyclerRowBinding


class LandmarkAdapter (val landmarkList: ArrayList<Food>) : RecyclerView.Adapter<LandmarkAdapter.LandmarkHolder>() {

    class LandmarkHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkHolder {
        //Layout (recycler_row) ile kodu binding ile bağlama işlemi
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LandmarkHolder(binding)
    }

    override fun onBindViewHolder(holder: LandmarkHolder, position: Int) {
        //Bağlandıktan sonra ne olacağı, neyin gösterileceği
        holder.binding.resName.text = landmarkList.get(position).name

        holder.binding.minus.setOnClickListener {

            var anzahl= holder.binding.anzahlitemid.text.toString().toInt()

            if (anzahl<=0){
                holder.binding.anzahlitemid.setText("0")
            }else{
                holder.binding.anzahlitemid.setText("${anzahl-1}")
            }
        }

        holder.binding.plus.setOnClickListener {
            var anzahl= holder.binding.anzahlitemid.text.toString().toInt()
            holder.binding.anzahlitemid.setText("${anzahl+1}")
        }




       // holder.itemView.setOnClickListener {
       //     val intent = Intent(holder.itemView.context,DetailsActivity::class.java)
       //     intent.putExtra("landmark",landmarkList.get(position))
       //     holder.itemView.context.startActivity(intent)
       // }
    }

    override fun getItemCount(): Int {
        //Kaç tane oluşturulacağı
        return landmarkList.size
    }

}