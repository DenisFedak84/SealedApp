package com.example.sealedapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sealedapp.data.TodoModel
import com.example.sealedapp.events.MainActivityEvents
import com.example.sealedapp.events.MainActivityEvents.LoadingEvent
import com.example.sealedapp.events.MainActivityEvents.DefaultStateEvent
import com.example.sealedapp.events.MainActivityEvents.ErrorEvent
import com.example.sealedapp.events.MainActivityEvents.ShowDataEvent
import com.example.sealedapp.usecase.GetTodosUseCase
import com.example.sealedapp.usecase.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase
) : ViewModel() {

    private val _mainActivityEvents = MutableStateFlow<MainActivityEvents>(DefaultStateEvent)
    val mainActivityEvents: StateFlow<MainActivityEvents> = _mainActivityEvents

    @Suppress("UNCHECKED_CAST")
    fun getTodos() {
        viewModelScope.launch {
            _mainActivityEvents.value = LoadingEvent(true)
            delay(1000L)
            when (val result = getTodosUseCase.invoke()) {
                is Resource.Success -> _mainActivityEvents.value = ShowDataEvent(result.data as List<TodoModel>)
                is Resource.Error -> _mainActivityEvents.value = ErrorEvent(result.message ?: "")
            }
            _mainActivityEvents.value = LoadingEvent(false)
        }
    }
}