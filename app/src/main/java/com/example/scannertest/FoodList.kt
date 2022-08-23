package com.example.scannertest


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.scannertest.databinding.ActivityMainBinding
import com.example.scannertest.databinding.FoodlistBinding

class FoodList() : AppCompatActivity() {

    private lateinit var binding: FoodlistBinding
    var foodArrayList = ArrayList<Food>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FoodlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        var icons=findViewById<ImageView>(R.id.icon2)
        

        if(name=="Pizza")icons!!.setImageResource(R.drawable.pizzas)
        if(name=="Pasta")icons!!.setImageResource(R.drawable.pasta)
        if(name=="Döner")icons!!.setImageResource(R.drawable.doner)
        if(name=="Reis")icons!!.setImageResource(R.drawable.reis)
        if(name=="Dessert")icons!!.setImageResource(R.drawable.dessert)
        if(name=="Getränke")icons!!.setImageResource(R.drawable.trinken)
        if(name=="Burger")icons!!.setImageResource(R.drawable.burger)
        if(name=="Nudel")icons!!.setImageResource(R.drawable.noodel)
        if(name=="Cocktail")icons!!.setImageResource(R.drawable.cocktail)
        if(name=="Pommes")icons!!.setImageResource(R.drawable.pommes)
        if(name=="Sushi")icons!!.setImageResource(R.drawable.sushi)
        if(name=="Wein")icons!!.setImageResource(R.drawable.wine)
        if(name=="Bier")icons!!.setImageResource(R.drawable.beer)










        //Hier werden die Daten von den Restaurants geladen
        val imageId = intArrayOf(
            R.drawable.burger,
            R.drawable.pommes,
            R.drawable.pizzas
        )

        val names = arrayOf(
            "Chicken Schnitzel",
            "Salt and Pepper Steak",
            "Bacon Wrapped Pork Tenderloin"
        )

        val descs = arrayOf(
            "This classic chicken Schnitzel recipe sounds harder to make than it is. Plus, you get to pound out the day's stress",
            "What's inexpensive, versatile, and crazy tasty? Skirt steak. It also cooks in a flash, which make it tailor-made for grilling",
            "Think of this as the weeknight version of a classic Italian porchetta—it's not traditional, but it sure is delicious"
        )

        for (i in names.indices){

            val food = Food(names[i],descs[i])
            foodArrayList.add(food)
        }
        binding.foodsList.adapter= MyAdapter(this,foodArrayList)

        // Click lestener to list item
        binding.foodsList.isClickable = false


       // binding.foodsList.setOnItemClickListener { parent, view, position, id ->
//
       //     val name = names[position]
       //     val desx = descs[position]
       //     val imageId = imageId[position]
//
       //     val i = Intent(this,FoodsScreen::class.java)
       //     i.putExtra("name",name)
       //     i.putExtra("desc",desx)
       //     i.putExtra("imageId", imageId)
       //     startActivity(i)
       // }


    }
}