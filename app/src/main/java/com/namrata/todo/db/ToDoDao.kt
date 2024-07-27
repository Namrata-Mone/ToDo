package com.namrata.todo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.namrata.todo.model.ToDo

@Dao
interface ToDoDao {
    @Query("SELECT * FROM TODO")
    fun getAllToDo():LiveData<List<ToDo>>

    @Insert
    fun addToDo(toDo:ToDo)

    @Query("DELETE FROM ToDo where id=:id")
    fun deleteToDo(id:Long)
}