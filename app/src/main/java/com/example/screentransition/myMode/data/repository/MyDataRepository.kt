package com.example.screentransition.myMode.data.repository

import com.example.screentransition.myMode.data.common.MyResult
import com.example.screentransition.myMode.data.model.MyData
import com.example.screentransition.myMode.data.model.MyName
import kotlinx.coroutines.flow.Flow

interface MyDataRepository {
    fun fetchMyData(): Flow<List<MyData>>
    suspend fun saveMyData(name: MyName): MyResult<Unit>
}