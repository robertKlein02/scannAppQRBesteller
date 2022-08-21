package com.example.scannertest


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.scannertest.databinding.ActivityMainBinding
import com.example.scannertest.databinding.FoodlistBinding

class FoodList() : AppCompatActivity() {

    private lateinit var binding: FoodlistBinding
    var foodArrayList = ArrayList<Food>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FoodlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageId = intArrayOf(
            R.drawable.chicken_schnitzel,
            R.drawable.salt_and_pepper_steak,
            R.drawable.weeknight_porchetta
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

            val food = Food(names[i],descs[i],imageId[i])
            foodArrayList.add(food)
        }
        binding.foodsList.adapter= MyAdapter(this,foodArrayList)

        // Click lestener to list item
        binding.foodsList.isClickable = true
        binding.foodsList.setOnItemClickListener { parent, view, position, id ->

            val name = names[position]
            val desx = descs[position]
            val imageId = imageId[position]

            val i = Intent(this,FoodsScreen::class.java)
            i.putExtra("name",name)
            i.putExtra("desc",desx)
            i.putExtra("imageId", imageId)
            startActivity(i)
        }


    }
}