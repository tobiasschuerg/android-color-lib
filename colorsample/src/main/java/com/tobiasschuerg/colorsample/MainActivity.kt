package com.tobiasschuerg.colorsample

import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.tobiasschuerg.color.ColorCreator
import com.tobiasschuerg.color.material.MaterialColor
import com.tobiasschuerg.color.models.HEXColor
import com.tobiasschuerg.color.models.getTextBlackWhite
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val materialColor: MaterialColor = MaterialColor(ColorCreator.randomColor())
        toolbar.setBackgroundColor(materialColor.get500().toColor())
        toolbar.setTitleTextColor(materialColor.getTextBlackWhite())

        setStatusBarColor(materialColor.get700().toColor())


        val color = HEXColor("#ffff0000")
                .toHSV()
                .copy(saturation = 0.6f)
                .copy(hue = 190f)
                .toHSL()
                .copy(lightness = 0.5f)
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
