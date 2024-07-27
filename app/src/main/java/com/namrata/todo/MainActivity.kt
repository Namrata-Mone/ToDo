package com.namrata.todo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.namrata.todo.ui.screen.ToDoScreen
import com.namrata.todo.ui.theme.ToDoTheme
import com.namrata.todo.viewmodel.ToDoViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toDoViewModel= ViewModelProvider(this)[ToDoViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            ToDoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ToDoScreen(innerPadding,toDoViewModel)
                }
            }
        }
    }
}