package com.example.sealedapp.viewmodel

import com.example.sealedapp.repository.FakeTodoRepositoryImpl
import com.example.sealedapp.usecase.GetTodosUseCase
import kotlinx.coroutines.test.runTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var repository: FakeTodoRepositoryImpl
    private lateinit var getTodosUseCase: GetTodosUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repository = FakeTodoRepositoryImpl()
        getTodosUseCase = GetTodosUseCase(repository)
        viewModel = MainViewModel(getTodosUseCase)
    }

    @Test
    fun `for success resource, data must be available`() = runTest {
        repository.isSuccess = true
        val result = getTodosUseCase.invoke()
        assertThat(result.data).isNotNull()
    }

    @Test
    fun `for error resource, errorMessage should not be empty`() = runTest {
        repository.isSuccess = false
        val result = getTodosUseCase.invoke()
        assertThat(result.message).isNotEmpty()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}