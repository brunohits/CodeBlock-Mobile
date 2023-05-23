package com.example.OurMobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.*
import com.example.OurMobile.ui.theme.OurMobileTheme

var cardIdCounter = 0

@Composable
fun MyScreen() {
    var showNewScreen by remember { mutableStateOf(false) }
    var ConsoleIsVisible by remember { mutableStateOf(false) }
    var FirstTime by remember { mutableStateOf(true) }

    // методы для добавления новой карточки в список
    fun TypeVaribleListAddCard() {
        TypeVaribleList.add(TypeVaribleClass(thisID = cardIdCounter))
        CardList.add(CardClass(childId = TypeVaribleList.last().childId,isDragging = TypeVaribleList.last().isDragging, offsetX = TypeVaribleList.last().offsetX, offsetY = TypeVaribleList.last().offsetY,thisID = cardIdCounter, width = 500.dp, height = 80.dp))
        cardIdCounter++;
    }

    fun VariableAssignmentListAddCard() {
        VariableAssignmentList.add(VariableAssignmentClass(thisID = cardIdCounter))
        CardList.add(CardClass(childId = VariableAssignmentList.last().childId,isDragging = VariableAssignmentList.last().isDragging, offsetX = VariableAssignmentList.last().offsetX, offsetY = VariableAssignmentList.last().offsetY,thisID = cardIdCounter,  width = 500.dp, height = 80.dp))
        cardIdCounter++;
    }
    fun IfBlockListAddCard() {
        IfBlockList.add(IfBlockClass(thisID = cardIdCounter))
        CardList.add(CardClass(childId = IfBlockList.last().childId,isDragging = IfBlockList.last().isDragging, offsetX = IfBlockList.last().offsetX, offsetY = IfBlockList.last().offsetY,thisID = cardIdCounter,width = 500.dp, height = 150.dp))
        cardIdCounter++;
        //BeginBlockList.add(BeginBlockClass(thisID = cardIdCounter))
        //CardList.add(CardClass(childId = BeginBlockList.last().childId,isDragging = BeginBlockList.last().isDragging, offsetX = BeginBlockList.last().offsetX, offsetY = BeginBlockList.last().offsetY,thisID = cardIdCounter,width = 500, height = 80.dp))
        //cardIdCounter++;
        EndBlockList.add(EndBlockClass(thisID = cardIdCounter))
        CardList.add(CardClass(childId = EndBlockList.last().childId,isDragging = EndBlockList.last().isDragging, offsetX = EndBlockList.last().offsetX, offsetY = EndBlockList.last().offsetY,thisID = cardIdCounter,width = 200.dp, height = 45.dp))
        cardIdCounter++;
    }
    fun ForBlockListAddCard() {
        ForBlockList.add(ForBlockClass(thisID = cardIdCounter))
        CardList.add(CardClass(childId = ForBlockList.last().childId,isDragging = ForBlockList.last().isDragging, offsetX = ForBlockList.last().offsetX, offsetY = ForBlockList.last().offsetY,thisID = cardIdCounter,width = 250.dp, height = 200.dp))
        cardIdCounter++;
        //BeginBlockList.add(BeginBlockClass(thisID = cardIdCounter))
        //CardList.add(CardClass(childId = BeginBlockList.last().childId,isDragging = BeginBlockList.last().isDragging, offsetX = BeginBlockList.last().offsetX, offsetY = BeginBlockList.last().offsetY,thisID = cardIdCounter,width = 500, height = 80.dp))
        //cardIdCounter++;
        EndBlockList.add(EndBlockClass(thisID = cardIdCounter))
        CardList.add(CardClass(childId = EndBlockList.last().childId,isDragging = EndBlockList.last().isDragging, offsetX = EndBlockList.last().offsetX, offsetY = EndBlockList.last().offsetY,thisID = cardIdCounter,width = 200.dp, height = 45.dp))
        cardIdCounter++;
    }

    fun CinBlockListAddCard() {
        CinBlockList.add(CinBlockClass(thisID = cardIdCounter))
        CardList.add(CardClass(childId = CinBlockList.last().childId,isDragging = CinBlockList.last().isDragging, offsetX = CinBlockList.last().offsetX, offsetY = CinBlockList.last().offsetY,thisID = cardIdCounter,width = 300.dp, height = 80.dp))
        cardIdCounter++;
    }

    fun CoutBlockListAddCard() {
        CoutBlockList.add(CoutBlockClass(thisID = cardIdCounter))
        CardList.add(CardClass(childId = CoutBlockList.last().childId,isDragging = CoutBlockList.last().isDragging, offsetX = CoutBlockList.last().offsetX, offsetY = CoutBlockList.last().offsetY,thisID = cardIdCounter,width = 300.dp, height = 80.dp))
        cardIdCounter++;
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.TopCenter)
            .padding(top = 20.dp)
    ) {
        AnimatedVisibility(
            visible = showNewScreen,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 500)
            ),
            exit = slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(durationMillis = 500)
            )
        ) {
            MenuScreen(showNewScreen = showNewScreen) {
                // Закрываем экран
                showNewScreen = false
            }
        }

        if (!showNewScreen) {
            if (myGlobalNumber == 1) {
                TypeVaribleListAddCard()
                myGlobalNumber = 0;
            }
            if (myGlobalNumber == 2) {
                VariableAssignmentListAddCard()
                myGlobalNumber = 0;
            }
            if (myGlobalNumber == 3) {
                IfBlockListAddCard()
                myGlobalNumber = 0;
            }
            if (myGlobalNumber == 4) {
                ForBlockListAddCard()
                myGlobalNumber = 0;
            }
            if (myGlobalNumber == 5) {
                CinBlockListAddCard()
                myGlobalNumber = 0;
            }
            if (myGlobalNumber == 6) {
                CoutBlockListAddCard()
                myGlobalNumber = 0;
            }
            if (FirstTime) {
                EndBeginBlockList.add(EndBeginBlockClass(thisID = cardIdCounter))
                CardList.add(
                    CardClass(
                        childId = EndBeginBlockList.last().childId,
                        isDragging = EndBeginBlockList.last().isDragging,
                        offsetX = EndBeginBlockList.last().offsetX,
                        offsetY = EndBeginBlockList.last().offsetY,
                        thisID = 0,
                        width = 300.dp,
                        height = 60.dp
                    )
                )
                cardIdCounter++
                EndBeginBlockList.add(EndBeginBlockClass(thisID = cardIdCounter))
                CardList.add(
                    CardClass(
                        childId = EndBeginBlockList.last().childId,
                        isDragging = EndBeginBlockList.last().isDragging,
                        offsetX = EndBeginBlockList.last().offsetX,
                        offsetY = EndBeginBlockList.last().offsetY,
                        thisID = 1,
                        width = 300.dp,
                        height = 60.dp
                    )
                )
                cardIdCounter++
                FirstTime = false
            }
            Column()

            {
                Row() {


                    Button(
                        modifier = Modifier.fillMaxWidth(0.3f),
                        onClick = {

                            showNewScreen = true // показываем новый экран
                        },
                    ) {
                        Text("MENU")
                    }
                    Row()
                    {

                        IconButton(onClick = {
                            ConsoleIsVisible = !ConsoleIsVisible
                        })
                        {
                            Icon(Icons.Filled.List, contentDescription = null)
                        }
                    }
                }
                if(ConsoleIsVisible)
                {
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .width(500.dp)
                            .height(200.dp)
                            .background(color = Color.Black)
                    ) {
                        Column()
                        {
                            LazyColumn(
                            ) {
                                itemsIndexed(messagesCout) { index, item ->
                                    Text(text = item)
                                }
                            }
                        }
                    }
                }
            }
            //отрисовка
            for (card in TypeVaribleList) {
                TypeVariableReal(
                    offsetX = card.offsetX,
                    offsetY = card.offsetY,
                    isDragging = card.isDragging,
                    variableName = card.variableName,
                    expanded = card.expanded,
                    selectedType = card.selectedType,
                    thisID = card.thisID,
                    CardList = CardList,
                )

            }
            for (card in EndBeginBlockList) {
                if (card.thisID == 0) {
                    BeginBlock(
                        offsetX = card.offsetX,
                        offsetY = card.offsetY,
                        isDragging = card.isDragging,
                        thisID = card.thisID,
                        CardList = CardList
                    )
                } else {
                    EndBlock(
                        offsetX = card.offsetX,
                        offsetY = card.offsetY,
                        isDragging = card.isDragging,
                        thisID = card.thisID,
                        CardList = CardList
                    )
                }
            }

            for (card in VariableAssignmentList) {
                VariableAssignmentReal(
                    offsetX = card.offsetX,
                    offsetY = card.offsetY,
                    isDragging = card.isDragging,
                    VariableName = card.variableName,
                    VariableValue = card.variableValue,
                    thisID = card.thisID,
                    CardList = CardList,
                )
            }
            for (card in IfBlockList) {
                IfBlockReal(
                    offsetX = card.offsetX,
                    offsetY = card.offsetY,
                    isDragging = card.isDragging,
                    conditionFirst = card.conditionFirst,
                    conditionSecond = card.conditionSecond,
                    expanded = card.expanded,
                    selectedSign = card.selectedSign,
                    thisID = card.thisID,
                    CardList = CardList,

                    )
            }
            for (card in ForBlockList) {
                ForBlockReal(
                    offsetX = card.offsetX,
                    offsetY = card.offsetY,
                    isDragging = card.isDragging,
                    initExpression = card.initExpression,
                    condExpression = card.condExpression,
                    loopExpression = card.loopExpression,
                    thisID = card.thisID,
                    CardList = CardList,

                    )
            }
            for (card in CinBlockList) {
                CinBlockReal(
                    offsetX = card.offsetX,
                    offsetY = card.offsetY,
                    isDragging = card.isDragging,
                    variableName = card.variableName,
                    thisID = card.thisID,
                    CardList = CardList,
                )
            }
            for (card in CoutBlockList) {
                CoutBlockReal(
                    offsetX = card.offsetX,
                    offsetY = card.offsetY,
                    isDragging = card.isDragging,
                    variableName = card.variableName,
                    thisID = card.thisID,
                    CardList = CardList,
                )
            }
            for (card in BeginBlockList) {
                BeginBlockReal(
                    offsetX = card.offsetX,
                    offsetY = card.offsetY,
                    isDragging = card.isDragging,
                    thisID = card.thisID,
                    CardList = CardList
                )
            }
            for (card in EndBlockList) {
                EndBlockReal(
                    offsetX = card.offsetX,
                    offsetY = card.offsetY,
                    isDragging = card.isDragging,
                    thisID = card.thisID,
                    CardList = CardList
                )
            }

            if (NeedClear.IdToClear != -1) {
                if (NeedClear.WhatList == 1) {
                    TypeVaribleList.removeIf { it.thisID == NeedClear.IdToClear }
                    CardList.removeIf { it.thisID == NeedClear.IdToClear }
                    NeedClear.IdToClear = -1;
                }
            }
            val MagnitRange = 80;
            var cardHeightInPixels = 0
            var cardWidthInPixels = 0
            var center = 0f;
            var HasChild = false;
            //Магниты
            // LocalDensity.current.run { MagnitRange.toDp().to }
            if (CardList.all { it.isDragging.value == false }) {
                for (i in 0 until CardList.size) {
                    HasChild = false;
                    cardHeightInPixels =
                        LocalDensity.current.run { CardList[i].height.toPx() }.toInt()
                    cardWidthInPixels =
                        LocalDensity.current.run { CardList[i].width.toPx() }.toInt()
                    for (j in 0 until CardList.size) {
                        if (i != j && CardList[i].offsetY.value < CardList[j].offsetY.value && CardList[j].offsetY.value - (CardList[i].offsetY.value + cardHeightInPixels) < MagnitRange) {
                            CardList[j].offsetY.value -= CardList[j].offsetY.value - (CardList[i].offsetY.value + cardHeightInPixels)
                            center = CardList[i].offsetX.value + (cardWidthInPixels/2)
                            cardWidthInPixels = LocalDensity.current.run { CardList[j].width.toPx() }.toInt()
                            CardList[j].offsetX.value = center - (cardWidthInPixels/2)
                            CardList[i].childId.value = CardList[j].thisID;
                            HasChild = true;
                        }
                    }
                    if (!HasChild) {
                        CardList[i].childId.value = -1;
                    }
                }
            }


        }
    }
    Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
        Button(modifier= Modifier
            .fillMaxWidth(0.9f)
            .padding(bottom = 20.dp),onClick = {
            RunApp()
            ConsoleIsVisible = true
        })
        {
            Icon(Icons.Filled.PlayArrow, contentDescription = null)
        }
    }
}



class MainActivity : ComponentActivity() {
    var pixelsPerDp: Float = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pixelsPerDp = resources.displayMetrics.density

        setContent {
            OurMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.DarkGray
                ) {
                    MyScreen()
                }
            }
        }
    }
}
