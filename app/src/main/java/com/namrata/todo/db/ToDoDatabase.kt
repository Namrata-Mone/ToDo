package com.namrata.todo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.namrata.todo.model.ToDo

@Database(entities = [ToDo::class], version = 1)
@TypeConverters(Converters::class)
abstract class ToDoDatabase: RoomDatabase() {
    companion object{
        const val NAME = "Todo_DB"
    }
    abstract fun getTodoDao() : ToDoDao
}