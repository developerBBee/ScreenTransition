package com.example.screentransition.myMode.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.screentransition.myMode.data.model.MyData
import com.example.screentransition.myMode.data.model.MyDataEntity

@Database(entities = [MyDataEntity::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao
}