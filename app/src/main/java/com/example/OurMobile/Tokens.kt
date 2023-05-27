package com.example.OurMobile

import Expression

interface IToken {
    fun command(input: String, program: ViNiInterpreter) {

    }

    var regex: Regex
    var returnType: String
}
//Каждый токен представляет определенную операцию:

//токен для создания переменной.
class VariableToken : IToken {

    override var regex = Regex("(?<=(^<variable:)).+(?=>$)")
    override var returnType = "void"

    //command берёт из строки имя переменной и ее тип
    // создает переменную в хэш-таблице varHashMap
    // объекта CelestialElysiaInterpreter и присваивает ей значение
    override fun command(input: String, program: ViNiInterpreter) {
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

//токен для присваивания значения переменной
class EqualsToken : IToken {

    override var regex = Regex("(?<=(^<equals:)).+,<.+>(?=>$)")
    private var tokenRegex = Regex("<\\w+")
    override var returnType = "void"
    private val arrayRegex = Regex("^\\w+\\[.+]$")
    private val arrayNameRegex = Regex("^\\w+(?=\\[)")
    private val arrayExpressionRegex = Regex("(?<=(\\[)).+(?=]$)")

    //command извлекает из строки имя переменной и выражение,
    override fun command(input: String, program: ViNiInterpreter) {
        var varName: String?
        val processedInput: String?
        val match = regex.find(input)
        processedInput = match?.value

        val arguments = processedInput!!.split(",")
        val tokenName = tokenRegex.find(arguments[1])!!.value
        val tokenType = program.tokenHashMap[tokenName]
        val tokenObject =
            tokenType?.java?.newInstance() ?: throw IllegalArgumentException("Invalid token type")
        tokenObject.command(arguments[1], program)

        varName = arguments[0]

        //вычисляет значение с помощью класса Expression, учитывая,
        // когда переменная является массивом и присваивает
        // полученное значение переменной
        if (arguments[0].matches(arrayRegex)) {
            val expression = Expression()
            val arrayIndex = expression.evaluateRPN(
                expression.toRPN(
                    arrayExpressionRegex.find(arguments[0])!!.value, program.varHashMap
                )
            ).toInt().toString()
            val arrayName = arrayNameRegex.find(arguments[0])!!.value
            val arrayToken = "$arrayName[$arrayIndex]"
            varName = arrayToken
        }

        val expressionValue = program.stack.removeFirst()
        val variableValue = program.varHashMap[varName]
        if (variableValue!!::class.java.simpleName == "Integer") {
            program.varHashMap[varName] = expressionValue.toInt()
        } else {
            program.varHashMap[varName] = expressionValue
        }
    }
}

//ExpressionToken-вычисляет арифметическое выражение
class ExpressionToken : IToken {

    override var regex = Regex("(?<=(<expression:)).+(?=>)")
    override var returnType = "int"

    //command извлекает из строки выражение, преобразует его в опс
    // с помощью Expression и вычисляет его значение, добавляя его в стек
    override fun command(input: String, program: ViNiInterpreter) {
        val expressionString: String?
        val match = regex.find(input)
        expressionString = match?.value

        val expression = Expression()
        val expressionValue =
            expression.evaluateRPN(expression.toRPN(expressionString!!, program.varHashMap))
        program.stack.add(expressionValue)
    }
}

//CallOutToken -для вызова внешней функции.
class CallOutToken : IToken {

    override var regex = Regex("(?<=(^<callout:)).+(?=>$)")
    private var tokenRegex = Regex("<\\w+")

    // command извлекает из строки имя функции,
    // создает объект соответствующего класса и вызывает в себе command,
    // добавляя полученное значение в список вызовов calloutList
    // объекта SpeedRunIntepreter
    override fun command(input: String, program: ViNiInterpreter) {

        val processedInput: String?
        val match = regex.find(input)
        processedInput = match?.value
        val tokenName = tokenRegex.find(processedInput!!)!!.value
        val tokenType = program.tokenHashMap[tokenName]
        val tokenObject =
            tokenType?.java?.newInstance() ?: throw IllegalArgumentException("Invalid token type")

        tokenObject.command(processedInput, program)
        program.calloutList.add(program.stack.removeFirst().toString())
    }

    override var returnType = "void"
}

//для токена иф
class IfToken : IToken {

    override var regex = Regex("(?<=(^<if:)).+(?=>\$)")
    override var returnType = "void"
    private var tokenRegex = Regex("<\\w+")
    private var endifRegex = Regex("<endif:\\d+>")
    private var idRegex = Regex("(?<=(^<endif:))\\d+(?=>$)")

    override fun command(input: String, program: ViNiInterpreter) {
        //извлекаем из строки аргументы операторов
        val processedInput: String?
        val match = regex.find(input)
        processedInput = match?.value
        val arguments = processedInput!!.split(",")
        //создаем объекты токенов для каждого аргумента и вызываем их метод command
        val token1Name = tokenRegex.find(arguments[0])!!.value
        val token1Type = program.tokenHashMap[token1Name]
        val token1Object =
            token1Type?.java?.newInstance() ?: throw IllegalArgumentException("Invalid token type")
        token1Object.command(arguments[0], program)

        val token2Name = tokenRegex.find(arguments[1])!!.value
        val token2Type = program.tokenHashMap[token2Name]
        val token2Object =
            token2Type?.java?.newInstance() ?: throw IllegalArgumentException("Invalid token type")
        token2Object.command(arguments[1], program)

        val value2 = program.stack.removeFirst()
        val value1 = program.stack.removeFirst()
        var boolValueIf = true
        when (arguments[2]) {
            "==" -> boolValueIf = value1 == value2
            "!=" -> boolValueIf = value1 != value2
            ">" -> boolValueIf = value1 > value2
            "<" -> boolValueIf = value1 < value2
            ">=" -> boolValueIf = value1 >= value2
            "<=" -> boolValueIf = value1 <= value2
        }
        //Если boolValue стало ложно, то мы перебираем команды программы,
        //начиная со stringPoint и ищем endif с соотв-им идентификатором
        if (!boolValueIf) {
            for (n in program.stringPoint until program.commandList.size) {
                //Если мы находим этот токен,то устанавливаем stringPoint на его позицию,
                //чтобы пропустить блок кода, который следует в операторе if.
                if ((program.commandList[n].matches(endifRegex)) && (idRegex.find(program.commandList[n])!!.value) == arguments[3]) {
                    program.stringPoint = n
                }
            }
        }
    }
}

//class ElseToken : IToken{
//    override var regex = Regex("^<else")
//    override var returnType = "void"
//}


class EndIfToken : IToken {
    override var regex = Regex("^<endif")
    override var returnType = "void"
}


class ForToken : IToken {
    override var regex = Regex("(?<=(^<for:)).+(?=>$)")
    override var returnType = "void"
    override fun command(input: String, program: ViNiInterpreter) {
        //command извлекает из строки аргументы оператора.
        val processedInput: String?
        val match = regex.find(input)
        processedInput = match?.value
        // Затем он помещает стартовое значение счетчика цикла наверх forStack(волшебное что-то вау)
        val arguments = processedInput!!.split(",")
        program.forStack.add(arguments[0].toInt())
    }
}

//завершение цикла for
class EndForToken : IToken {

    override var regex = Regex("(?<=(^<endfor:)).+(?=>$)")
    private var forRegex = Regex("<for:.+")
    override var returnType = "void"

    //идентификатор цикла
    private var idRegex = Regex("(?<=(,))\\d+(?=>$)")

    override fun command(input: String, program: ViNiInterpreter) {
        val processedInput: String?
        val match = regex.find(input)
        processedInput = match?.value
        //уменьшаем значение счетчика цикла наверху стека forStack
        program.forStack[0] = program.forStack[0] - 1
        //Если значение больше нуля, то перебираем команды программы,
        //начиная с текущей позиции `stringPoint`, и ищем токен for с соотв-им идент-ом
        if (program.forStack[0] > 0) {
            for (n in 0 until program.commandList.size) {
                //Если мы находим этот токен, то устанавливаем stringPoint на следующую позицию,
                // чтобы начать новую итерацию цикла
                if (program.commandList[n].matches(forRegex) && idRegex.find(program.commandList[n])!!.value == processedInput) {
                    program.stringPoint = n + 1
                }
            }
        }
        //Если значение счетчика равно нулю, то мы удаляем его из стека
        else {
            program.forStack.removeFirst()
        }
    }
}

//токен для создания массива переменных
class ArrayToken : IToken {
    override var regex = Regex("(?<=(^<array:)).+(?=>\$)")
    override var returnType = "void"

    override fun command(input: String, program: ViNiInterpreter) {
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
        //создаем переменные с указанным именем и типом данных и добавляем
        // их в хэш-таблицу varHashMap
        for (n in 1..arrayCapacity) {
            program.varHashMap[arrayName + "[" + (n - 1).toString() + "]"] = variableType
        }
    }
}