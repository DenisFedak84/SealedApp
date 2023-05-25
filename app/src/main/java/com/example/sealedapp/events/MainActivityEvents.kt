package com.example.sealedapp.events

import com.example.sealedapp.data.TodoModel

sealed interface MainActivityEvents {
    class LoadingEvent(val loading: Boolean) : MainActivityEvents
    class ShowDataEvent(val todos: List<TodoModel>) : MainActivityEvents
    class ErrorEvent(val message: String) : MainActivityEvents
    object DefaultStateEvent : MainActivityEvents
}