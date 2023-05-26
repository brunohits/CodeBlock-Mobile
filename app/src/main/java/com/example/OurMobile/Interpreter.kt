@Suppress("NAME_SHADOWING")
class Expression {
    fun toRPN(expression: String, variables: Map<String, Any>): String {
        val stack = mutableListOf<String>()
        val output = mutableListOf<String>()
        val operators = setOf("+", "-", "*", "/")
        val arrayRegex = Regex("^\\w+\\[.+]$")
        val arrayNameRegex = Regex("^\\w+(?=\\[)")
        val arrayExpressionRegex = Regex("(?<=(\\[)).+(?=]$)")

        expression.split(" ").forEach { token ->
            //Если токен -оператор, то из стека извлекаются все операторы и
            // добавляются в выходную последовательность
            if (token in operators) {
                while (stack.isNotEmpty() && stack.last() in operators && priority(stack.last()) >= priority(
                        token
                    )
                ) {
                    output.add(stack.removeLast())
                }
                // затем текущий оператор добавляется в стек
                stack.add(token)
            } else if (token == "(") {
                stack.add(token)
            } else if (token == ")") {
                //Если токен  ),то
                //из стека извлекаются все операторы до открывающейся (
                // и добавляются в output, а откр.( удаляется из стека.
                while (stack.isNotEmpty() && stack.last() != "(") {
                    output.add(stack.removeLast())
                }
                stack.removeLast()
            } else {
                //Если токен не оператор и не скобка, то проверяем,
                //является ли он числом или переменной
                //если число-в output
                if (token.toDoubleOrNull() != null) {
                    output.add(token)
                }
                // если токен-массив, то сначала из регулярного выражения arrayExpressionRegex
                // получаем индекс массива, затем рекурсивно преобразуем выражение в ОПС,
                // чтобы вычислить значение функцией evaluateReversePolishNotation
                // И наконец из регулярного выражения arrayNameRegex получается имя массива
                // и через это имя и вычисленный индекс получается значение массива
                // из variables, которое идёт в output
                else if (token.matches(arrayRegex)) {
                    val expression = Expression()
                    val arrayIndex = expression.evaluateRPN(
                        expression.toRPN(
                            arrayExpressionRegex.find(token)!!.value,
                            variables
                        )
                    ).toInt().toString()
                    val arrayName = arrayNameRegex.find(token)!!.value
                    val arrayToken = arrayName + "[" + arrayIndex + "]"
                    val value = variables[arrayToken]
                        ?: throw IllegalArgumentException("Unknown variable: $token")
                    output.add(value.toString())
                }
                //всё остальное
                else {
                    val value = variables[token]
                        ?: throw IllegalArgumentException("Unknown variable: $token")
                    output.add(value.toString())
                }

            }
        }
        //всё извлекаем из стека
        while (stack.isNotEmpty()) {
            output.add(stack.removeLast())
        }
        //опс готово!
        return output.joinToString(" ")
    }

    fun evaluateRPN(rpn: String): Double {
        val stack = mutableListOf<Double>()

        rpn.split(" ").forEach { token ->
            //Если токен-оператор,то из стека извлекаются 2 последних переменных,
            // выполняется нужная операция и результат add в стек.
            // Если токен является операндом, он тоже addв стек.
            when (token) {
                "+", "-", "*", "/" -> {
                    val b = stack.removeLast()
                    val a = stack.removeLast()
                    val result = when (token) {
                        "+" -> a + b
                        "-" -> a - b
                        "*" -> a * b
                        "/" -> a / b
                        else -> throw IllegalArgumentException("Unknown operator: $token")
                    }
                    stack.add(result)
                }

                else -> {
                    val operand = token.toDoubleOrNull()
                        ?: throw IllegalArgumentException("Invalid token: $token")
                    stack.add(operand)
                }
            }
        }
        //Если в стеке остались другие элементы, сообщаем об ошибке
        if (stack.size != 1) {
            throw IllegalArgumentException("Invalid RPN expression: $rpn")
        }
        //результат выражения
        return stack[0]
    }

    private fun priority(operator: String): Int {
        return when (operator) {
            "+", "-" -> 1
            "*", "/" -> 2
            else -> 0
        }
    }
}
 