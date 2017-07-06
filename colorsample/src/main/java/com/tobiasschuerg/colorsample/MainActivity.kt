package com.tobiasschuerg.colorsample

import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.tobiasschuerg.color.material.MaterialColor
import com.tobiasschuerg.color.models.HEXColor
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val materialColor = MaterialColor.random(true)
        toolbar.setBackgroundColor(materialColor.get500())
        toolbar.setTitleTextColor(materialColor.textBlackWhite)

        setStatusBarColor(materialColor.get700())


        val color = HEXColor("#ffff0000").toHSV().setSaturation(0.6f).setHue(190f).toHSL().setLightness(0.5f)
        hello.setTextColor(color.toColor())
    }

    private fun setStatusBarColor(@ColorInt color: Int) {
        val window = window
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        // finally change the color
        window.statusBarColor = color
    }

}
