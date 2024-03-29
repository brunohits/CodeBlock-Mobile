package com.example.OurMobile

import androidx.compose.ui.unit.dp

// методы для добавления новых карточек в лист
fun typeVarListToAdd() {
    typeVarList.add(TypeVarClass(id = cardIdCounter))
    CardList.add(
        CardClass(
            childId = typeVarList.last().childId,
            isDragging = typeVarList.last().isDragging,
            offsetX = typeVarList.last().offsetX,
            offsetY = typeVarList.last().offsetY,
            id = cardIdCounter,
            width = 500.dp,
            height = 80.dp
        )
    )
    cardIdCounter++
}

fun varAssignmentToListAdd() {
    varAssignmentList.add(VarAssignmentClass(id = cardIdCounter))
    CardList.add(
        CardClass(
            childId = varAssignmentList.last().childId,
            isDragging = varAssignmentList.last().isDragging,
            offsetX = varAssignmentList.last().offsetX,
            offsetY = varAssignmentList.last().offsetY,
            id = cardIdCounter,
            width = 500.dp,
            height = 80.dp
        )
    )
    cardIdCounter++
}

fun ifBlockToListAdd() {
    IfBlockList.add(IfBlockClass(id = cardIdCounter))
    CardList.add(
        CardClass(
            childId = IfBlockList.last().childId,
            isDragging = IfBlockList.last().isDragging,
            offsetX = IfBlockList.last().offsetX,
            offsetY = IfBlockList.last().offsetY,
            id = cardIdCounter,
            width = 500.dp,
            height = 150.dp
        )
    )
    cardIdCounter++
    EndBlockList.add(EndBlockClass(id = cardIdCounter))
    CardList.add(
        CardClass(
            childId = EndBlockList.last().childId,
            isDragging = EndBlockList.last().isDragging,
            offsetX = EndBlockList.last().offsetX,
            offsetY = EndBlockList.last().offsetY,
            id = cardIdCounter,
            width = 200.dp,
            height = 45.dp
        )
    )
    cardIdCounter++
}

fun forBlockToListAdd() {
    ForBlockList.add(ForBlockClass(id = cardIdCounter))
    CardList.add(
        CardClass(
            childId = ForBlockList.last().childId,
            isDragging = ForBlockList.last().isDragging,
            offsetX = ForBlockList.last().offsetX,
            offsetY = ForBlockList.last().offsetY,
            id = cardIdCounter,
            width = 250.dp,
            height = 200.dp
        )
    )
    cardIdCounter++
    EndBlockList.add(EndBlockClass(id = cardIdCounter))
    CardList.add(
        CardClass(
            childId = EndBlockList.last().childId,
            isDragging = EndBlockList.last().isDragging,
            offsetX = EndBlockList.last().offsetX,
            offsetY = EndBlockList.last().offsetY,
            id = cardIdCounter,
            width = 200.dp,
            height = 45.dp
        )
    )
    cardIdCounter++
}

fun printBlockToListAdd() {
    CoutBlockList.add(CoutBlockClass(id = cardIdCounter))
    CardList.add(
        CardClass(
            childId = CoutBlockList.last().childId,
            isDragging = CoutBlockList.last().isDragging,
            offsetX = CoutBlockList.last().offsetX,
            offsetY = CoutBlockList.last().offsetY,
            id = cardIdCounter,
            width = 300.dp,
            height = 80.dp
        )
    )
    cardIdCounter++
}