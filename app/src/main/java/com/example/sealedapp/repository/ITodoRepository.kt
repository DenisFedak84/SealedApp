package com.example.sealedapp.repository

import com.example.sealedapp.data.TodoModel
import kotlinx.coroutines.flow.Flow

interface ITodoRepository {

    fun getTodos(): Flow<List<TodoModel>>
}