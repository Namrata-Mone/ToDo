package com.namrata.todo.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.namrata.todo.model.ToDo
import com.namrata.todo.viewmodel.ToDoViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ToDoScreen(innerPadding: PaddingValues,
               toDoViewModel: ToDoViewModel) {
    val toDoList by toDoViewModel.toDoList.observeAsState()
    var note by rememberSaveable {
        mutableStateOf("")
    }
    Column(Modifier.padding(innerPadding)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(value = note, onValueChange ={
                note=it
            } )
            Button(onClick = {
                toDoViewModel.addToDo(note)
                note=""
            }) {
                Text(text = "Add")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        toDoList?.let {
            LazyColumn {
                itemsIndexed(it.reversed()){_, toDo->
                    ToDoList(toDo = toDo,
                        onDelete = {toDoViewModel.deleteToDo(toDo.id)}
                    )
                }
            }
        }?:Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text="Add your tasks to the To Do list",
            fontSize = 16.sp
        )
    }
}

@Composable
fun ToDoList(toDo: ToDo,onDelete:()->Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clip(shape = RoundedCornerShape(10.dp))
        .background(MaterialTheme.colorScheme.primary)
        .border(
            border = ButtonDefaults.outlinedButtonBorder,
            shape = RoundedCornerShape(10.dp)
        )
    ){
        Column {
            Text(text = SimpleDateFormat("HH:mm aa, dd/MM", Locale.ENGLISH).format(toDo.createdAt),
                modifier = Modifier.padding(top=10.dp, start = 10.dp),
                color = Color(0x88ffffff),
                fontSize = 10.sp
            )
            Text(text=toDo.title,
                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
                color = Color(0xffffffff),
                fontSize = 15.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = onDelete) {
            Icon(imageVector = Icons.Default.Delete, tint = Color.White, contentDescription = "")
        }

    }
}
