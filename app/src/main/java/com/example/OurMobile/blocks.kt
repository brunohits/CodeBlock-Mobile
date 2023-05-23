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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
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
    thisID: Int,
    CardList: MutableList<CardClass>,
) {
    Card(
        modifier = Modifier
            .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
            .width(300.dp)
            .height(60.dp)
            .padding(2.dp)
            .background(Color.LightGray,)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        isDragging.value = true
                    },
                    onDragEnd = { isDragging.value = false },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                        change.consumeAllChanges()
                        var i = CardList[thisID].childId.value
                        while (i != -1) {
                            CardList[i].offsetY.value += dragAmount.y
                            CardList[i].offsetX.value += dragAmount.x
                            i = CardList[i].childId.value
                        }
                    }
                )
            },
        shape = RoundedCornerShape(15.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Main begin",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun BeginBlockReal(
    offsetX: MutableState<Float>,
    offsetY: MutableState<Float>,
    isDragging: MutableState<Boolean>,
    thisID: Int,
    CardList: MutableList<CardClass>,
) {
    Card(
        modifier = Modifier
            .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
            .width(200.dp)
            .height(45.dp)
            .padding(2.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        isDragging.value = true
                    },
                    onDragEnd = { isDragging.value = false },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                        change.consumeAllChanges()
                        var i = CardList[thisID].childId.value
                        while (i != -1) {
                            CardList[i].offsetY.value += dragAmount.y
                            CardList[i].offsetX.value += dragAmount.x
                            i = CardList[i].childId.value
                        }
                    }
                )
            },
        shape = RoundedCornerShape(15.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Begin",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun EndBlockReal(
    offsetX: MutableState<Float>,
    offsetY: MutableState<Float>,
    isDragging: MutableState<Boolean>,
    thisID: Int,
    CardList: MutableList<CardClass>,
) {
    Card(
        modifier = Modifier
            .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
            .width(200.dp)
            .height(45.dp)
            .padding(2.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        isDragging.value = true
                    },
                    onDragEnd = { isDragging.value = false },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                        change.consumeAllChanges()
                        var i = CardList[thisID].childId.value
                        while (i != -1) {
                            CardList[i].offsetY.value += dragAmount.y
                            CardList[i].offsetX.value += dragAmount.x
                            i = CardList[i].childId.value
                        }
                    }
                )
            },
        shape = RoundedCornerShape(15.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "End",
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
    thisID: Int,
    CardList: MutableList<CardClass>,
) {
    Card(
        modifier = Modifier
            .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
            .width(300.dp)
            .height(60.dp)
            .padding(2.dp)
            .background(Color.LightGray,)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        isDragging.value = true
                    },
                    onDragEnd = { isDragging.value = false },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                        change.consumeAllChanges()
                        var i = CardList[thisID].childId.value
                        while (i != -1) {
                            CardList[i].offsetY.value += dragAmount.y
                            CardList[i].offsetX.value += dragAmount.x
                            i = CardList[i].childId.value
                        }
                    }
                )
            },
        shape = RoundedCornerShape(15.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Main End",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}

//Кард для создания переменной
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeVariableReal(
    offsetX: MutableState<Float>,
    offsetY: MutableState<Float>,
    isDragging: MutableState<Boolean>,
    expanded: MutableState<Boolean>,
    variableName: MutableState<String>,
    selectedType: MutableState<String>,
    thisID: Int,
    CardList: MutableList<CardClass>,
) {
    // Сохраненный тип переменной
    if (selectedType.value == "") {
        selectedType.value = "int"
    }
    // Сохраненное имя переменной
    if (variableName.value == "") {
        variableName.value = "NewVariable"
    }

    Card(
        modifier = Modifier
            .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
            .width(400.dp)
            .height(80.dp)
            .padding(2.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        isDragging.value = true
                    },
                    onDragEnd = { isDragging.value = false },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                        change.consumeAllChanges()
                        var i = CardList[thisID].childId.value;
                        while (i != -1) {
                            CardList[i].offsetY.value += dragAmount.y
                            CardList[i].offsetX.value += dragAmount.x
                            i = CardList[i].childId.value
                        }
                    }
                )
            },
        shape = RoundedCornerShape(15.dp)

    )
    {
        Column {
            Row()
            {
                Image(painter = painterResource(id = R.drawable.globalvaricon),modifier=Modifier.padding(7.dp), contentDescription ="var" )
                IconButton(onClick = { expanded.value = true }, modifier = Modifier.padding(top=10.dp))
                {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                }
                Text(
                    text = selectedType.value,
                    modifier = Modifier.padding(top = 25.dp),
                    fontSize = 15.sp
                )
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "int") },
                        onClick = {
                            selectedType.value = "int"
                            // Изменять значение внешнего класса (типа переменной) здесь (при изменении дроп меню) именно через selectedType.value
                            expanded.value = false
                        })
                    DropdownMenuItem(
                        text = { Text(text = "double") },
                        onClick = {
                            selectedType.value = "double"
                            // Изменять значение внешнего класса (типа переменной) здесь (при изменении дроп меню) именно через selectedType.value
                            expanded.value = false
                        })
                    DropdownMenuItem(
                        text = { Text(text = "string") },
                        onClick = {
                            selectedType.value = "string"
                            // Изменять значение внешнего класса (типа переменной) здесь (при изменении дроп меню) именно через selectedType.value
                            expanded.value = false
                        })
                }
                Text(text = "   ", fontSize = 15.sp)
                TextField(
                    modifier = Modifier.width(200.dp).padding(top=10.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = variableName.value,
                    onValueChange = { newText ->
                        variableName.value = newText
                        // Изменять значение внешнего класса (имени переменной) здесь (при изменении текст филда) именно через variableName.value
                    }
                )

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForBlockReal(
    offsetX: MutableState<Float>,
    offsetY: MutableState<Float>,
    isDragging: MutableState<Boolean>,
    thisID: Int,
    CardList: MutableList<CardClass>,
    initExpression: MutableState<String>,
    condExpression: MutableState<String>,
    loopExpression: MutableState<String>,
) {

    Card(
        modifier = Modifier
            .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
            .width(250.dp)
            .padding(2.dp)
            .height(200.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        isDragging.value = true
                    },
                    onDragEnd = { isDragging.value = false },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                        change.consumeAllChanges()
                        var i = CardList[thisID].childId.value;
                        while (i != -1) {
                            CardList[i].offsetY.value += dragAmount.y
                            CardList[i].offsetX.value += dragAmount.x
                            i = CardList[i].childId.value
                        }
                    }
                )
            },
        shape = RoundedCornerShape(15.dp),
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "For", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    modifier = Modifier.width(200.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = initExpression.value,
                    onValueChange = { newText ->
                        initExpression.value = newText
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "to", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    modifier = Modifier.width(200.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = condExpression.value,
                    onValueChange = { newText ->
                        condExpression.value = newText
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "step", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    modifier = Modifier.width(200.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = loopExpression.value,
                    onValueChange = { newText ->
                        loopExpression.value = newText
                    }
                )

            }
            Text(
                text = "Do begin",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CinBlockReal(
    offsetX: MutableState<Float>,
    offsetY: MutableState<Float>,
    isDragging: MutableState<Boolean>,
    variableName: MutableState<String>,
    thisID: Int,
    CardList: MutableList<CardClass>,
) {
    Card(
        modifier = Modifier
            .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
            .width(300.dp)
            .padding(2.dp)
            .height(80.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        isDragging.value = true
                    },
                    onDragEnd = { isDragging.value = false },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                        change.consumeAllChanges()
                        var i = CardList[thisID].childId.value
                        while (i != -1) {
                            CardList[i].offsetY.value += dragAmount.y
                            CardList[i].offsetX.value += dragAmount.x
                            i = CardList[i].childId.value
                        }
                    }
                )
            },
        shape = RoundedCornerShape(15.dp),
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Cin",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 8.dp)
            )
            TextField(
                modifier = Modifier.weight(1f),
                textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                value = variableName.value,
                onValueChange = { newText ->
                    variableName.value = newText
                    // Изменять значение внешнего класса (значение имени переменной) здесь (при изменении текст филда) именно через variableName.value
                }
            )
        }
    }
}

