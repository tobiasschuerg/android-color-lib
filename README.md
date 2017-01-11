[![master](https://jitpack.io/v/tobiasschuerg/android-color-lib.svg)](https://jitpack.io/#tobiasschuerg/android-color-lib)

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


https://jitpack.io/#jitpack/android-example

**Step 1.** Add the JitPack repository to your build file
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
**Step 2.** Add the dependency

```gradle
dependencies {
        compile 'com.github.tobiasschuerg:android-color-lib:-SNAPSHOT'
}
```

Also see the guide for [building Android projects](https://github.com/jitpack/jitpack.io/blob/master/ANDROID.md)
