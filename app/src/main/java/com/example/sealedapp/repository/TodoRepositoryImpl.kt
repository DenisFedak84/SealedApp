package com.example.sealedapp.repository

import com.example.sealedapp.network.TodosApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Default implementation of [TodoRepository]. Single entry point for managing tasks' data.
 *
 * @param api - The network data source
 */
class TodoRepositoryImpl @Inject constructor(private val api: TodosApi): TodoRepository  {

    /**
     * Returns a list of tasks.
     */
    override fun getTodos() = flow {
        emit(api.getTodos())
    }
}