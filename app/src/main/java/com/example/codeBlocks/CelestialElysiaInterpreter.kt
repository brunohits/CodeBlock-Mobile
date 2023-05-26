package com.example.codeBlocks

import com.example.OurMobile.R
import java.util.HashMap

class CelestialElysiaInterpreter(val varHashMap: HashMap<String, Any>,
                                 val commandList: MutableList<String>) {
    val tokenHashMap = hashMapOf(
        R.string.var_for_token.toString() to VariableToken::class,
        R.string.equals_for_token.toString() to EqualsToken::class,
        R.string.expr_for_token.toString() to ExpressionToken::class,
        R.string.callout_for_token.toString() to CallOutToken::class,
        R.string.if_for_token.toString() to IfToken::class,
        R.string.endif_for_token.toString() to EndIfToken::class,
        R.string.for_for_token.toString() to ForToken::class,
        R.string.endfor_for_token.toString() to EndForToken::class,
        R.string.array_for_token.toString() to ArrayToken::class
    )
    var calloutList = mutableListOf<String>()
    var stack = ArrayDeque<Double>()
    var stringPoint: Int = 0
    var forStack = ArrayDeque<Int>()

    fun interprete(){
        val tokenRegex = Regex("<\\w+")
        while(stringPoint<commandList.size){
            val tokenName = tokenRegex.find(commandList[stringPoint])!!.value
            val tokenType = tokenHashMap.get(tokenName)
            val tokenObject = tokenType?.java?.newInstance() ?: throw IllegalArgumentException("Invalid token type")
            tokenObject.command(commandList[stringPoint],this)
            stringPoint++
        }
    }

}