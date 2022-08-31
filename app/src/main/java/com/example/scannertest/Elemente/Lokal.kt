package com.example.scannertest.Elemente

class Lokal (var name:String,var lokalID:Int){

    var bestellNrListe= mutableListOf<Int>()
    var betsellNR=funbestellNR()

    fun funbestellNR():Int{
        bestellNrListe.add(bestellNrListe.size)
        return bestellNrListe.size-1
    }
}