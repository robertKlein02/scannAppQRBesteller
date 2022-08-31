package com.example.scannertest.Elemente

import com.example.scannertest.Elemente.Items


class BestellungTisch(var bestellungID: String = "generated", var lokalID:String="", var tischID:String="", var bestellListe: MutableList<Items>?, var bezahlt:Boolean=false, var belegt:Boolean=false) {



}