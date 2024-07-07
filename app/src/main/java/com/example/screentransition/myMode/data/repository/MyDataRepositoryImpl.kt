package com.example.screentransition.myMode.data.repository

import com.example.screentransition.myMode.data.common.MyResult
import com.example.screentransition.myMode.data.database.MyDao
import com.example.screentransition.myMode.data.model.MyData
import com.example.screentransition.myMode.data.model.MyDataEntity
import com.example.screentransition.myMode.data.model.MyName
import com.example.screentransition.myMode.data.model.asDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyDataRepositoryImpl @Inject constructor(
    private val dao: MyDao
) : MyDataRepository {
    override fun fetchMyData(): Flow<List<MyData>> {
        return dao.fetchMyData().map { it.map(MyDataEntity::asDataModel) }
    }

    override suspend fun saveMyData(name: MyName): MyResult<Unit> = runCatching {
        dao.insertMyData(listOf(MyDataEntity(0, name.value)))
    }.fold(
        onSuccess = { MyResult.Success(Unit) },
        onFailure = { MyResult.Error(exception = it) }
    )
}