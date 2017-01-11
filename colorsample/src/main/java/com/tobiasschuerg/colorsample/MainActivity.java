package com.tobiasschuerg.colorsample;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.tobiasschuerg.color.material.MaterialColor;
import com.tobiasschuerg.color.models.ColorModel;
import com.tobiasschuerg.color.models.HEXColor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MaterialColor materialColor = MaterialColor.random(true);
        toolbar.setBackgroundColor(materialColor.get500());
        toolbar.setTitleTextColor(materialColor.getTextBlackWhite());

        setStatusBarColor(materialColor.get700());


        TextView tv = (TextView) findViewById(R.id.hello);
        ColorModel color = new HEXColor("#ffff0000").toHSV().setSaturation(0.6f).setHue(190f).toHSL().setLightness(0.5f);
        tv.setTextColor(color.toColor());
    }

    private void setStatusBarColor(@ColorInt int color) {
        Window window = getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(color);
    }

}
