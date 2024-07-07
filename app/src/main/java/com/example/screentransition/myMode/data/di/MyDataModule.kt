package com.example.screentransition.myMode.data.di

import android.content.Context
import androidx.room.Room
import com.example.screentransition.myMode.data.database.MyDao
import com.example.screentransition.myMode.data.database.MyDatabase
import com.example.screentransition.myMode.data.repository.MyDataRepository
import com.example.screentransition.myMode.data.repository.MyDataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyDataModule {
    @Singleton
    @Provides
    fun provideMyDatabase(@ApplicationContext context: Context): MyDatabase =
        Room.databaseBuilder(context, MyDatabase::class.java, "my_database").build()

    @Singleton
    @Provides
    fun provideMyDao(database: MyDatabase) = database.myDao()

    @Singleton
    @Provides
    fun provideMyDataRepository(myDao: MyDao): MyDataRepository = MyDataRepositoryImpl(myDao)
}