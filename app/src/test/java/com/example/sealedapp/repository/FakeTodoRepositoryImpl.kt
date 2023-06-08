package com.example.sealedapp.repository


import com.example.sealedapp.data.TodoModel
import kotlinx.coroutines.flow.flow

class FakeTodoRepositoryImpl : TodoRepository {

    var isSuccess = false

    override fun getTodos() = flow {
        val data = mutableListOf<TodoModel>()
        if (isSuccess){
            data.add(TodoModel(true,12,"Fake", 1))
        }
        emit(data)
    }
}