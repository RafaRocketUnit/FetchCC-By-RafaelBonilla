package com.rafarocket.fce.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable screen view that renders the List of Hiring data.
 */
@Preview
@Composable
fun HiringListScreen(@PreviewParameter(PreviewParameterViewModel::class) hiringViewModel: HiringViewModel) {

    val stateComposable = remember {
        mutableStateOf<ViewStates>(ViewStates.Idle)
    }

    LaunchedEffect(Unit) {
        hiringViewModel.fetchHiringList()
        hiringViewModel.hiring.collect {
            stateComposable.value = it
        }
    }
    
    HandleResult(viewStates = stateComposable.value)
}

@Composable
fun DrawList(resultHiring: Map<Int, List<HiringUi>>) {
    LazyColumn(modifier = Modifier
        .fillMaxSize(1F)
        .testTag("containerList")
    ) {
        this.items(resultHiring.keys.toList())
        {
            DrawItemsOfId(listId = it, list = resultHiring[it] ?: emptyList())
        }
    }
}

@Composable
fun DrawItemsOfId(listId: Int, list: List<HiringUi>) {
    Column(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp)
            .testTag("subContainerList")
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0x50, 0xAA, 0x50, 0x55))
    ) {
        GroupItem(listId)
        ListOfItems(list)
    }
}

@Composable
fun HandleResult(viewStates: ViewStates) {
    when (viewStates) {
        is ViewStates.HiringListObtained -> DrawList(resultHiring = viewStates.result)
        is ViewStates.Error -> ShowError(error = viewStates.error)
        is ViewStates.Idle -> Unit
    }
}

@Composable
fun ListOfItems(list: List<HiringUi>) {
    // Making a for loop, only for the listId items to be drawn.
    // Also sort by the number contained in the name.
    val color = Color(red = randomIntColor(), green = randomIntColor(), blue = randomIntColor(), 0x77)

    list.forEach { hiring ->
        ListItem(hiring, color)
    }
}

@Composable
fun ListItem(hiring: HiringUi, color: Color) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(6.dp)
        .height(60.dp)
        .background(color)) {
        Text(
            text = hiring.name ?: "",
            modifier = Modifier.padding(8.dp).testTag("listItem"),
            color = Color.White
        )
    }
}

@Composable
fun GroupItem(listId: Int) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
        .height(60.dp)
        .background(Color(red = 0x00, green = 0x00, blue = 0x00, alpha = 0x77))) {
        Text(
            text = "Items of List id $listId",
            modifier = Modifier.padding(8.dp).testTag("groupItem"),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Composable
fun ShowError(error: String) {
    Text(
        text = "Error: $error",
        modifier = Modifier.padding(8.dp).testTag("error"),
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
}

fun randomIntColor() = (Math.random() * 16777215).toInt() or (0xFF shl 24)

