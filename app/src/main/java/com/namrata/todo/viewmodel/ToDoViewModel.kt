package com.namrata.todo.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.namrata.todo.MainApplication
import com.namrata.todo.model.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class ToDoViewModel:ViewModel() {

    private val toDoDao=MainApplication.toDoDB.getTodoDao()
    val toDoList:LiveData<List<ToDo>> = toDoDao.getAllToDo()

    @RequiresApi(Build.VERSION_CODES.O)
    fun addToDo(title:String){
        viewModelScope.launch(Dispatchers.IO) {
            toDoDao.addToDo(ToDo(title = title, createdAt = Date.from(Instant.now())))
        }
    }

    fun deleteToDo(id:Long){
        viewModelScope.launch(Dispatchers.IO) {
            toDoDao.deleteToDo(id)
        }
    }
}