package com.example.sealedapp.repository

import com.example.sealedapp.data.TodoModel
import com.example.sealedapp.network.TodosApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TodoRepository @Inject constructor(private val api: TodosApi): ITodoRepository  {

    override fun getTodos() = flow {
        emit(api.getTodos())
    }
}