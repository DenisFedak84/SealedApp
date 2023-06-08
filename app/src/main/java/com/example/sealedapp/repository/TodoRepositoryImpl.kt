package com.example.sealedapp.repository

import com.example.sealedapp.network.TodosApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(private val api: TodosApi): TodoRepository  {

    override fun getTodos() = flow {
        emit(api.getTodos())
    }
}