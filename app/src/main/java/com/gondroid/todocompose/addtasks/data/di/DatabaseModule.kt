package com.gondroid.todocompose.addtasks.data.di

import android.content.Context
import androidx.room.Room
import com.gondroid.todocompose.addtasks.data.TodoDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun providesTodoDataBase(@ApplicationContext applicationContext: Context):  TodoDataBase {
        return Room.databaseBuilder(
            applicationContext,
            TodoDataBase::class.java,
            "taskDataBase"
        ).build()
    }

    @Provides
    fun providesTaskDao(todoDataBase: TodoDataBase) = todoDataBase.taskDao()
}