package com.example.scannertest

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import com.example.scannertest.databinding.OrderBinding
import com.google.firebase.database.*


private const val CAMERA_REQUEST_CODE=101


class MainActivity : AppCompatActivity() {

     private var database =FirebaseDatabase.getInstance()
     private var refData :DatabaseReference=database.getReference()
     private var isInsertSuccess = false
    lateinit var tv_lokalID: TextView
    lateinit var tv_tischID: TextView

    private lateinit var binding: OrderBinding

    private val itemList: Array<String>
        get() = arrayOf(
            "Pizza",
            "Pasta",
            "Döner",
            "Pizza",
            "Pasta",
            "Bier",
            "Reis",
            "Dessert",
            "Pizza",
            "Pasta",
            "Döner",
            "Reis",
            "Dessert",
            "Pizza",
            "Pasta",
            "Döner",
            "Reis",
            "Dessert",
            "Reis",
            "Dessert",
            "Trinken"
        )




    private lateinit var codeScanner: CodeScanner
    private val SPLASH_TIME_OUT = 300L

    lateinit var scann:JsonQRCode

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





        setContentView(R.layout.activity_main)
        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)
        val tv_view = findViewById<TextView>(R.id.tv_textView)




        codeScanner = CodeScanner(this, scannerView)
        setupPermission()
        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.TWO_DIMENSIONAL_FORMATS// list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.CONTINUOUS // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        var bestellungen:Lokal=Lokal("hans",1)



        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {

                    tv_view.text = it.text
                    scann = JsonQRCode(tv_view.text.toString())

                    if (scann.checkQR()) {



                       setContentView(R.layout.loading)
                            Handler().postDelayed(
                                {
                                    val i = Intent(this@MainActivity, HomeActivity::class.java)
                                    wennScannTrue()
                                }, SPLASH_TIME_OUT)






                    } else {
                        tv_view.text = "Dies ist kein QR Code von uns"
                    }
                }

        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Log.e("Main", "Camera initialization error ${it.message}")

            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    private fun setupGridView() {
        val adapter = ImageListAdapter(this, R.layout.itemlist, itemList)
        binding.gridview.adapter = adapter

        binding.gridview.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                Toast.makeText(
                    this@MainActivity, itemList.get(position) ,
                    Toast.LENGTH_SHORT
                ).show()
            }
    }


    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setupPermission(){
        val permission =ContextCompat.checkSelfPermission(this,
        android.Manifest.permission.CAMERA)

        if (permission!= PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_REQUEST_CODE-> {
                if (grantResults.isEmpty()|| grantResults[0]!= PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Kamera freigeben", Toast.LENGTH_SHORT)
                }else{
                println("test")
                }
            }
        }
    }


    fun wennScannTrue(){

        scann.getID()



        println("startbestellung")



        binding = OrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupGridView()



//
       // tv_tischID = findViewById(R.id.tischID)
       // tv_lokalID = findViewById(R.id.lokalID)
       // tv_lokalID.text = scann.toStringID(scann.lokalQR)
       // tv_tischID.text = scann.toStringID(scann.tischQR)


        //findViewById<Button>(R.id.button).setOnClickListener{
        println("pfeife")




        refData.addValueEventListener(RealTimeDB(scann).postListener)


        RealTimeDB(scann).insert(
            BestellungTisch(
                "",
                scann.toStringID(scann.lokalQR),
                scann.toStringID(scann.tischQR),
                null,
                false,
                true
            ), scann
        )


        setContentView(binding.root)


        //  println(Thread.currentThread().name
        //}
    }














}
