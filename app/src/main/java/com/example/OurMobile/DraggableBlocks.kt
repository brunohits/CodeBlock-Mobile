package com.example.OurMobile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun BeginBlock(
    offsetX: MutableState<Float>,
    offsetY: MutableState<Float>,
    isDragging: MutableState<Boolean>,
    id: Int,
    CardList: MutableList<CardClass>,
) {
    Card(
        modifier = Modifier
            .offset {
                IntOffset(
                    offsetX.value.roundToInt(), offsetY.value.roundToInt()
                )
            }
            .width(300.dp)
            .height(60.dp)
            .padding(2.dp)
            .background(Color.Transparent)
            .pointerInput(Unit) {
                detectDragGestures(onDragStart = {
                    isDragging.value = true
                },
                    onDragEnd = { isDragging.value = false },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                        change.consume()
                        var i = CardList[id].childId.value
                        while (i != -1) {
                            CardList[i].offsetY.value += dragAmount.y
                            CardList[i].offsetX.value += dragAmount.x
                            i = CardList[i].childId.value
                        }
                    })
            },
        colors = CardDefaults.cardColors(colorResource(id = R.color.begin_end_color)),
        shape = RoundedCornerShape(15.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp), contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.main_begin),
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun BeginBlockDraggable(
    offsetX: MutableState<Float>,
    offsetY: MutableState<Float>,
    isDragging: MutableState<Boolean>,
    id: Int,
    CardList: MutableList<CardClass>,
) {
    Card(modifier = Modifier
        .offset {
            IntOffset(
                offsetX.value.roundToInt(), offsetY.value.roundToInt()
            )
        }
        .width(200.dp)
        .height(45.dp)
        .padding(2.dp)
        .pointerInput(Unit) {
            detectDragGestures(onDragStart = {
                isDragging.value = true
            },
                onDragEnd = { isDragging.value = false },
                onDragCancel = { },
                onDrag = { change, dragAmount ->
                    offsetX.value += dragAmount.x
                    offsetY.value += dragAmount.y
                    change.consume()
                    var i = CardList[id].childId.value
                    while (i != -1) {
                        CardList[i].offsetY.value += dragAmount.y
                        CardList[i].offsetX.value += dragAmount.x
                        i = CardList[i].childId.value
                    }
                })
        }, shape = RoundedCornerShape(15.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp), contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.begin),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun EndBlockDraggable(
    offsetX: MutableState<Float>,
    offsetY: MutableState<Float>,
    isDragging: MutableState<Boolean>,
    id: Int,
    CardList: MutableList<CardClass>,
) {
    Card(modifier = Modifier
        .offset {
            IntOffset(
                offsetX.value.roundToInt(), offsetY.value.roundToInt()
            )
        }
        .width(200.dp)
        .height(45.dp)
        .padding(2.dp)
        .pointerInput(Unit) {
            detectDragGestures(onDragStart = {
                isDragging.value = true
            },
                onDragEnd = { isDragging.value = false },
                onDragCancel = { },
                onDrag = { change, dragAmount ->
                    offsetX.value += dragAmount.x
                    offsetY.value += dragAmount.y
                    change.consume()
                    var i = CardList[id].childId.value
                    while (i != -1) {
                        CardList[i].offsetY.value += dragAmount.y
                        CardList[i].offsetX.value += dragAmount.x
                        i = CardList[i].childId.value
                    }
                })
        },
        colors = CardDefaults.cardColors(colorResource(id = R.color.begin_end_color)),
        shape = RoundedCornerShape(15.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp), contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.end),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun EndBlock(
    offsetX: MutableState<Float>,
    offsetY: MutableState<Float>,
    isDragging: MutableState<Boolean>,
    id: Int,
    CardList: MutableList<CardClass>,
) {
    Card(
        modifier = Modifier
            .offset {
                IntOffset(
                    offsetX.value.roundToInt(), offsetY.value.roundToInt()
                )
            }
            .width(300.dp)
            .height(60.dp)
            .padding(2.dp)
            .background(Color.LightGray)
            .pointerInput(Unit) {
                detectDragGestures(onDragStart = {
                    isDragging.value = true
                },
                    onDragEnd = { isDragging.value = false },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                        change.consume()
                        var i = CardList[id].childId.value
                        while (i != -1) {
                            CardList[i].offsetY.value += dragAmount.y
                            CardList[i].offsetX.value += dragAmount.x
                            i = CardList[i].childId.value
                        }
                    })
            },
        shape = RoundedCornerShape(15.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp), contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.main_end),
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}

//Кард для создания переменной
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeVarDraggable(
    offsetX: MutableState<Float>,
    offsetY: MutableState<Float>,
    isDragging: MutableState<Boolean>,
    expanded: MutableState<Boolean>,
    variableName: MutableState<String>,
    selectedType: MutableState<String>,
    id: Int,
    CardList: MutableList<CardClass>,
) {
    // Сохраненный тип переменной
    if (selectedType.value == "") {
        selectedType.value = stringResource(id = R.string.int_string)
    }
    // Сохраненное имя переменной
    if (variableName.value == "") {
        variableName.value = stringResource(id = R.string.new_variable)
    }

    Card(modifier = Modifier
        .offset {
            IntOffset(
                offsetX.value.roundToInt(), offsetY.value.roundToInt()
            )
        }
        .width(400.dp)
        .height(80.dp)
        .padding(2.dp)
        .pointerInput(Unit) {
            detectDragGestures(onDragStart = {
                isDragging.value = true
            },
                onDragEnd = { isDragging.value = false },
                onDragCancel = { },
                onDrag = { change, dragAmount ->
                    offsetX.value += dragAmount.x
                    offsetY.value += dragAmount.y
                    change.consume()
                    var i = CardList[id].childId.value
                    while (i != -1) {
                        CardList[i].offsetY.value += dragAmount.y
                        CardList[i].offsetX.value += dragAmount.x
                        i = CardList[i].childId.value
                    }
                })
        },
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.var_type_block_color)),
        shape = RoundedCornerShape(15.dp)

    ) {
        Column {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.globalvaricon),
                    modifier = Modifier.padding(7.dp),
                    contentDescription = "var"
                )
                IconButton(
                    onClick = { expanded.value = true }, modifier = Modifier.padding(top = 10.dp)
                ) {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                }
                Text(
                    text = selectedType.value,
                    modifier = Modifier.padding(top = 25.dp),
                    fontSize = 15.sp
                )
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }) {
                    val typeList = listOf(
                        stringResource(id = R.string.int_string),
                        stringResource(id = R.string.double_string),
                        stringResource(id = R.string.string_string)
                    )
                    typeList.forEach { type ->
                        DropdownMenuItem(text = { Text(text = type) }, onClick = {
                            selectedType.value = type
                            expanded.value = false
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
                    onValueChange = { newText ->
                        variableName.value = newText
                        // Изменять значение внешнего класса (имени переменной) здесь (при изменении текст филда) именно через variableName.value
                    },
                    shape = RoundedCornerShape(15.dp)
                )

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForBlockDraggable(
    offsetX: MutableState<Float>,
    offsetY: MutableState<Float>,
    isDragging: MutableState<Boolean>,
    id: Int,
    CardList: MutableList<CardClass>,
    initExpression: MutableState<String>,
    condExpression: MutableState<String>,
    loopExpression: MutableState<String>,
) {

    Card(
        modifier = Modifier
            .offset {
                IntOffset(
                    offsetX.value.roundToInt(), offsetY.value.roundToInt()
                )
            }
            .width(250.dp)
            .padding(2.dp)
            .height(200.dp)
            .pointerInput(Unit) {
                detectDragGestures(onDragStart = {
                    isDragging.value = true
                },
                    onDragEnd = { isDragging.value = false },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                        change.consume()
                        var i = CardList[id].childId.value
                        while (i != -1) {
                            CardList[i].offsetY.value += dragAmount.y
                            CardList[i].offsetX.value += dragAmount.x
                            i = CardList[i].childId.value
                        }
                    })
            },
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.for_block_color)),
        shape = RoundedCornerShape(15.dp),
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.for_string),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
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
                    value = initExpression.value,
                    onValueChange = { newText ->
                        initExpression.value = newText
                    },
                    shape = RoundedCornerShape(15.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.to),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
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
                    value = condExpression.value,
                    onValueChange = { newText ->
                        condExpression.value = newText
                    },
                    shape = RoundedCornerShape(15.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.step),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
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
                    value = loopExpression.value,
                    onValueChange = { newText ->
                        loopExpression.value = newText
                    },
                    shape = RoundedCornerShape(15.dp)
                )

            }
            Text(
                text = stringResource(id = R.string.do_begin),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

// Кард для вывода значения переменной
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrintBlockDraggable(
    offsetX: MutableState<Float>,
    offsetY: MutableState<Float>,
    isDragging: MutableState<Boolean>,
    variableName: MutableState<String>,
    id: Int,
    CardList: MutableList<CardClass>,
) {
    Card(
        modifier = Modifier
            .offset {
                IntOffset(
                    offsetX.value.roundToInt(), offsetY.value.roundToInt()
                )
            }
            .width(300.dp)
            .height(80.dp)
            .padding(2.dp)
            .pointerInput(Unit) {
                detectDragGestures(onDragStart = {
                    isDragging.value = true
                },
                    onDragEnd = { isDragging.value = false },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                        change.consume()
                        var i = CardList[id].childId.value
                        while (i != -1) {
                            CardList[i].offsetY.value += dragAmount.y
                            CardList[i].offsetX.value += dragAmount.x
                            i = CardList[i].childId.value
                        }
                    })
            },
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.print_block_color)),
        shape = RoundedCornerShape(15.dp),
    ) {
        Row(
            modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.print),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 8.dp)
            )
            TextField(
                modifier = Modifier.weight(1f),
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
                    // Изменять значение внешнего класса (значение имени переменной) здесь (при изменении текст филда) именно через variableName.value
                },
                shape = RoundedCornerShape(15.dp)
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VarAssignmentDraggable(
    offsetX: MutableState<Float>,
    offsetY: MutableState<Float>,
    isDragging: MutableState<Boolean>,
    VariableName: MutableState<String>,
    VariableValue: MutableState<String>,
    id: Int,
    CardList: MutableList<CardClass>,
) {
    Card(
        modifier = Modifier
            .offset {
                IntOffset(
                    offsetX.value.roundToInt(), offsetY.value.roundToInt()
                )
            }
            .width(500.dp)
            .height(80.dp)
            .padding(2.dp)
            .pointerInput(Unit) {
                detectDragGestures(onDragStart = {
                    isDragging.value = true
                },
                    onDragEnd = { isDragging.value = false },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                        change.consume()
                        var i = CardList[id].childId.value
                        while (i != -1) {
                            CardList[i].offsetY.value += dragAmount.y
                            CardList[i].offsetX.value += dragAmount.x
                            i = CardList[i].childId.value
                        }
                    })
            },
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.var_assign_block_color)),
        shape = RoundedCornerShape(15.dp),
    ) {
        Box {
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier
                        .weight(1f)
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
                    value = VariableName.value,
                    onValueChange = { newText -> VariableName.value = newText },
                    shape = RoundedCornerShape(15.dp)
                )
                Text(
                    text = stringResource(id = R.string.equal),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
                TextField(
                    modifier = Modifier
                        .weight(1f)
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
                    value = VariableValue.value,
                    onValueChange = { newText -> VariableValue.value = newText },
                    shape = RoundedCornerShape(15.dp)
                )
            }
        }
    }
}

