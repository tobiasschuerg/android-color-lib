package com.tobiasschuerg.colorsample

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import com.tobiasschuerg.color.ColorCreator
import com.tobiasschuerg.color.helper.setAccentColor
import com.tobiasschuerg.color.material.MaterialColor
import com.tobiasschuerg.color.models.ColorModel
import com.tobiasschuerg.color.models.HEXColor
import com.tobiasschuerg.color.models.getTextBlackWhite
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val materialColor = MaterialColor(ColorCreator.randomColor())
        val color1 = materialColor.get500()
        toolbar.setBackgroundColor(color1.toColor())
        toolbar.setTitleTextColor(materialColor.getTextBlackWhite())

        setStatusBarColor(materialColor.get700().toColor())


        val color: ColorModel = HEXColor("#ffaabbff")
                .toHSV()
                .copy(saturation = 0.6f)
                .copy(hue = 190f)
                .toHSL()
                .copy(lightness = 0.5f)
        hello.setTextColor(color.toColor())


        first_name_layout.setAccentColor(color1.toColor())
        last_name_layout.setAccentColor(color1.toColor())


        restart_button.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
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
