package com.diegoalvis.sandbox

class Greeting {
    fun greeting(): String {
       val string =
           """  
           Hello, ${Platform().platform}!
           There are only ${daysUntilNewYear()} days left until New Year! 🎅🏼
           """
        return string.trimIndent()
    }
}