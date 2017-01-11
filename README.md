[![Release](https://img.shields.io/github/release/jitpack/android-example.svg?label=Jitpack)](https://jitpack.io/#jitpack/android-example)

# Android Material Color Library

Android library for quick and easy conversions between color spaces (RGB, HSV, HSL, etc...) and for creating a material color palette based on a single color.

## Example code

### Conversion between color spaces
```
  ColorModel color = new HEXColor("#ffff0000")
                        .toHSV().setSaturation(0.6f)
                                .setHue(190f)
                        .toHSL().setLightness(0.5f);
```

### Generating Material Colors Programmatically
```
  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
  MaterialColor materialColor = MaterialColor.random(true);
  toolbar.setBackgroundColor(materialColor.get500());
  toolbar.setTitleTextColor(materialColor.getTextBlackWhite());

```



## Integration with gradle

Example Android library project that works with jitpack.io.
Also see the guide for [building Android projects](https://github.com/jitpack/jitpack.io/blob/master/ANDROID.md)

https://jitpack.io/#jitpack/android-example

Add it to your build.gradle with:
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
and:

```gradle
dependencies {
    compile 'com.github.jitpack:android-example:{latest version}'
}
```
