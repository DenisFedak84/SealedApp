package com.example.sealedapp.network

import com.example.sealedapp.data.TodoModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

/**
 * Main entry point for accessing tasks data from the network.
 */
interface TodosApi {

    @GET("/todos")
    suspend fun getTodos(): List<TodoModel>
}