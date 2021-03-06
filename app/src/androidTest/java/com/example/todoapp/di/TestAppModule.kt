package com.example.todoapp.di

import com.example.todoapp.domain.model.ToDoItem
import com.example.todoapp.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object TestAppModule {
    @Singleton
    @Provides
    fun provideFakeHomeRepository() = object : HomeRepository {
        private var list = mutableListOf(
            ToDoItem(id = 1, title = "title 1", description = "test 1"),
            ToDoItem(id = 2, title = "title 2", description = "test 2"),
            ToDoItem(id = 3, title = "title 3", description = "test 3"),
            ToDoItem(id = 4, title = "title 4", description = "test 4"),
        )

        override fun getAllToDos(): Flow<List<ToDoItem>> {
            return flow { emit(list) }
        }

        override suspend fun deleteToDo(item: ToDoItem) {
            list.remove(item)
        }

        override suspend fun addToDo(item: ToDoItem): Long {
            val newItemIndex = list.size.toLong() + 1
            list.add(
                ToDoItem(
                    id = item.id ?: newItemIndex,
                    title = item.title,
                    description = item.description
                )
            )
            return newItemIndex
        }
    }

    @ExperimentalCoroutinesApi
    @Singleton
    @Provides
    fun provideDispatcher(): CoroutineDispatcher = TestCoroutineDispatcher()

}