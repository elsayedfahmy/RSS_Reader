package com.example.rssreader.Interfacses

import android.view.View

interface Itemclicklistner {
    fun onclick(item:View,postion:Int,islongclick:Boolean):Unit
}