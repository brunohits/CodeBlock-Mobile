package com.example.codeBlocks

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp

data class needClear(
    var IdToClear:Int,
    var WhatList:Int,
)
var NeedClear = needClear(-1,0)

//1 - TypeVarible
//2 - VariableAssignment
//3 - IfBlock
//4 - ForBlock
//5 - Cout

class CardClass(
    var offsetX: MutableState<Float> = mutableStateOf(0f),
    var offsetY: MutableState<Float> = mutableStateOf(0f),
    var thisID: Int,
    var height: Dp,
    var width: Dp,
    var isDragging: MutableState<Boolean> = mutableStateOf(false),
    var childId: MutableState<Int> = mutableStateOf(-1),

    )

data class VarAssignmentClass(
    var offsetX: MutableState<Float> = mutableStateOf(0f),
    var offsetY: MutableState<Float> = mutableStateOf(0f),
    var isDragging: MutableState<Boolean> = mutableStateOf(false),
    var variableName: MutableState<String> = mutableStateOf(""),
    var variableValue: MutableState<String> = mutableStateOf(""),
    var thisID: Int,
    var childId: MutableState<Int> = mutableStateOf(-1),

    )

data class IfBlockClass(
    var offsetX: MutableState<Float> = mutableStateOf(0f),
    var offsetY: MutableState<Float> = mutableStateOf(0f),
    var isDragging: MutableState<Boolean> = mutableStateOf(false),
    var thisID: Int,
    var childId: MutableState<Int> = mutableStateOf(-1),
    var conditionFirst: MutableState<String> = mutableStateOf(""),
    var conditionSecond: MutableState<String> = mutableStateOf(""),
    var selectedSign: MutableState<String> = mutableStateOf(""),
    var expanded: MutableState<Boolean> = mutableStateOf(false),

    )

data class ForBlockClass(
    var offsetX: MutableState<Float> = mutableStateOf(0f),
    var offsetY: MutableState<Float> = mutableStateOf(0f),
    var isDragging: MutableState<Boolean> = mutableStateOf(false),
    var initExpression: MutableState<String> = mutableStateOf(""),
    var condExpression: MutableState<String> = mutableStateOf(""),
    var loopExpression: MutableState<String> = mutableStateOf(""),
    var thisID: Int,
    var childId: MutableState<Int> = mutableStateOf(-1),
)

data class TypeVarClass(
    var offsetX: MutableState<Float> = mutableStateOf(0f),
    var offsetY: MutableState<Float> = mutableStateOf(0f),
    var isDragging: MutableState<Boolean> = mutableStateOf(false),
    var expanded: MutableState<Boolean> = mutableStateOf(false),
    var variableName: MutableState<String> = mutableStateOf(""),
    var selectedType: MutableState<String> = mutableStateOf(""),
    var thisID: Int,
    var childId: MutableState<Int> = mutableStateOf(-1),
)

data class CoutBlockClass(
    var offsetX: MutableState<Float> = mutableStateOf(0f),
    var offsetY: MutableState<Float> = mutableStateOf(0f),
    var isDragging: MutableState<Boolean> = mutableStateOf(false),
    var variableName: MutableState<String> = mutableStateOf(""),
    var thisID: Int,
    var childId: MutableState<Int> = mutableStateOf(-1),
)

data class EndBeginBlockClass(
    var offsetX: MutableState<Float> = mutableStateOf(0f),
    var offsetY: MutableState<Float> = mutableStateOf(0f),
    var thisID: Int,
    var isDragging: MutableState<Boolean> = mutableStateOf(false),
    var childId: MutableState<Int> = mutableStateOf(-1),
)

data class BeginBlockClass(
    var offsetX: MutableState<Float> = mutableStateOf(0f),
    var offsetY: MutableState<Float> = mutableStateOf(0f),
    var thisID: Int,
    var isDragging: MutableState<Boolean> = mutableStateOf(false),
    var childId: MutableState<Int> = mutableStateOf(-1),
)
data class EndBlockClass(
    var offsetX: MutableState<Float> = mutableStateOf(0f),
    var offsetY: MutableState<Float> = mutableStateOf(0f),
    var thisID: Int,
    var isDragging: MutableState<Boolean> = mutableStateOf(false),
    var childId: MutableState<Int> = mutableStateOf(-1),
)


val typeVarList = mutableListOf<TypeVarClass>()
val varAssignmentList = mutableListOf<VarAssignmentClass>()
val IfBlockList = mutableListOf<IfBlockClass>()
val CardList = mutableListOf<CardClass>()
val ForBlockList = mutableListOf<ForBlockClass>()
val CoutBlockList = mutableListOf<CoutBlockClass>()
val BeginBlockList = mutableListOf<BeginBlockClass>()
val EndBlockList = mutableListOf<EndBlockClass>()
val EndBeginBlockList = mutableListOf<EndBeginBlockClass>()
