package com.namrata.todo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class ToDo (
    @PrimaryKey(autoGenerate = true)
    var id:Long=0,
    val title:String,
    val createdAt: Date
)