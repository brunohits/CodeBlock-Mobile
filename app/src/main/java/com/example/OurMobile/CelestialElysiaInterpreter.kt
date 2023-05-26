package com.example.OurMobile

import java.util.HashMap

class CelestialElysiaInterpreter(
    val varHashMap: HashMap<String, Any>,
    val commandList: MutableList<String>
) {
    val tokenHashMap = hashMapOf(
        "<variable" to VariableToken::class,
        "<equals" to EqualsToken::class,
        "<expression" to ExpressionToken::class,
        "<callout" to CallOutToken::class,
        "<if" to IfToken::class,
        "<endif" to EndIfToken::class,
        "<for" to ForToken::class,
        "<endfor" to EndForToken::class,
        "<array" to ArrayToken::class
    )
    var calloutList = mutableListOf<String>()
    var stack = ArrayDeque<Double>()
    var stringPoint: Int = 0
    var forStack = ArrayDeque<Int>()

    fun interprete() {
        val tokenRegex = Regex("<\\w+")
        while (stringPoint < commandList.size) {
            val tokenName = tokenRegex.find(commandList[stringPoint])!!.value
            val tokenType = tokenHashMap.get(tokenName)
            val tokenObject = tokenType?.java?.newInstance()
                ?: throw IllegalArgumentException("Invalid token type")
            tokenObject.command(commandList[stringPoint], this)
            stringPoint++
        }
    }

}