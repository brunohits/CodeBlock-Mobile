package com.example.OurMobile

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.*
import kotlin.math.roundToInt
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource


var number by mutableStateOf(0)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VarAssignment(onCloseClicked: () -> Unit) {
    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }
    val isDragging = remember { mutableStateOf(false) }
    val variableName = remember { mutableStateOf("") }
    val variableValue = remember { mutableStateOf("") }
    Card(
        modifier = Modifier
            .offset {
                IntOffset(
                    offsetX.value.roundToInt(), offsetY.value.roundToInt()
                )
            }
            .width(500.dp)
            .height(80.dp)
            .padding(10.dp)
            .pointerInput(Unit) {
                detectDragGestures(onDragStart = {
                    number = 2
                    isDragging.value = true
                    onCloseClicked()
                },
                    onDragEnd = { isDragging.value = false },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                        change.consume()
                    })
            }
            .clickable {
                number = 2
                onCloseClicked()
            },
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.var_assign_block_color)),
        shape = RoundedCornerShape(15.dp),
    ) {
        Box {
            Row {
                TextField(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(10.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    textStyle = LocalTextStyle.current.copy(fontSize = 20.sp),
                    value = variableName.value,
                    onValueChange = { newText -> variableName.value = newText },
                    shape = RoundedCornerShape(15.dp)
                )
                Text(
                    text = stringResource(id = R.string.equal_with_spaces),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
                TextField(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(10.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    textStyle = LocalTextStyle.current.copy(fontSize = 20.sp),
                    value = variableValue.value,
                    onValueChange = { newText -> variableValue.value = newText },
                    shape = RoundedCornerShape(15.dp)
                )
            }
        }
    }
}

//Кард для создания переменной
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeVar(onCloseClicked: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val variableName = remember { mutableStateOf("") }
    val selectedType = remember { mutableStateOf("") }

    // Сохраненный тип переменной
    selectedType.value = stringResource(id = R.string.int_string)
    // Сохраненное имя переменной
    variableName.value = stringResource(id = R.string.new_variable)

    Card(
        modifier = Modifier
            .width(500.dp)
            .padding(10.dp)
            .clickable {
                number = 1
                onCloseClicked()
            },
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.var_type_block_color)),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column {
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.globalvaricon),
                    modifier = Modifier
                        .fillMaxSize(0.13f)
                        .padding(start = 10.dp),
                    contentDescription = "var"
                )
                IconButton(
                    onClick = { expanded = true }, modifier = Modifier.padding(top = 10.dp)
                ) {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                }
                Text(
                    text = selectedType.value,
                    modifier = Modifier.padding(top = 25.dp),
                    fontSize = 15.sp
                )
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    val typeList = listOf(
                        stringResource(id = R.string.int_string),
                        stringResource(id = R.string.double_string),
                        stringResource(id = R.string.string_string)
                    )
                    typeList.forEach { type ->
                        DropdownMenuItem(text = { Text(text = type) }, onClick = {
                            selectedType.value = type
                            expanded = false
                        })
                    }
                }
                Text(text = "   ", fontSize = 15.sp)
                TextField(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(top = 10.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = variableName.value,
                    onValueChange = { inputedText ->
                        variableName.value = inputedText
                    },
                    shape = RoundedCornerShape(15.dp)
                )
            }
        }
    }
}

//Кард для ифа
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IfBlock(onCloseClicked: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var conditionFirst by remember { mutableStateOf("") }
    var conditionSecond by remember { mutableStateOf("") }
    var selectedSign by remember { mutableStateOf("") }
    Card(
        modifier = Modifier
            .width(500.dp)
            .padding(10.dp)
            .clickable {
                number = 3
                onCloseClicked()
            },
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.if_block_color)),
        shape = RoundedCornerShape(15.dp),
    ) {
        Column {
            Row() {
                Text(
                    text = stringResource(id = R.string.if_string),
                    modifier = Modifier.padding(15.dp),
                    fontSize = 15.sp
                )
                TextField(
                    modifier = Modifier.width(200.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = conditionFirst,
                    onValueChange = { newText ->
                        conditionFirst = newText
// Изменять значение внешнего класса (условия ифа) здесь (при изменении текст филда) именно через condition.value
                    },
                    shape = RoundedCornerShape(15.dp)
                )
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                }
                Text(
                    text = selectedSign, modifier = Modifier.padding(15.dp), fontSize = 15.sp
                )
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    val signList = listOf(
                        stringResource(id = R.string.mega_equal),
                        stringResource(id = R.string.not_equal),
                        stringResource(id = R.string.greater),
                        stringResource(id = R.string.greater_or_equal),
                        stringResource(id = R.string.less),
                        stringResource(id = R.string.less_or_equal)
                    )
                    signList.forEach { sign ->
                        DropdownMenuItem(text = { Text(text = sign) }, onClick = {
                            selectedSign = sign
                            expanded = false
                        })
                    }
                }
                TextField(
                    modifier = Modifier.width(200.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = conditionSecond,
                    onValueChange = { newText ->
                        conditionSecond = newText
// Изменять значение внешнего класса (условия ифа) здесь (при изменении текст филда) именно через condition.value
                    },
                    shape = RoundedCornerShape(15.dp)
                )
                Button(modifier = Modifier.padding(5.dp), onClick = {
// Действие для удаления блока
                }) {}
            }
            Text(
                text = stringResource(id = R.string.do_begin),
                fontSize = 15.sp,
                modifier = Modifier.padding(15.dp)
            )
        }
    }
}


