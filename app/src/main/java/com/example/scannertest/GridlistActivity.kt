package com.example.scannertest

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import com.example.scannertest.Adapter.ImageListAdapter
import com.example.scannertest.Datenbank.RealTimeDB
import com.example.scannertest.Elemente.BestellungTisch
import com.example.scannertest.Scanner.JsonQRCode
import com.example.scannertest.databinding.*
import com.google.firebase.database.*
import java.util.concurrent.Semaphore


class GridlistActivity : AppCompatActivity() {


    private var database =FirebaseDatabase.getInstance()
    private var refData :DatabaseReference=database.getReference()
    private var isInsertSuccess = false
    var s = Semaphore(1)

    var click = ""





    private lateinit var binding: OrderBinding


    private val itemList: Array<String>
        get() = arrayOf(
            "Pizza",
            "Pasta",
            "Döner",
            "Reis",
            "Dessert",
            "Getränke",
            "Burger",
            "Nudel",
            "Cocktail",
            "Pommes",
            "Sushi",
            "Wein",
            "Bier"
        )




    var text=""



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        s.acquire()                                         //Semaphore da Screen sich 2 mal öffnet
        binding = OrderBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupGridView()
        s.release()












    }





    // Diese Funkrion Sortiert die überkategorien damit alle Geschäfte die gleiche Reihenfolge haben
    fun sortArray(array: Array<String>):Array<String> {
        val musterSort= mutableListOf<String>("Pizza", "Pasta", "Nudel", "Reis", "Döner",  "Burger", "Pommes", "Sushi", "Getränke", "Cocktail", "Wein", "Bier", "Dessert")
        var newlist = mutableListOf<String>("")

        for (i in musterSort){
            for (i2 in array){
                if (i == i2){
                    newlist.add(i)
                }
            }
        }
        newlist.removeAt(0)
        return newlist.toTypedArray()
    }



    private fun setupGridView() {
        val adapter = ImageListAdapter(this, R.layout.itemlist, sortArray(itemList))

        binding.gridview.adapter = adapter


        binding.gridview.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->


                click= this.sortArray(itemList)[position]





                openUnderMenu()
            }


    }



    fun openUnderMenu(){
        val i = Intent(this,FoodlistAcitivity::class.java)

        i.putExtra("click",click)


        startActivity(Intent(i))


    }





}
