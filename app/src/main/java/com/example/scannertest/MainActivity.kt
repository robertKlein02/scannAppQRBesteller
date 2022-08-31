package com.example.scannertest

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.telephony.TelephonyManager
import android.util.LayoutDirection
import android.util.Log
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.*
import com.budiyev.android.codescanner.*
import com.example.scannertest.Adapter.ImageListAdapter
import com.example.scannertest.Datenbank.RealTimeDB
import com.example.scannertest.Elemente.BestellungTisch
import com.example.scannertest.Scanner.JsonQRCode
import com.example.scannertest.databinding.*
import com.google.firebase.database.*
import java.util.concurrent.Semaphore


private const val CAMERA_REQUEST_CODE=101
var s = Semaphore(1)

class MainActivity : AppCompatActivity() {


    private var database =FirebaseDatabase.getInstance()
    private var refData :DatabaseReference=database.getReference()
    private var isInsertSuccess = false


    lateinit var tv_lokalID: TextView
    lateinit var tv_tischID: TextView
    var click = ""

    private lateinit var names:String




    private lateinit var codeScanner: CodeScanner


    private val SPLASH_TIME_OUT = 300L

    var text=""

    lateinit var scann: JsonQRCode

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_main)
        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)
        val tv_view = findViewById<TextView>(R.id.tv_textView)
        val buttinhier=findViewById<Button>(R.id.oderhier)
        val tischvisi=findViewById<TextView>(R.id.tischidvisible)
        val lokalvisi=findViewById<TextView>(R.id.lokalidvisible)
        val icon=findViewById<ImageView>(R.id.imageIcon)





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

//// #########################  Um Scan zu überspringen ###################################
//
//        scann= JsonQRCode("x15y51z")
//
//
//
//        setContentView(R.layout.loading)
//        Handler().postDelayed(
//            {
//                val i = Intent(this@MainActivity, MainActivity::class.java)
//                wennScannTrue()
//            }, SPLASH_TIME_OUT)
//
//// #######################################################################################


        buttinhier.setOnClickListener {
            wennScannTrue()
        }


        // Callbacks
        if (codeScanner.decodeCallback==null) {                              // Verzweigung da sich Screen Gridlist zwei mal öffnet
            codeScanner.decodeCallback = DecodeCallback {
                runOnUiThread {

                    scann = JsonQRCode(it.text)



                    if (scann.checkQR()) {

                        text = scann.geleseneDatei

                        wennScannTrue()
                        buttinhier.isVisible = true
                        scannerView.isVisible = false
                        tischvisi.isVisible = true
                        lokalvisi.isVisible = true


                        val param = icon.layoutParams as ViewGroup.MarginLayoutParams
                        param.setMargins(icon.marginLeft, 400, icon.marginRight, icon.marginBottom)
                        icon.layoutParams = param //


                        tv_view.text = "Sie sind angemeldet an:"
                        tischvisi.text = "TischID: ${scann.toStringID(scann.tischQR)}"
                        lokalvisi.text = "LokalID: ${scann.toStringID(scann.lokalQR)}"


                    } else {
                        tv_view.text = "Dies ist kein QR Code von uns"
                    }

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


        val i = Intent(this,GridlistActivity::class.java)

        i.putExtra("click",scann.geleseneDatei)


        startActivity(Intent(i))

    }















}