//Кард для фора
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForBlock(onCloseClicked: () -> Unit) {
    val initExpression = remember { mutableStateOf("") }
    val condExpression = remember { mutableStateOf("") }
    val loopExpression = remember { mutableStateOf("") }

    // Сохраненное пре-объявление переменной
    initExpression.value = ""
    // Сохраненное условие цикла
    condExpression.value = ""
    // Сохраненное действие цикла
    loopExpression.value = ""

    Card(
        modifier = Modifier
            .width(500.dp)
            .padding(10.dp)
            .clickable {
                number = 4
                onCloseClicked()
            },
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.for_block_color)),
        shape = RoundedCornerShape(15.dp),
    ) {
        Column {
            Row() {
                Text(
                    text = stringResource(id = R.string.for_string),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(15.dp)
                )
                TextField(
                    modifier = Modifier.width(100.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = initExpression.value,
                    onValueChange = { newText ->
                        initExpression.value = newText
                        // Изменять значение внешнего класса (пре-объявление переменной) здесь (при изменении текст филда) именно через initExpression.value
                    },
                    shape = RoundedCornerShape(15.dp)
                )
                Text(text = " ", fontSize = 15.sp, modifier = Modifier.padding(15.dp))
                TextField(
                    modifier = Modifier.width(100.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = condExpression.value,
                    onValueChange = { newText ->
                        condExpression.value = newText
                        // Изменять значение внешнего класса (условие цикла) здесь (при изменении текст филда) именно через condExpression.value
                    },
                    shape = RoundedCornerShape(15.dp)
                )
                Text(text = " ", fontSize = 15.sp, modifier = Modifier.padding(15.dp))
                TextField(
                    modifier = Modifier.width(100.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = loopExpression.value,
                    onValueChange = { newText ->
                        loopExpression.value = newText
                        // Изменять значение внешнего класса (действие цикла) здесь (при изменении текст филда) именно через loopExpression.value
                    },
                    shape = RoundedCornerShape(15.dp)
                )
            }
            Text(
                text = stringResource(id = R.string.do_begin),
                fontSize = 15.sp,
                modifier = Modifier.padding(15.dp)
            )
        }
    }
}


// Кард для вывода значения переменной
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrintBlock(onCloseClicked: () -> Unit) {
    val variableName = remember { mutableStateOf("") }
    // Сохраненное значение имени переменной
    variableName.value = ""
    Card(
        modifier = Modifier
            .width(400.dp)
            .padding(10.dp)
            .clickable {
                number = 5
                onCloseClicked()
            },
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.print_block_color)),
        shape = RoundedCornerShape(15.dp),
    ) {
        Row(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
            Text(
                text = stringResource(id = R.string.print),
                fontSize = 15.sp,
                modifier = Modifier.padding(15.dp)
            )
            TextField(
                modifier = Modifier.width(200.dp),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                value = variableName.value,
                onValueChange = { newText ->
                    variableName.value = newText
                    // Изменять значение внешнего класса (значение имени переменной) здесь (при изменении текст филда) именно через variableName.value
                },

                shape = RoundedCornerShape(15.dp)
            )
        }
    }
}

@Composable
fun MenuScreen(showNewScreen: Boolean, onCloseClicked: () -> Unit) {
    var selectedButton by remember { mutableStateOf(-1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.TopCenter)
            .background(Color.DarkGray)
    ) {
        if (showNewScreen) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Button(
                    onClick = {
                        selectedButton = if (selectedButton == 1) -1 else 1
                    },
                    shape = RoundedCornerShape(40.dp),
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.1f)
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.globalvaricon),
                            contentDescription = "varicon",
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            stringResource(id = R.string.big_var),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 5.dp, start = 40.dp, end = 40.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.globalvaricon),
                            contentDescription = "varicon",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
                if (selectedButton == 1) {
                    TypeVar(onCloseClicked = onCloseClicked)
                    VarAssignment(onCloseClicked = onCloseClicked)
                    PrintBlock(onCloseClicked = onCloseClicked)
                }
                Button(
                    onClick = {
                        selectedButton = if (selectedButton == 2) -1 else 2
                    },
                    shape = RoundedCornerShape(40.dp),
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight(0.1f)
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.ificon),
                            contentDescription = "ificon",
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            stringResource(id = R.string.big_if),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 5.dp, start = 55.dp, end = 55.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ificon),
                            contentDescription = "ificon",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
                if (selectedButton == 2) {
                    IfBlock(onCloseClicked = onCloseClicked)
                }
                Button(
                    onClick = {
                        selectedButton = if (selectedButton == 3) -1 else 3
                    },
                    shape = RoundedCornerShape(40.dp),
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.1f)
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.foricon),
                            contentDescription = "ificon",
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            stringResource(id = R.string.big_for),
                            fontSize = 18.sp,
                            modifier = Modifier.padding(top = 5.dp, start = 20.dp, end = 20.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.foricon),
                            contentDescription = "ificon",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
                if (selectedButton == 3) {
                    ForBlock(onCloseClicked = onCloseClicked)
                }
                IconButton(
                    onClick = onCloseClicked, modifier = Modifier.padding(top = 16.dp)
                ) {
                    Icon(Icons.Filled.Close, contentDescription = "close")
                }
            }
        }
    }
}