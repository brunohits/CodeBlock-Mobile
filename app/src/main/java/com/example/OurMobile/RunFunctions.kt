package com.example.OurMobile

import android.util.Log

class Variables(
    var variableName: String, var variableType: String
)

var variablesList = mutableListOf<Variables>()
var doRun: Boolean = true
var messagesCout = mutableListOf<String>()
var valuesCout = mutableListOf<Int>()
var commandList = mutableListOf<String>()

fun runApp() {
    valuesCout.clear()
    variablesList.clear()
    messagesCout.clear()
    valuesCout.clear()
    doRun = true
    commandList = createCommandList()

    if (doRun) {
        val compiler = ViNiInterpreter(hashMapOf(), commandList)
        compiler.interprete()
        messagesCout += compiler.calloutList
    }
    for (i in 0 until messagesCout.size) {
        Log.d("Tag", messagesCout[i])
    }
    for (i in 0 until commandList.size) {
        Log.d("Tag", commandList[i])
    }
}

fun createCommandList(): MutableList<String> {
    var hasChild = true
    var childId: Int = CardList[0].childId.value
    var blockNumber = 0
    val commandList = mutableListOf<String>()
    val endList = mutableListOf<String>()
    var numOfEnd = 1

    while (hasChild) {
        ++blockNumber
        hasChild = false

        if (childId == -1) {
            break
        }

        for (i in 0 until typeVarList.size) {
            //Если это блок создания переменной
            if (typeVarList[i].id == childId) {
                hasChild = true
                //,то проверяем создание переменной с таким же именем в блоке
                if (checkMakeAVariable(typeVarList[i].variableName.value, blockNumber)) {
                    //если да, то добавляем в список команд переменную с именем и типом,
                    commandList.add("<variable:" + typeVarList[i].variableName.value + "," + typeVarList[i].selectedType.value + ">")
                    //+создаём объект класса Variables
                    variablesList.add(
                        Variables(
                            typeVarList[i].variableName.value, typeVarList[i].selectedType.value
                        )
                    )
                } else {
                    doRun = false
                }
                childId = typeVarList[i].childId.value
            }
        }

        if (!hasChild) {
            for (i in 0 until varAssignmentList.size) {
                //если это блок присваивания, то проверяем можем ли создать переменную с этим именем в блоке
                if (varAssignmentList[i].id == childId) {
                    hasChild = true
                    var expString = spaceRemove(varAssignmentList[i].variableValue.value)
                    //если всё ок, то добавляем
                    if (checkMakeAVariable(
                            varAssignmentList[i].variableName.value, blockNumber
                        )
                    ) {
                        expString = normalizationOfExpression(expString)
                        commandList.add("<equals:" + varAssignmentList[i].variableName.value + ",<expression:" + expString + ">>")
                    } else {
                        doRun = false
                    }
                    childId = varAssignmentList[i].childId.value
                }
            }
        }

        if (!hasChild) {
            for (i in 0 until CoutBlockList.size) {
                // если это блок вывода значения переменной, то в список команд добавляется callout
                if (CoutBlockList[i].id == childId) {
                    hasChild = true
                    var expString = spaceRemove(CoutBlockList[i].variableName.value)
                    expString = normalizationOfExpression(expString)
                    commandList.add("<callout:<expression:$expString>>")
                    childId = CoutBlockList[i].childId.value
                }
            }
        }
        //check
        if (!hasChild) {
            for (i in 0 until IfBlockList.size) {

                //Если это блок условного оператора, то добавляем if с двумя выражениями из блока условия+сравнение
                // и номером конечного блока для <endif>` Номер конечного блока увеличивается на 1.(check)
                if (IfBlockList[i].id == childId) {
                    hasChild = true
                    var expStringFir = spaceRemove(IfBlockList[i].conditionFirst.value)
                    var expStringSec = spaceRemove(IfBlockList[i].conditionSecond.value)
                    expStringFir = normalizationOfExpression(expStringFir)
                    expStringSec = normalizationOfExpression(expStringSec)
                    commandList.add("<if:<expression:$expStringFir>,<expression:$expStringSec>," + IfBlockList[i].selectedSign.value + ",$numOfEnd>")
                    endList.add("<endif:$numOfEnd>")
                    ++numOfEnd
                    childId = IfBlockList[i].childId.value
                }
            }
        }

        if (!hasChild) {
            for (i in 0 until EndBlockList.size) {
                //Если это блок конца if, то из списка `endList` удаляется последний элемент
                // и добавляется команда завершения условного оператора.
                if (EndBlockList[i].id == childId) {
                    hasChild = true
                    if (endList.size > 0) {
                        commandList.add(endList[endList.size - 1])
                        endList.removeAt(endList.size - 1)
                    } else {
                        doRun = false
                    }
                    childId = IfBlockList[i].childId.value
                }
            }
        }
    }
    return commandList
}

fun checkMakeAVariable(name: String, index: Int): Boolean {
    var checkResult = true
    val variableRegex = "([a-zA-Z][a-zA-Z0-9]*)".toRegex()
    var result = name
    result = variableRegex.replaceFirst(result, "a")
    //Если результат не равен "a", то имя переменной некорректно
    if (result != "a") {
        checkResult = false
        messagesCout.add("In block number: $index name of variable is incorrect")
    }
    return checkResult
}

fun spaceRemove(exp: String): String {
    var newExp = ""
    for (i in exp.indices) {
        if (exp[i] != ' ') {
            newExp += exp[i]
            exp.split(" ")
        }
    }
    return newExp
}

fun normalizationOfExpression(expression: String): String {
    val variableRegex = "([a-zA-Z][a-zA-Z0-9]*)"
    val numberRegex = "(([1-9][0-9]*([.][0-9]*[1-9])?)|([0][.][0-9]*[1-9])|([0]))"
    //check
    val arrayRegex = "([a-zA-Z]+\\[(([a-zA-Z]+)|(([0])|([1-9][0-9]*)))\\])"
    val expressionRegex =
        "($variableRegex|$numberRegex|$arrayRegex|([\\+\\-\\/\\*])|([\\(])|([\\)]))"
    val expSpaceReg = "([\\+\\-\\/\\*][ ][\\-][ ])"

    val pattern = expressionRegex.toRegex()
    val expSpaceRegex = expSpaceReg.toRegex()
    var exp = expression
    var result = ""

    while (pattern.find(exp) != null) {
        val matchExp = pattern.find(exp)
        val matchedExp = matchExp?.value
        exp = pattern.replaceFirst(exp, "")
        result += "$matchedExp "
    }

    while (expSpaceRegex.find(result) != null) {
        //если есть пробел между знаками арифметических операций и отрицательными числами,
        // то он заменяется на точку
        val matchExp = expSpaceRegex.find(result)
        val matchedExp = matchExp?.value
        var neededExp = matchedExp.toString()
        neededExp = ".$".toRegex().replaceFirst(neededExp, "")
        result = expSpaceRegex.replaceFirst(result, neededExp)
    }

    result = ".$".toRegex().replaceFirst(result, "")
    return result
}

