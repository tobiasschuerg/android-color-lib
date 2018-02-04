package com.tobiasschuerg.color.random

data class Options(
        var hue: Int = 0,
        var saturationType: SaturationType,
        var luminosity: Luminosity
)