//Кард для ифа
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IfBlockDraggable(
    offsetX: MutableState<Float>,
    offsetY: MutableState<Float>,
    isDragging: MutableState<Boolean>,
    conditionFirst: MutableState<String>,
    conditionSecond: MutableState<String>,
    expanded: MutableState<Boolean>,
    selectedSign: MutableState<String>,
    id: Int,
    CardList: MutableList<CardClass>,
) {
    Card(
        modifier = Modifier
            .offset {
                IntOffset(
                    offsetX.value.roundToInt(), offsetY.value.roundToInt()
                )
            }
            .width(500.dp)
            .height(150.dp)
            .padding(8.dp)
            .pointerInput(Unit) {
                detectDragGestures(onDragStart = {
                    isDragging.value = true
                },
                    onDragEnd = { isDragging.value = false },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                        change.consume()
                        var i = CardList[id].childId.value
                        while (i != -1) {
                            CardList[i].offsetY.value += dragAmount.y
                            CardList[i].offsetX.value += dragAmount.x
                            i = CardList[i].childId.value
                        }
                    })
            },
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.if_block_color)),
        shape = RoundedCornerShape(15.dp),
    ) {
        Column(
            Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = R.string.if_string.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 8.dp)
                )
                TextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = conditionFirst.value,
                    onValueChange = { newText ->
                        conditionFirst.value = newText
                    },
                    shape = RoundedCornerShape(15.dp)
                )
                IconButton(onClick = { expanded.value = true }) {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                }
                Text(
                    text = selectedSign.value,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }) {
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
                            selectedSign.value = sign
                            expanded.value = false
                        })
                    }
                }
                TextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = conditionSecond.value,
                    onValueChange = { newText ->
                        conditionSecond.value = newText
                    },
                    shape = RoundedCornerShape(15.dp)
                )
            }
            Text(
                text = stringResource(id = R.string.begin),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}