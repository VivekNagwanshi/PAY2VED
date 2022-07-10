package com.jmcpapertech.jmcapp.utility

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jmcpapertech.jmcapp.singleton.Data
import com.jmcpapertech.jmcapp.R
import com.jmcpapertech.jmcapp.core.BaseActivity

class ImageViewerActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_image)
        val img = intent.getStringExtra("currentUrl")
        val image = findViewById<ImageView>(R.id.productImg)
        Glide.with(image).load(img).centerInside().into(image)
    }
}