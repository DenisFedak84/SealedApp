package com.example.sealedapp.usecase

import com.example.sealedapp.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * UseCase for get todos list. Will return Resource.Error if list is empty
 * or Resource.Success if is data available.
 * @param repository - Single entry point for managing tasks' data.
 */
class GetTodosUseCase @Inject constructor(
    private val repository: TodoRepository
) : BaseUseCase() {

    override suspend fun invoke(request: Any?): Resource<Any> {

        repository.getTodos().flowOn(Dispatchers.IO).catch { e ->
            error = Resource.Error(e.message ?: "", null)
        }.collect {
            if (it.isEmpty()) {
                error = Resource.Error("Empty Data", null)
            } else {
                success = Resource.Success(it)
            }
        }

        return useCaseResult()
    }
}