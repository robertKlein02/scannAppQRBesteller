package com.example.scannertest.Datenbank

import android.content.ContentValues
import android.util.Log
import com.example.scannertest.Elemente.BestellungTisch
import com.example.scannertest.Scanner.JsonQRCode
import com.google.firebase.database.*

class RealTimeDB(var scann: JsonQRCode) {


    private var database = FirebaseDatabase.getInstance()
     var refData : DatabaseReference =database.getReference()
    private var isInsertSuccess = false




    val postListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Get Post object and use the values to update the UI
            var post:Boolean = dataSnapshot.child("BestellungenTisch").child("${scann.toStringID(scann.lokalQR)+"X"+scann.toStringID(scann.tischQR)}").child("belegt").value.toString().toBoolean()
            println(post)

        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Post failed, log a message
            Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
        }

    }


    fun insert(bestellungen: BestellungTisch, scann: JsonQRCode) {

        val value = BestellungTisch(
            bestellungen.lokalID+"X"+bestellungen.tischID,
            bestellungen.lokalID,
            bestellungen.tischID,
            bestellungen.bestellListe,
            bestellungen.bezahlt,
            bestellungen.belegt
        )



        refData.child("BestellungenTisch").child(scann.toStringID(scann.lokalQR)+"X"+scann.toStringID(scann.tischQR)).setValue(value).addOnCompleteListener {
            isInsertSuccess = true

        }.addOnFailureListener {
            isInsertSuccess = false

        }

    }
}