// Кард для вывода значения переменной
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoutBlockReal(
    offsetX: MutableState<Float>,
    offsetY: MutableState<Float>,
    isDragging: MutableState<Boolean>,
    variableName: MutableState<String>,
    thisID: Int,
    CardList: MutableList<CardClass>,
) {

    Card(
        modifier = Modifier
            .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
            .width(300.dp)
            .height(80.dp)
            .padding(2.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        isDragging.value = true
                    },
                    onDragEnd = { isDragging.value = false },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                        change.consumeAllChanges()
                        var i = CardList[thisID].childId.value;
                        while (i != -1) {
                            CardList[i].offsetY.value += dragAmount.y
                            CardList[i].offsetX.value += dragAmount.x
                            i = CardList[i].childId.value
                        }
                    }
                )
            },
        shape = RoundedCornerShape(15.dp),
    ) {
        Row(            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically)
        {
            Text(
                text = "Cout",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 8.dp)
            )
            TextField(
                modifier = Modifier.weight(1f),
                textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                value = variableName.value,
                onValueChange = { newText ->
                    variableName.value = newText
                    // Изменять значение внешнего класса (значение имени переменной) здесь (при изменении текст филда) именно через variableName.value
                }
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VariableAssignmentReal(
    offsetX: MutableState<Float>,
    offsetY: MutableState<Float>,
    isDragging: MutableState<Boolean>,
    VariableName: MutableState<String>,
    VariableValue: MutableState<String>,
    thisID: Int,
    CardList: MutableList<CardClass>,
) {
    Card(
        modifier = Modifier
            .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
            .width(500.dp)
            .height(80.dp)
            .padding(2.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        isDragging.value = true
                    },
                    onDragEnd = { isDragging.value = false },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                        change.consumeAllChanges()
                        var i = CardList[thisID].childId.value;
                        while (i != -1) {
                            CardList[i].offsetY.value += dragAmount.y
                            CardList[i].offsetX.value += dragAmount.x
                            i = CardList[i].childId.value
                        }
                    }
                )
            },
        shape = RoundedCornerShape(15.dp),
    ) {
        Box() {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 20.sp),
                    value = VariableName.value,
                    onValueChange = { newText -> VariableName.value = newText }
                )
                Text(
                    text = " = ",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
                TextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 20.sp),
                    value = VariableValue.value,
                    onValueChange = { newText -> VariableValue.value = newText }
                )
            }
        }
    }
}

