package com.example.colabore.utils

import android.text.InputFilter
import android.text.Spanned

sealed class CustomFilter : InputFilter {

    class LimitCharacters(private val regex: Regex) : CustomFilter() {

        constructor(regex: String): this(regex.toRegex())

        override fun filter(source: CharSequence, start: Int, end: Int, p3: Spanned?, p4: Int, p5: Int): CharSequence? {
            return if (source.toString().substring(start, end).matches(regex)) {
                null
            } else {
                ""
            }
        }
    }

    class RemoveCharacters(private val regex: Regex) : CustomFilter() {

        constructor(regex: String): this(regex.toRegex())

        override fun filter(source: CharSequence, start: Int, end: Int, p3: Spanned?, p4: Int, p5: Int): CharSequence? {
            return if (source.toString().substring(start, end).matches(regex)) {
                ""
            } else {
                null
            }
        }
    }

    object RemoveWhiteSpaces : CustomFilter() {

        override fun filter(source: CharSequence, start: Int, end: Int, p3: Spanned?, p4: Int, p5: Int): CharSequence? {
            for (i in start until end) {
                if (Character.isWhitespace(source[i])) {
                    return ""
                }
            }
            return null
        }
    }
    
    

}