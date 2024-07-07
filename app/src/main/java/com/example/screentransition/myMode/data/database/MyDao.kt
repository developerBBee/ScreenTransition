package com.example.screentransition.myMode.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.screentransition.myMode.data.model.MyData
import com.example.screentransition.myMode.data.model.MyDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDao {
    @Query(value = "SELECT * FROM my_data")
    fun fetchMyData(): Flow<List<MyDataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyData(myData: List<MyDataEntity>)
}