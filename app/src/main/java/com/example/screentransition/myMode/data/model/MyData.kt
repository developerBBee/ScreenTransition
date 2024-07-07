package com.example.screentransition.myMode.data.model

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable

@Serializable
data class MyData(
    val id: Int,
    val name: MyName
)

@Serializable
class MyName(val value: String) {
    companion object {
        fun createRandomName(): MyName = runBlocking {
            flow {
                repeat(5) {
                    emit((Char.MIN_VALUE .. Char.MAX_VALUE).random())
                }
            }
                .map { toString() }
                .reduce { accumulator, value -> accumulator + value }
                .let { MyName(it) }
        }

        fun createName(name: String): MyName {
            return MyName(value = name)
        }
    }
}