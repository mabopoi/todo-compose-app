package com.example.todoapp.common

enum class Times(val valueInMilis: Long) {
    Minutes(valueInMilis = (60 * 1000).toLong()),
    Hours(valueInMilis = (60 * 60 * 1000).toLong()),
    Days(valueInMilis = (24 * 60 * 60 * 1000).toLong())
}