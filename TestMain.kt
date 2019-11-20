package test.myapplication

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.io.File


object TestMain {

    private val words = arrayOf(
        "start",
        "citizen",
        "flour",
        "circle",
        "petty",
        "neck",
        "seem",
        "lake",
        "page",
        "color",
        "ceiling",
        "angle",
        "agent",
        "mild",
        "touch",
        "bite",
        "cause",
        "finance",
        "greet",
        "eat",
        "minor",
        "echo",
        "aviation",
        "baby",
        "role",
        "surround",
        "incapable",
        "refuse",
        "reliable",
        "imperial",
        "outer",
        "liability",
        "struggle",
        "harsh",
        "coerce",
        "front",
        "strike",
        "rage",
        "casualty",
        "artist",
        "ex",
        "transaction",
        "parking",
        "plug",
        "formulate",
        "press",
        "kettle",
        "export",
        "hiccup",
        "stem",
        "exception",
        "report",
        "central",
        "cancer",
        "volunteer",
        "professional",
        "teacher",
        "relax",
        "trip",
        "fountain",
        "effect",
        "news",
        "mark",
        "romantic",
        "policy",
        "contemporary",
        "conglomerate",
        "cotton",
        "happen",
        "contempt",
        "joystick",
        "champagne",
        "vegetation",
        "bat",
        "cylinder",
        "classify",
        "even",
        "surgeon",
        "slip",
        "private",
        "fox",
        "gravity",
        "aspect",
        "hypnothize",
        "generate",
        "miserable",
        "breakin",
        "love",
        "chest",
        "split",
        "coach",
        "pound",
        "sharp",
        "battery",
        "cheap",
        "corpse",
        "hobby",
        "mature",
        "attractive",
        "rock"
    )

    @JvmStatic
    fun main(arg: Array<String>) {
       // task01()
        // task02()
        task03Async()
        //task03FizzBuzzAsync()
      //  witeToFile()
    }

    /**
     * Print numbers from 1 to 100 to the console, but for each number also print
     * a random word using the function getRandomWordSync
     */
    private fun task01() {
        val strBuilder = StringBuilder();

        for (i in 1..100) {
            val print= "$i :" + getRandomWordSync()
            println(print)

            strBuilder.appendln(print);
        }

        witeToFile(strBuilder.toString())
    }


    private fun getRandomWordSync(): String {
        val index = (0..99).random()
        return words[index]
    }

    //================================================================

    /**
     * modify your code to be a "Fizz Buzz" program. That is, print the numbers as in the previous step,
     * but for multiples of three, print "Fizz" (instead of the random word), for multiples of five,
     * print "Buzz" and for numbers which are both multiples of three and five, print "FizzBuzz".
     */
    private fun task02() {
        for (i in 1..100) {
            println("$i :" + getFizzBuzz(i))
        }
    }


    private fun getFizzBuzz(index: Int): String {
        return if (index % 5 == 0 && index % 3 == 0)
            "FizzBuzz"
        else if (index % 3 == 0)
            "Fizz"
        else if (index % 5 == 0)
            "Buzz"
        else getRandomWordSync()

    }

    //================================================================

    /**
     * Create a version of steps 1 and 2 using the asynchronous function,
     * getRandomWord. This function returns a Promise, which\
     * resolves to a random word string.
     */
    private fun task03Async() = runBlocking {
        val startTime = System.currentTimeMillis()
        for (i in 1..100) {
            async { printRandomWordsAsync(i) }

        }
        val endTime = System.currentTimeMillis()
        println("Time taken: ${endTime - startTime}")
    }

    private suspend fun printRandomWordsAsync(index: Int) {
        println("$index :" + getRandomWord(true))
    }


    private suspend fun getRandomWord(slow: Boolean = false): String {
        val index = (0..199).random()
//        val word = if (index < words.size) {
//            words[index]
//        } else {
//            "Fatal: Index out of bounds"
//        }


        val word = try {
            words[index]
        } catch (e: Exception) {
            "It shouldn't break anything!"
        }

        if (slow)
            delay(500)
        return word
    }
    //**************


    /**
     * async FizzBuzz
     */
    private fun task03FizzBuzzAsync() = runBlocking {
        val startTime = System.currentTimeMillis()
        for (i in 1..100) {
            async { printRandomWordsWithFizzBuzzAsync(i) }

        }
        val endTime = System.currentTimeMillis()
        println("Time taken: ${endTime - startTime}")
    }

    private suspend fun printRandomWordsWithFizzBuzzAsync(index: Int) {
        println("$index :" + getFizzBuzzAsync(index))
    }


    private suspend fun getFizzBuzzAsync(index: Int): String {
        return if (index % 5 == 0 && index % 3 == 0)
            "FizzBuzz"
        else if (index % 3 == 0)
            "Fizz"
        else if (index % 5 == 0)
            "Buzz"
        else getRandomWord()

    }

    fun witeToFile(value: String){
        File("file.txt").printWriter().use { out ->
            out.println(value)
        }
    }

}
