package com.example.screentransition.myMode.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_data")
data class MyDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)

fun MyDataEntity.asDataModel() = MyData(
    id = id,
    name = MyName(name)
)