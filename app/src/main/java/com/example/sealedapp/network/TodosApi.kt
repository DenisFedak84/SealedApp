package com.example.sealedapp.network

import com.example.sealedapp.data.TodoModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface TodosApi {

    @GET("/todos")
    suspend fun getTodos(): List<TodoModel>
}