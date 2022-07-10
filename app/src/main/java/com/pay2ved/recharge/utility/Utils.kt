package com.jmcpapertech.jmcapp.utility

import android.view.View

    fun View.setVisible(flag:Boolean){
        this.visibility = if (flag){
            View.VISIBLE
        }else{
            View.GONE
        }
    }