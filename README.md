# Android Color Library

[![master](https://jitpack.io/v/tobiasschuerg/android-color-lib.svg)](https://jitpack.io/#tobiasschuerg/android-color-lib)

Android library for **color space conversions** (HSV, HSL, HEX) and **Material Design color palette** generation.

<img src="https://github.com/tobiasschuerg/android-color-lib/blob/master/screenshot.png" width="300">

## Features

- 🎨 **Color Space Conversions**: Convert between RGB, HSV, HSL, HEX
- 📱 **Material Design Palettes**: Generate complete Material color schemes
- ♿ **Accessibility Support**: WCAG contrast ratio calculations
- 🚀 **Jetpack Compose Compatible**: Built-in Compose Color support

## Quick Start

### Color Conversions
```kotlin
val color = HEXColor("#FF2196F3")
    .toHSV()
    .setSaturation(0.8f)
    .toHSL()
    .setLightness(0.6f)

textView.setTextColor(color.toColor())
```

### Material Color Palettes
```kotlin
val materialColor = MaterialColor.fromColorInt(Color.BLUE)
toolbar.setBackgroundColor(materialColor.get500().toColor())
fab.setBackgroundColor(materialColor.get600().toColor())

// Or from Jetpack Compose
val composeMaterial = MaterialColor.fromComposeColor(Color.Blue)
```

### Accessibility
```kotlin
val backgroundColor = HEXColor("#2196F3")
val textColor = backgroundColor.getContrastingTextColor()

// Check WCAG compliance
if (backgroundColor.meetsWCAG_AA(textColor)) {
    // Color combination is accessible
}
```

## Installation

Add to your `build.gradle`:

```gradle
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation 'com.github.tobiasschuerg:android-color-lib:latest-version'
}
```

## Requirements

- **Min SDK**: 21 (Android 5.0)
- **Compile SDK**: 36
- **Kotlin**: 2.2.20+
- **Jetpack Compose**: Optional, but supported

---

*Simple, powerful, accessible color handling for Android.*
