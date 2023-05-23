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
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.*
import kotlin.math.roundToInt
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.OurMobile.R

var myGlobalNumber by mutableStateOf(0);


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VariableAssignment(onCloseClicked: () -> Unit) {
    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }
    val isDragging = remember { mutableStateOf(false) }
    val VariableName = remember { mutableStateOf("") }
    val VariableValue = remember { mutableStateOf("") }

    Card(

        modifier = Modifier
            .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
            .width(500.dp)
            .height(80.dp)
            .padding(10.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        myGlobalNumber = 2;
                        isDragging.value = true
                        onCloseClicked()
                    },
                    onDragEnd = { isDragging.value = false },
                    onDragCancel = { },
                    onDrag = { change, dragAmount ->
                        offsetX.value += dragAmount.x
                        offsetY.value += dragAmount.y
                        change.consumeAllChanges()
                    }
                )
            }
            .clickable {
                myGlobalNumber = 2;
                onCloseClicked()

            },
        shape = RoundedCornerShape(15.dp),
    ) {
        Box(
        ) {
            Row() {
                TextField(
                    modifier = Modifier
                        .width(200.dp)
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
                        .width(200.dp)
                        .padding(10.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 20.sp),
                    value = VariableValue.value,
                    onValueChange = { newText -> VariableValue.value = newText }
                )
                Button(
                    onClick = {},
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}

//Кард для создания переменной
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeVariable(onCloseClicked: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val variableName = remember { mutableStateOf("") }
    val selectedType = remember { mutableStateOf("") }

    // Сохраненный тип переменной
    selectedType.value = "int"
    // Сохраненное имя переменной
    variableName.value = "NewVariable"

    Card(
        modifier = Modifier
            .width(500.dp)
            .padding(10.dp)
            .clickable {
                myGlobalNumber = 1;
                onCloseClicked()
            },
        colors = CardDefaults.cardColors(Color.DarkGray),
        shape = RoundedCornerShape(15.dp)

    )
    {
        Column {
            Row()
            {
                Image(painter = painterResource(id = R.drawable.globalvaricon),modifier= Modifier
                    .fillMaxSize(0.13f)
                    .padding(start = 10.dp), contentDescription ="var" )
                IconButton(onClick = { expanded = true }, modifier = Modifier.padding(top=10.dp))
                {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                }
                Text(
                    text = selectedType.value,
                    modifier = Modifier.padding(top=25.dp),
                    fontSize = 15.sp
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "int") },
                        onClick = {
                            selectedType.value = "int"
                            // Изменять значение внешнего класса (типа переменной) здесь (при изменении дроп меню) именно через selectedType.value
                            expanded = false
                        })
                    DropdownMenuItem(
                        text = { Text(text = "double") },
                        onClick = {
                            selectedType.value = "double"
                            // Изменять значение внешнего класса (типа переменной) здесь (при изменении дроп меню) именно через selectedType.value
                            expanded = false
                        })
                    DropdownMenuItem(
                        text = { Text(text = "string") },
                        onClick = {
                            selectedType.value = "string"
                            // Изменять значение внешнего класса (типа переменной) здесь (при изменении дроп меню) именно через selectedType.value
                            expanded = false
                        })
                }
                Text(text = "   ", fontSize = 15.sp)
                TextField(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(top = 10.dp),
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

//Кард для ифа
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IfBlock(onCloseClicked: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val conditionFirst= remember { mutableStateOf("") }
    val conditionSecond = remember { mutableStateOf("") }
    var selectedSign by remember { mutableStateOf("") }
    Card(
        modifier = Modifier
            .width(500.dp)
            .padding(10.dp)
            .clickable {
                myGlobalNumber = 3;
                onCloseClicked()
            },
        shape = RoundedCornerShape(15.dp),
    ) {
        Column {
            Row()
            {
                Text(text = "If ", modifier = Modifier.padding(15.dp), fontSize = 15.sp)
                TextField(
                    modifier = Modifier.width(200.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = conditionFirst.value,
                    onValueChange = { newText ->
                        conditionFirst.value = newText
// Изменять значение внешнего класса (условия ифа) здесь (при изменении текст филда) именно через condition.value
                    }
                )
                IconButton(onClick = { expanded= true })
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
                    DropdownMenuItem(
                        text = { Text(text = "==") },
                        onClick = {
                            selectedSign = "=="
                            expanded = false
                        })
                    DropdownMenuItem(
                        text = { Text(text = "!=") },
                        onClick = {
                            selectedSign = "!="
                            expanded = false
                        })
                    DropdownMenuItem(
                        text = { Text(text = ">") },
                        onClick = {
                            selectedSign= ">"
                            expanded = false
                        })
                    DropdownMenuItem(
                        text = { Text(text = ">=") },
                        onClick = {
                            selectedSign = ">="
                            expanded = false
                        })
                    DropdownMenuItem(
                        text = { Text(text = "<") },
                        onClick = {
                            selectedSign = "<"
                            expanded = false
                        })
                    DropdownMenuItem(
                        text = { Text(text = "<=") },
                        onClick = {
                            selectedSign = "<="
                            expanded = false
                        })
                }
                TextField(
                    modifier = Modifier.width(200.dp),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                    value = conditionSecond.value,
                    onValueChange = { newText ->
                        conditionSecond.value = newText
// Изменять значение внешнего класса (условия ифа) здесь (при изменении текст филда) именно через condition.value
                    }
                )
                Button(
                    modifier = Modifier.padding(5.dp),
                    onClick = {
// Действие для удаления блока
                    }
                )
                {
                    Text(text = "Del", fontSize = 15.sp)
                }
            }
            Text(text = "Then begin", fontSize = 15.sp, modifier = Modifier.padding(15.dp))
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
                myGlobalNumber = 4;
                onCloseClicked();
            },
        shape = RoundedCornerShape(15.dp),
    ) {
        Column {
            Row()
            {
                Text(text = "For ", fontSize = 15.sp, modifier = Modifier.padding(15.dp))
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
                Button(
                    modifier = Modifier.padding(5.dp),
                    onClick = {
                        // Действие для удаления блока
                    }
                )
                {
                    Text(text = "Del", fontSize = 15.sp)
                }
            }
            Text(text = "Do begin", fontSize = 15.sp, modifier = Modifier.padding(15.dp))
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
                myGlobalNumber = 6;
                onCloseClicked();
            },
        shape = RoundedCornerShape(15.dp),
    ) {
        Row()
        {
            Text(text = "Cout ", fontSize = 15.sp, modifier = Modifier.padding(15.dp))
            TextField(
                modifier = Modifier.width(200.dp),
                textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
                value = variableName.value,
                onValueChange = { newText ->
                    variableName.value = newText
                    // Изменять значение внешнего класса (значение имени переменной) здесь (при изменении текст филда) именно через variableName.value
                }
            )
            Button(
                modifier = Modifier.padding(5.dp),
                onClick = {
                    // Действие для удаления блока
                }
            )
            {
                Text(text = "Del", fontSize = 15.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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
                            if (selectedButton == 1) {
                                selectedButton = -1
                            } else {
                                selectedButton = 1
                            }
                        },
                        shape = RoundedCornerShape(40.dp)  ,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(0.1f)
                    ) {
                        Row() {
                            Image(painter = painterResource(id = R.drawable.globalvaricon), contentDescription = "varicon",modifier=Modifier.size(40.dp))
                            Text("VARIABLES", fontSize = 20.sp, modifier = Modifier.padding(top=5.dp,start = 40.dp, end = 40.dp))
                            Image(painter = painterResource(id = R.drawable.globalvaricon), contentDescription = "varicon",modifier=Modifier.size(40.dp))
                        }
                    }
                if (selectedButton == 1) {
                    TypeVariable(onCloseClicked = onCloseClicked)
                    VariableAssignment(onCloseClicked = onCloseClicked)
                    CoutBlock(onCloseClicked = onCloseClicked)
                }
                Button(
                    onClick = {
                        if (selectedButton == 2) {
                            selectedButton = -1
                        } else {
                            selectedButton = 2
                        }
                    },
                    shape = RoundedCornerShape(40.dp)  ,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight(0.1f)
                ) {
                    Row() {
                        Image(painter = painterResource(id = R.drawable.ificon), contentDescription = "ificon",modifier=Modifier.size(40.dp))
                        Text("IF", fontSize = 20.sp, modifier = Modifier.padding(top=5.dp,start = 55.dp, end = 55.dp))
                        Image(painter = painterResource(id = R.drawable.ificon), contentDescription = "ificon",modifier=Modifier.size(40.dp))
                    }
                }
                if (selectedButton == 2) {
                    IfBlock(onCloseClicked = onCloseClicked)
                }
                Button(
                    onClick = {
                        if (selectedButton == 3) {
                            selectedButton = -1
                        } else {
                            selectedButton = 3
                        }
                    },
                    shape = RoundedCornerShape(40.dp)  ,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.1f)
                ) {
                    Row() {
                        Image(painter = painterResource(id = R.drawable.foricon), contentDescription = "ificon",modifier=Modifier.size(40.dp))
                        Text("FOR", fontSize = 18.sp, modifier = Modifier.padding(top=5.dp,start = 20.dp, end = 20.dp))
                        Image(painter = painterResource(id = R.drawable.foricon), contentDescription = "ificon",modifier=Modifier.size(40.dp))
                    }
                }
                if (selectedButton == 3) {
                    ForBlock(onCloseClicked = onCloseClicked)
                }
                IconButton(
                    onClick = onCloseClicked,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Icon(Icons.Filled.Close, contentDescription ="close" )
                }
            }
        } else {
            Button(
                onClick = {},
            ) {
                Text("Нажми меня еще раз")
            }
        }
    }
}