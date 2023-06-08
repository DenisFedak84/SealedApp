package com.example.sealedapp.repository

import com.example.sealedapp.data.TodoModel
import kotlinx.coroutines.flow.Flow

/**
 * Interface to the data layer.
 */
interface TodoRepository {

    fun getTodos(): Flow<List<TodoModel>>
}