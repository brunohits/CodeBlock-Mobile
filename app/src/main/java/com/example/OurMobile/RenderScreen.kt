package com.example.OurMobile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

var cardIdCounter = 0

@Composable
fun MyScreen() {
    var showNewScreen by remember { mutableStateOf(false) }
    var consoleIsVisible by remember { mutableStateOf(false) }
    var isFirstTime by remember { mutableStateOf(true) }
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
            when(myGlobalNumber){
                1 -> typeVarListToAdd()
                2 -> varAssignmentToListAdd()
                3 -> ifBlockToListAdd()
                4 -> forBlockToListAdd()
                5 -> coutBlockToListAdd()
            }
            myGlobalNumber = 0

            if (isFirstTime) {
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
                isFirstTime = false
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
                            consoleIsVisible = !consoleIsVisible
                        })
                        {
                            Icon(Icons.Filled.List, contentDescription = null)
                        }
                    }
                }
                if(consoleIsVisible)
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
            for (card in typeVarList) {
                TypeVarDraggable(
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

            for (card in varAssignmentList) {
                VarAssignmentDraggable(
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
                IfBlockDraggable(
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
                ForBlockDraggable(
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
            for (card in CoutBlockList) {
                CoutBlockDraggable(
                    offsetX = card.offsetX,
                    offsetY = card.offsetY,
                    isDragging = card.isDragging,
                    variableName = card.variableName,
                    thisID = card.thisID,
                    CardList = CardList,
                )
            }
            for (card in BeginBlockList) {
                BeginBlockDraggable(
                    offsetX = card.offsetX,
                    offsetY = card.offsetY,
                    isDragging = card.isDragging,
                    thisID = card.thisID,
                    CardList = CardList
                )
            }
            for (card in EndBlockList) {
                EndBlockDraggable(
                    offsetX = card.offsetX,
                    offsetY = card.offsetY,
                    isDragging = card.isDragging,
                    thisID = card.thisID,
                    CardList = CardList
                )
            }

            if (NeedClear.IdToClear != -1) {
                if (NeedClear.WhatList == 1) {
                    typeVarList.removeIf { it.thisID == NeedClear.IdToClear }
                    CardList.removeIf { it.thisID == NeedClear.IdToClear }
                    NeedClear.IdToClear = -1
                }
            }
            val magnitRange = 80
            var cardHeightInPixels: Int
            var cardWidthInPixels: Int
            var center: Float
            var hasChild: Boolean
            //Магниты
            // LocalDensity.current.run { MagnitRange.toDp().to }
            if (CardList.all { !it.isDragging.value }) {
                for (i in 0 until CardList.size) {
                    hasChild = false
                    cardHeightInPixels =
                        LocalDensity.current.run { CardList[i].height.toPx() }.toInt()
                    cardWidthInPixels =
                        LocalDensity.current.run { CardList[i].width.toPx() }.toInt()
                    for (j in 0 until CardList.size) {
                        if (i != j && CardList[i].offsetY.value < CardList[j].offsetY.value && CardList[j].offsetY.value - (CardList[i].offsetY.value + cardHeightInPixels) < magnitRange) {
                            CardList[j].offsetY.value -= CardList[j].offsetY.value - (CardList[i].offsetY.value + cardHeightInPixels)
                            center = CardList[i].offsetX.value + (cardWidthInPixels/2)
                            cardWidthInPixels = LocalDensity.current.run { CardList[j].width.toPx() }.toInt()
                            CardList[j].offsetX.value = center - (cardWidthInPixels/2)
                            CardList[i].childId.value = CardList[j].thisID
                            hasChild = true
                        }
                    }
                    if (!hasChild) CardList[i].childId.value = -1

                }
            }


        }
    }
    Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
        Button(modifier= Modifier
            .fillMaxWidth(0.9f)
            .padding(bottom = 20.dp),onClick = {
            RunApp()
            consoleIsVisible = true
        })
        {
            Icon(Icons.Filled.PlayArrow, contentDescription = "run")
        }
    }
}

