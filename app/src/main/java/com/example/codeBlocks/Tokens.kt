package com.example.codeBlocks
import Expression

interface IToken {
    fun command(input:String, program:CelestialElysiaInterpreter){}
    var regex: Regex
    var returnType: String
}
class VariableToken : IToken{
    override var regex = Regex("(?<=(^<variable:)).+(?=>$)")
    override var returnType = "void"
    override fun command(input:String, program:CelestialElysiaInterpreter){
        val match = regex.find(input)
        val processedInput = match?.value
        val arguments = processedInput!!.split(",")
        val variableType = when (arguments[1]) {
            "int" -> 0
            "double" -> 0.0
            "string" -> ""
            else -> 0
        }
        program.varHashMap[arguments[0]] = variableType
    }
}
class EqualsToken : IToken{
    override var regex = Regex("(?<=(^<equals:)).+,<.+>(?=>$)")
    var tokenRegex = Regex("<\\w+")
    override var returnType = "void"
    val arrayRegex = Regex("^\\w+\\[.+]$")
    val arrayNameRegex = Regex("^\\w+(?=\\[)")
    val arrayExpressionRegex = Regex("(?<=(\\[)).+(?=]$)")
    override fun command(input:String, program:CelestialElysiaInterpreter){
        var varName: String?
        val processedInput: String?
        val match = regex.find(input)
        processedInput = match?.value

        val arguments = processedInput!!.split(",")
        val tokenName = tokenRegex.find(arguments[1])!!.value
        val tokenType = program.tokenHashMap.get(tokenName)
        val tokenObject = tokenType?.java?.newInstance()?: throw IllegalArgumentException("Invalid token type")
        tokenObject.command(arguments[1],program)

        varName = arguments[0]

        if(arguments[0].matches(arrayRegex)){
            val expression = Expression()
            val arrayIndex = expression.evaluateRPN(expression.toReversePolishNotation(arrayExpressionRegex.find(arguments[0])!!.value,program.varHashMap)).toInt().toString()
            val arrayName = arrayNameRegex.find(arguments[0])!!.value
            val arrayToken = "$arrayName[$arrayIndex]"
            varName = arrayToken
        }

        val expressionValue = program.stack.removeFirst()
        val variableValue = program.varHashMap[varName]
        if(variableValue!!::class.java.simpleName == "Integer"){
            program.varHashMap.put(varName,expressionValue.toInt())
        }
        else{
            program.varHashMap.put(varName,expressionValue)
        }
    }
}
class ExpressionToken : IToken{
    override var regex = Regex("(?<=(<expression:)).+(?=>)")
    override var returnType = "int"
    override fun command(input:String, program:CelestialElysiaInterpreter){
        val expressionString: String?
        val match = regex.find(input)
        expressionString = match?.value

        val expression = Expression()
        val expressionValue = expression.evaluateRPN(expression.toReversePolishNotation(expressionString!!, program.varHashMap))
        program.stack.add(expressionValue)
    }
}
class CallOutToken : IToken{
    override var regex = Regex("(?<=(^<callout:)).+(?=>$)")
    var tokenRegex = Regex("<\\w+")
    override fun command(input:String, program:CelestialElysiaInterpreter){
        val processedInput: String?
        val match = regex.find(input)
        processedInput = match?.value
        val tokenName = tokenRegex.find(processedInput!!)!!.value
        val tokenType = program.tokenHashMap.get(tokenName)
        val tokenObject = tokenType?.java?.newInstance()?: throw IllegalArgumentException("Invalid token type")
        tokenObject.command(processedInput,program)
        program.calloutList.add(program.stack.removeFirst().toString())
    }
    override var returnType = "void"
}

class IfToken : IToken{
    override var regex = Regex("(?<=(^<if:)).+(?=>\$)")
    override var returnType = "void"
    var tokenRegex = Regex("<\\w+")
    var endifRegex = Regex("<endif:\\d+>")
    var idRegex = Regex("(?<=(^<endif:))\\d+(?=>$)")
    override fun command(input:String, program:CelestialElysiaInterpreter){
        val processedInput: String?
        val match = regex.find(input)
        processedInput = match?.value

        val arguments = processedInput!!.split(",")

        val token1Name = tokenRegex.find(arguments[0])!!.value
        val token1Type = program.tokenHashMap.get(token1Name)
        val token1Object = token1Type?.java?.newInstance()?: throw IllegalArgumentException("Invalid token type")
        token1Object.command(arguments[0],program)

        val token2Name = tokenRegex.find(arguments[1])!!.value
        val token2Type = program.tokenHashMap.get(token2Name)
        val token2Object = token2Type?.java?.newInstance()?: throw IllegalArgumentException("Invalid token type")
        token2Object.command(arguments[1],program)

        val value2 = program.stack.removeFirst()
        val value1 = program.stack.removeFirst()
        var boolValue = true
        when (arguments[2]) {
            "==" -> boolValue = value1 == value2
            "!=" -> boolValue = value1 != value2
            ">" -> boolValue = value1 > value2
            "<" -> boolValue = value1 < value2
            ">=" -> boolValue = value1 >= value2
            "<=" -> boolValue = value1 <= value2
        }
        if(!boolValue){
            for(n in program.stringPoint until program.commandList.size){
                if(program.commandList[n].matches(endifRegex) && idRegex.find(program.commandList[n])!!.value == arguments[3]){
                    program.stringPoint = n
                }
            }
        }
    }
}
class ElseToken : IToken{
    override var regex = Regex("^<else")
    override var returnType = "void"
}
class EndIfToken : IToken{
    override var regex = Regex("^<endif")
    override var returnType = "void"
}

class ForToken : IToken {
    override var regex = Regex("(?<=(^<for:)).+(?=>$)")
    override var returnType = "void"
    override fun command(input:String, program:CelestialElysiaInterpreter){
        val processedInput: String?
        val match = regex.find(input)
        processedInput = match?.value

        val arguments = processedInput!!.split(",")
        program.forStack.add(arguments[0].toInt())
    }
}
class EndForToken : IToken{
    override var regex = Regex("(?<=(^<endfor:)).+(?=>$)")
    var forRegex = Regex("<for:.+")
    override var returnType = "void"
    var idRegex = Regex("(?<=(,))\\d+(?=>$)")
    override fun command(input:String, program:CelestialElysiaInterpreter){
        val processedInput: String?
        val match = regex.find(input)
        processedInput = match?.value

        program.forStack.set(0,program.forStack[0]-1)
        if(program.forStack[0]>0){
            for(n in 0..program.commandList.size-1){
                if(program.commandList[n].matches(forRegex) && idRegex.find(program.commandList[n])!!.value == processedInput){
                    program.stringPoint = n+1
                }
            }
        }
        else{
            program.forStack.removeFirst()
        }
    }
}
class ArrayToken : IToken{
    override var regex = Regex("(?<=(^<array:)).+(?=>\$)")
    override var returnType = "void"
    override fun command(input:String, program:CelestialElysiaInterpreter){
        val processedInput: String?
        val match = regex.find(input)
        processedInput = match?.value

        val arguments = processedInput!!.split(",")
        val arrayName = arguments[0]
        val arrayCapacity = arguments[1].toInt()
        val variableType = when (arguments[2]) {
            "int" -> 0
            "double" -> 0.0
            "string" -> ""
            else -> 0
        }

        for(n in 1..arrayCapacity){
            program.varHashMap[arrayName+"["+(n-1).toString()+"]"] = variableType
        }
    }
}
