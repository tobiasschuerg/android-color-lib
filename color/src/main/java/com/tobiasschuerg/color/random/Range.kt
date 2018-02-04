package com.tobiasschuerg.color.random

data class Range(var start: Int, var end: Int) {

    fun contains(value: Int): Boolean {
        return value >= start && value <= end
    }

}