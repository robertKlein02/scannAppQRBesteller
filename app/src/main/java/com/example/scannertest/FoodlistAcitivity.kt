package com.example.scannertest


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scannertest.Elemente.Food
import com.example.scannertest.databinding.FoodlistBinding


class FoodlistAcitivity : AppCompatActivity() {

    private lateinit var binding: FoodlistBinding
    private lateinit var landmarkList : ArrayList<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FoodlistBinding.inflate(layoutInflater)
        val view = binding.root
        var click= intent.getStringExtra("click")



        // click wurde aus mainAcitvity übergeben und wird jetzt mit den drawable Elementen abgeglichen und dargestellt
        println(click)

        if (click=="Pizza") binding.topBild.setImageResource(R.drawable.pizzas)
        if (click=="Pasta") binding.topBild.setImageResource(R.drawable.pasta)
        if (click=="Döner") binding.topBild.setImageResource(R.drawable.doner)
        if (click=="Reis") binding.topBild.setImageResource(R.drawable.reis)
        if (click=="Dessert") binding.topBild.setImageResource(R.drawable.dessert)
        if (click=="Getränke") binding.topBild.setImageResource(R.drawable.trinken)
        if (click=="Burger") binding.topBild.setImageResource(R.drawable.burger)
        if (click=="Nudel") binding.topBild.setImageResource(R.drawable.noodel)
        if (click=="Cocktail") binding.topBild.setImageResource(R.drawable.cocktail)
        if (click=="Pommes") binding.topBild.setImageResource(R.drawable.pommes)
        if (click=="Sushi") binding.topBild.setImageResource(R.drawable.sushi)
        if (click=="Wein") binding.topBild.setImageResource(R.drawable.wine)
        if (click=="Bier") binding.topBild.setImageResource(R.drawable.beer)




        setContentView(view)

        landmarkList = ArrayList<Food>()



        //Data

        val pisa = Food("Essen","beschreibung4",R.drawable.pizzas)
        val colosseum = Food("Essen","beschreibung4",R.drawable.pizzas)
        val eiffel = Food("Essen","beschreibung5",R.drawable.pizzas)
        val londonBridge = Food("Essen Essen", "beschreibung4", R.drawable.pizzas)

        landmarkList.add(pisa)
        landmarkList.add(colosseum)
        landmarkList.add(eiffel)
        landmarkList.add(londonBridge)
        landmarkList.add(colosseum)
        landmarkList.add(eiffel)
        landmarkList.add(londonBridge)
        landmarkList.add(colosseum)
        landmarkList.add(eiffel)
        landmarkList.add(londonBridge)
        landmarkList.add(colosseum)
        landmarkList.add(eiffel)
        landmarkList.add(londonBridge)




        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val landmarkAdapter = RecyclerAdapter(landmarkList)
        binding.recyclerView.adapter = landmarkAdapter





        /*
        //Adapter : Layout & Data

        //Mapping

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,landmarkList.map { landmark -> landmark.name })

        binding.listView.adapter = adapter

        binding.listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(MainActivity@this,DetailsActivity::class.java)
            intent.putExtra("Landmark",landmarkList.get(position))
            startActivity(intent)
        }
        */






    }
}