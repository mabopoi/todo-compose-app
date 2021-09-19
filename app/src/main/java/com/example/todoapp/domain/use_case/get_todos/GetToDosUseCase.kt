package com.example.todoapp.domain.use_case.get_todos

import com.example.todoapp.common.Resource
import com.example.todoapp.domain.model.ToDoItem
import com.example.todoapp.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetToDosUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    operator fun invoke(): Flow<Resource<Flow<List<ToDoItem>>>> = flow {
        emit(Resource.Loading())
        try {
            val listFlow = homeRepository.getAllToDos()
            emit(Resource.Success(data = listFlow))
        } catch (e: Exception) {
            emit(Resource.Error<Flow<List<ToDoItem>>>(message = e.message ?: "There was an error"))
        }
    }

}