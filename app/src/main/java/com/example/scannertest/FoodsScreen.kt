package com.example.scannertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.scannertest.databinding.ActivityFoodsScreenBinding

class FoodsScreen() : AppCompatActivity() {

    private lateinit var binding: ActivityFoodsScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val desc = intent.getStringExtra("desc")
        val image = intent.getIntExtra("imageId", R.drawable.chicken_schnitzel)


        binding.foodDesc.text =desc
        binding.foodName.text =name
        binding.imgFood.setImageResource(image)


    }
}