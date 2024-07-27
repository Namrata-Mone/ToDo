package com.namrata.todo

import android.app.Application
import androidx.room.Room
import com.namrata.todo.db.ToDoDatabase

class MainApplication :Application() {
    companion object{
        lateinit var toDoDB : ToDoDatabase
    }

    override fun onCreate() {
        super.onCreate()
        toDoDB=Room.databaseBuilder(
            context = applicationContext,
            ToDoDatabase::class.java,
            ToDoDatabase.NAME
        ).build()
    }
}