//Кард для ифа
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IfBlockReal(
    offsetX: MutableState<Float>,
    offsetY: MutableState<Float>,
    isDragging: MutableState<Boolean>,
    conditionFirst: MutableState<String>,
    conditionSecond: MutableState<String>,
    expanded: MutableState<Boolean>,
    selectedSign: MutableState<String>,
    thisID: Int,
    CardList: MutableList<CardClass>,
) {
    Card(
        modifier = Modifier
            .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
            .width(500.dp)
            .height(150.dp)
            .padding(8.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        isDragging.value = true
                    },
                    onDragEnd = { isDragging.value = false },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                        change.consumeAllChanges()
                        var i = CardList[thisID].childId.value
                        while (i != -1) {
                            CardList[i].offsetY.value += dragAmount.y
                            CardList[i].offsetX.value += dragAmount.x
                            i = CardList[i].childId.value
                        }
                    }
                )
            },
        shape = RoundedCornerShape(15.dp),
    ) {
        Column(
            Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "If",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 8.dp)
                )
                TextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = conditionFirst.value,
                    onValueChange = { newText ->
                        conditionFirst.value = newText
                    }
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
                    onDismissRequest = { expanded.value = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "==") },
                        onClick = {
                            selectedSign.value = "=="
                            expanded.value = false
                        })
                    DropdownMenuItem(
                        text = { Text(text = "!=") },
                        onClick = {
                            selectedSign.value = "!="
                            expanded.value = false
                        })
                    DropdownMenuItem(
                        text = { Text(text = ">") },
                        onClick = {
                            selectedSign.value = ">"
                            expanded.value = false
                        })
                    DropdownMenuItem(
                        text = { Text(text = ">=") },
                        onClick = {
                            selectedSign.value = ">="
                            expanded.value = false
                        })
                    DropdownMenuItem(
                        text = { Text(text = "<") },
                        onClick = {
                            selectedSign.value = "<"
                            expanded.value = false
                        })
                    DropdownMenuItem(
                        text = { Text(text = "<=") },
                        onClick = {
                            selectedSign.value = "<="
                            expanded.value = false
                        })
                }
                TextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = conditionSecond.value,
                    onValueChange = { newText ->
                        conditionSecond.value = newText
                    }
                )
            }
            Text(
                text = "Then begin",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}