package com.example.shemajamebeli4redo.extensions

import android.util.Log

fun String.getInitials(): String {
    val reversed = this.split(' ').reversed()
    val surnameSize = reversed[0].length
    val reversedStr = reversed.joinToString(" ")
    return reversedStr.subSequence(0, surnameSize + 2).toString()


}