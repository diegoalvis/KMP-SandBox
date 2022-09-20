package com.diegoalvis.sandbox

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}