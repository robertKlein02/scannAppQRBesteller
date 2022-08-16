package com.example.scannertest

import android.widget.TextView

class JsonQRCode(var geleseneDatei:String) {

    var lokalQR=mutableListOf<String>()
    var tischQR=mutableListOf<String>()



    fun checkQR():Boolean{
        if (geleseneDatei.first().toString()=="x"&&geleseneDatei.last().toString()=="z") return true
        else return false
    }

    fun getID(){
        var isX=false
        var isY=false

        for (i in geleseneDatei){

            if (isX){
                lokalQR.add(i.toString())
            }
            if (isY){
                tischQR.add(i.toString())
            }
            if (i.toString()=="x") isX=true
            if (i.toString()=="y"){
                isX=false
                isY=true
            }

        }

    }

    fun toStringID(mutableList: MutableList<String>):String{
        var string:String=""
        for (i in mutableList){
            if (i.toString()!="y"&&i.toString()!="z")string=string+i
        }
        return string
    }







}