package com.example.sealedapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sealedapp.events.MainActivityEvents
import com.example.sealedapp.events.MainActivityEvents.LoadingEvent
import com.example.sealedapp.events.MainActivityEvents.DefaultStateEvent
import com.example.sealedapp.events.MainActivityEvents.ErrorEvent
import com.example.sealedapp.events.MainActivityEvents.ShowDataEvent
import com.example.sealedapp.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    private val _mainActivityEvents = MutableStateFlow<MainActivityEvents>(DefaultStateEvent)
    val mainActivityEvents: StateFlow<MainActivityEvents> = _mainActivityEvents

    fun getTodos() {
        viewModelScope.launch {
            _mainActivityEvents.value = LoadingEvent(true)
            delay(1000L)
            repository.getTodos().flowOn(Dispatchers.IO).catch { error ->
                _mainActivityEvents.value = LoadingEvent(false)
                _mainActivityEvents.value = ErrorEvent(error.message ?: "")
            }.collect {
                _mainActivityEvents.value = LoadingEvent(false)
                _mainActivityEvents.value = ShowDataEvent(it)
            }
        }
    }


}