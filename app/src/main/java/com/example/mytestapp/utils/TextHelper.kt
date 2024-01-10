package com.example.mytestapp.utils

class TextHelper {
    companion object {
        fun isNotEmptyStrings(vararg s: String?): Boolean {
            val list = s.filter { s ->
                if (s == null) {
                    return false
                }
                s.isNotBlank()
            }
            return list.size == s.count()
        }
    }
}