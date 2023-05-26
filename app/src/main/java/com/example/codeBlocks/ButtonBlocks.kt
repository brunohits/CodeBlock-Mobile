package com.example.codeBlocks

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
import androidx.compose.ui.res.painterResource
import com.example.OurMobile.R


var myGlobalNumber by mutableStateOf(0)

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
            .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
            .width(500.dp)
            .height(80.dp)
            .padding(10.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        myGlobalNumber = 2
                        isDragging.value = true
                        onCloseClicked()
                    },
                    onDragEnd = { isDragging.value = false },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                        change.consume()
                    }
                )
            }
            .clickable {
                myGlobalNumber = 2
                onCloseClicked()
            },
        shape = RoundedCornerShape(15.dp),
    ) {
        Box {
            Row {
                TextField(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(10.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 20.sp),
                    value = variableName.value,
                    onValueChange = { newText -> variableName.value = newText }
                )
                Text(
                    text = R.string.equal_with_spaces.toString(),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
                TextField(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(10.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 20.sp),
                    value = variableValue.value,
                    onValueChange = { newText -> variableValue.value = newText }
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
    selectedType.value = R.string.int_string.toString()
    // Сохраненное имя переменной
    variableName.value = R.string.new_variable.toString()

    Card(
        modifier = Modifier
            .width(500.dp)
            .padding(10.dp)
            .clickable {
                myGlobalNumber = 1
                onCloseClicked()
            },
        colors = CardDefaults.cardColors(Color.DarkGray),
        shape = RoundedCornerShape(15.dp)
    )
    {
        Column {
            Row()
            {
                Image(
                    painter = painterResource(id = R.drawable.globalvaricon), modifier = Modifier
                        .fillMaxSize(0.13f)
                        .padding(start = 10.dp), contentDescription = "var"
                )
                IconButton(onClick = { expanded = true }, modifier = Modifier.padding(top = 10.dp))
                {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                }
                Text(
                    text = selectedType.value,
                    modifier = Modifier.padding(top = 25.dp),
                    fontSize = 15.sp
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    val typeList = listOf(
                        R.string.int_string.toString(),
                        R.string.double_string.toString(),
                        R.string.string_string.toString()
                    )
                    typeList.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(text = type) },
                            onClick = {
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
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = variableName.value,
                    onValueChange = { inputedText ->
                        variableName.value = inputedText
                    }
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
                myGlobalNumber = 3
                onCloseClicked()
            },
        shape = RoundedCornerShape(15.dp),
    ) {
        Column {
            Row()
            {
                Text(
                    text = R.string.if_string.toString(),
                    modifier = Modifier.padding(15.dp),
                    fontSize = 15.sp
                )
                TextField(
                    modifier = Modifier.width(200.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = conditionFirst,
                    onValueChange = { newText ->
                        conditionFirst = newText
// Изменять значение внешнего класса (условия ифа) здесь (при изменении текст филда) именно через condition.value
                    }
                )
                IconButton(onClick = { expanded = true })
                {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                }
                Text(
                    text = selectedSign,
                    modifier = Modifier.padding(15.dp),
                    fontSize = 15.sp
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    val signList = listOf(
                        R.string.mega_equal.toString(),
                        R.string.not_equal.toString(),
                        R.string.greater.toString(),
                        R.string.greater_or_equal.toString(),
                        R.string.less.toString(),
                        R.string.less_or_equal.toString()
                    )
                    signList.forEach { sign ->
                        DropdownMenuItem(
                            text = { Text(text = sign) },
                            onClick = {
                                selectedSign = sign
                                expanded = false
                            })
                    }
                }
                TextField(
                    modifier = Modifier.width(200.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = conditionSecond,
                    onValueChange = { newText ->
                        conditionSecond = newText
// Изменять значение внешнего класса (условия ифа) здесь (при изменении текст филда) именно через condition.value
                    }
                )
                Button(
                    modifier = Modifier.padding(5.dp),
                    onClick = {
// Действие для удаления блока
                    }
                ) {}
            }
            Text(
                text = R.string.do_begin.toString(),
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
                myGlobalNumber = 4
                onCloseClicked()
            },
        shape = RoundedCornerShape(15.dp),
    ) {
        Column {
            Row()
            {
                Text(
                    text = R.string.for_string.toString(),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(15.dp)
                )
                TextField(
                    modifier = Modifier.width(100.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = initExpression.value,
                    onValueChange = { newText ->
                        initExpression.value = newText
                        // Изменять значение внешнего класса (пре-объявление переменной) здесь (при изменении текст филда) именно через initExpression.value
                    }
                )
                Text(text = " ", fontSize = 15.sp, modifier = Modifier.padding(15.dp))
                TextField(
                    modifier = Modifier.width(100.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = condExpression.value,
                    onValueChange = { newText ->
                        condExpression.value = newText
                        // Изменять значение внешнего класса (условие цикла) здесь (при изменении текст филда) именно через condExpression.value
                    }
                )
                Text(text = " ", fontSize = 15.sp, modifier = Modifier.padding(15.dp))
                TextField(
                    modifier = Modifier.width(100.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = loopExpression.value,
                    onValueChange = { newText ->
                        loopExpression.value = newText
                        // Изменять значение внешнего класса (действие цикла) здесь (при изменении текст филда) именно через loopExpression.value
                    }
                )
            }
            Text(
                text = R.string.do_begin.toString(),
                fontSize = 15.sp,
                modifier = Modifier.padding(15.dp)
            )
        }
    }
}


// Кард для вывода значения переменной
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoutBlock(onCloseClicked: () -> Unit) {
    val variableName = remember { mutableStateOf("") }
    // Сохраненное значение имени переменной
    variableName.value = ""
    Card(
        modifier = Modifier
            .width(400.dp)
            .padding(10.dp)
            .clickable {
                myGlobalNumber = 5
                onCloseClicked()
            },
        shape = RoundedCornerShape(15.dp),
    ) {
        Row()
        {
            Text(
                text = R.string.print.toString(),
                fontSize = 15.sp,
                modifier = Modifier.padding(15.dp)
            )
            TextField(
                modifier = Modifier.width(200.dp),
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
                modifier = Modifier
                    .fillMaxSize()
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
                            R.string.big_var.toString(),
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
                    CoutBlock(onCloseClicked = onCloseClicked)
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
                            R.string.big_if.toString(),
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
                            R.string.big_for.toString(),
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
                    onClick = onCloseClicked,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Icon(Icons.Filled.Close, contentDescription = "close")
                }
            }
        }
    }
}